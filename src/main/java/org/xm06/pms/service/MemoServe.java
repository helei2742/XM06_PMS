package org.xm06.pms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xm06.pms.dao.MemoMapper;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.utils.DateUtil;
import org.xm06.pms.vo.Memo;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class MemoServe {
    @Resource
    private MemoMapper memoMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 添加便签记录
     * @param userId
     * @param memo
     * @param createDate
     * @param staleDate
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMemo(Integer userId, String memo,
                        String createDate, String staleDate){

        AssertUtil.isTrue(StringUtils.isBlank(createDate), "输入创建时间");
        AssertUtil.isTrue(StringUtils.isBlank(staleDate), "请输入过期时间");
        AssertUtil.isTrue(StringUtils.isBlank(memo), "请输入标签内容");

        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(user == null, "没有该用户");
        Memo m = new Memo();
        m.setUserId(userId);
        m.setMemo(memo);
        m.setFinish(false);
        try {
            m.setCreateDate(DateUtil.parse(createDate));
            m.setStaleDate(DateUtil.parse(staleDate));
        } catch (ParseException e) {
            e.printStackTrace();
            AssertUtil.isTrue(true, "请输入正确格式的日期");
        }

        AssertUtil.isTrue(memoMapper.insertSelective(m)<1, "保存便签失败");
    }

    /**
     * 查询用户的便签
     * @param userId
     * @return
     */
    public List<Memo> queryUserMemo(Integer userId){
        return memoMapper.queryUserMemos(userId);
    }


    /**
     * 移除用户指定的便签
     * @param userId
     * @param memoId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeMemo(Integer userId, Integer memoId) {
        Memo memo = memoMapper.selectByPrimaryKey(memoId);
        AssertUtil.isTrue(memo == null, "没有该便签记录");
        AssertUtil.isTrue(memo.getUserId() != userId, "便签记录与用户不符");
        AssertUtil.isTrue(memoMapper.deleteByPrimaryKey(memoId)<1, "删除便签记录失败");
    }

    /**
     * 确认便签状态为完成状态
     * @param userId
     * @param memoId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void confirmMemo(Integer userId, Integer memoId) {
        Memo memo = memoMapper.selectByPrimaryKey(memoId);
        AssertUtil.isTrue(memo == null, "没有该便签记录");
        AssertUtil.isTrue(memo.getUserId() != userId, "便签记录与用户不符");
        AssertUtil.isTrue(memoMapper.confirmMemo(memoId)<1, "确认便签完成失败");
    }
}

