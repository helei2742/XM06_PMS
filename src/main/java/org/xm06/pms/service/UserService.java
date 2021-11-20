package org.xm06.pms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xm06.pms.base.BaseService;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.model.UserModel;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.utils.Md5Util;
import org.xm06.pms.utils.PhoneUtil;
import org.xm06.pms.utils.UserIDBase64;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService extends BaseService<User, Integer> {

    @Resource
    UserMapper userMapper;

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 登录
     * @param loginUser
     * @return
     */
    public UserModel login(User loginUser) {
        AssertUtil.isTrue(StringUtils.isBlank(loginUser.getUserName()), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(loginUser.getUserPwd()), "密码不能为空");
        User user = userMapper.findByUsername(loginUser.getUserName());

        AssertUtil.isTrue(user == null, "该用户不存在");
        String encode = Md5Util.encode(loginUser.getUserPwd());
        AssertUtil.isTrue(!user.getUserPwd().equals(encode), "密码错误");
        System.out.println(user.getIsValid());
        AssertUtil.isTrue(!user.getIsValid(), "用户尚未验证邮箱，请前往邮箱验证");
        return getUserModel(user);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public UserModel addUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPwd()), "密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getEmail()), "请输入正确邮箱用户验证");

        User selectUser = userMapper.findByUsername(user.getUserName());
        AssertUtil.isTrue(selectUser != null, "用户名已被使用");


        //        AssertUtil.isTrue(user.getPhone()!=null
//                && !PhoneUtil.isMobile(user.getPhone()), "请输入正确的手机号");

        String encode = Md5Util.encode(user.getUserPwd());
        user.setUserPwd(encode);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setIsValid(false);

        AssertUtil.isTrue(userMapper.insertSelective(user)<1, "注册失败");
        Integer userId = userMapper.queryIdByUsername(user.getUserName());

        sendConfirmMail(userId, user.getEmail());
        return getUserModel(user);
    }


    /**
     * 发送确认邮件
     * @param id
     * @param email
     */
    private void sendConfirmMail(Integer id, String email){
        Context context = new Context();
        context.setVariable("id", id);
        String emailContent = templateEngine.process("userRegistConfirm", context);
        mailService.sendHtmlMail(email, "注册确认", emailContent);

    }

    /**
     * 确定注册
     * @param userId
     */
    public void confirm(Integer userId){
        User user = selectByPrimaryKey(userId);
        AssertUtil.isTrue(user == null, "该用户尚未注册");
        AssertUtil.isTrue(userMapper.confirm(userId) < 1,"确认注册失败");
    }


    public User findByIdStr(Integer userID) {
        User user = userMapper.selectByPrimaryKey(userID);
        AssertUtil.isTrue(user == null, "用户不存在");
        user.setUserPwd("");
        return user;
    }

    /**
     * 修改用户信息，不包括修改密码
     * @param user
     * @return
     */
    public UserModel alter(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空");
        AssertUtil.isTrue(user.getPhone()!=null
                && !PhoneUtil.isMobile(user.getPhone()), "请输入正确的手机号");

        User selectById = userMapper.selectByPrimaryKey(user.getId());
        AssertUtil.isTrue(selectById == null, "修改用户不存在");

        User selectByUsername = userMapper.findByUsername(user.getUserName());
        AssertUtil.isTrue(selectByUsername != null &&
                selectById.getId() != selectByUsername.getId()
                , "用户名已被使用");

        user.setUpdateDate(new Date());
        AssertUtil.isTrue(userMapper.updateUserNoPwd(user)<1, "更新信息失败");
        return getUserModel(user);
    }

    /**
     * 修改密码
     * @param
     */
    public void alterPassword(int id, String oldPwd, String newPwd, String confirmPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "请输入旧密码");
        AssertUtil.isTrue(StringUtils.isBlank(newPwd), "请输入新密码");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPwd), "请再次确认密码");
        AssertUtil.isTrue(!newPwd.equals(confirmPwd), "两次输入的密码不相同");
        AssertUtil.isTrue(oldPwd.equals(newPwd), "新密码与原来相同");

        User selectById = userMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(selectById == null, "该用户不存在");
        AssertUtil.isTrue(!selectById.getUserPwd().equals(Md5Util.encode(oldPwd)),
                "输入旧密码错误");

        User user = new User();
        user.setUpdateDate(new Date());
        user.setId(id);
        user.setUserPwd(Md5Util.encode(newPwd));
        AssertUtil.isTrue(userMapper.alterPassword(user)<1, "修改密码失败");
    }

    public UserModel getUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUsername(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    public User queryByUsername(String username){
        return userMapper.findByUsername(username);
    }

}
