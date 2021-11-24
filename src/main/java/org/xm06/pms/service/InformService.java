package org.xm06.pms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xm06.pms.dao.GroupMapper;
import org.xm06.pms.dao.InformMapper;
import org.xm06.pms.dao.NotReadInformMapper;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.model.InformModel;
import org.xm06.pms.model.UserModel;
import org.xm06.pms.query.InformQuery;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.utils.LoginUtil;
import org.xm06.pms.utils.UserIDBase64;
import org.xm06.pms.vo.Inform;
import org.xm06.pms.vo.User;
import org.xm06.pms.websocket.WebSocketServer;

import javax.annotation.Resource;
import javax.sound.sampled.Line;
import java.util.*;

@Service
public class InformService {

    @Resource
    InformMapper informMapper;
    @Resource
    NotReadInformMapper notReadInformMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    GroupMapper groupMapper;


    /**
     * 保存消息
     * @param inform
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveInform(Inform inform) {
        return informMapper.insertHasKey(inform);
    }

    /**
     * 保存消息，
     * @param groupInform <用户id, 消息[]> >
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveGroupInform(Hashtable<Integer, Vector<InformModel>> groupInform){
        Set<Integer> keySet = groupInform.keySet();
        for (Integer group_id : keySet) {
            Vector<InformModel> informModels = groupInform.get(group_id);
            for (InformModel informModel : informModels) {
                System.out.println("保存消息"+informModel);
                informMapper.insertSelective(informModelConvent(informModel));
            }
        }
    }

    /**
     * 保存用户在某一组的 未读的消息
     * @param notReadMessage <用户id, <小组id, 未看消息id[]> >
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveNotReadInform(Hashtable<Integer,Hashtable<Integer, Vector<Long>>> notReadMessage) {
        Set<Integer> userIds = notReadMessage.keySet();
        for (Integer userId : userIds) {
            Hashtable<Integer, Vector<Long>> groupTable = notReadMessage.get(userId);
            Set<Integer> groupIds = groupTable.keySet();
            for (Integer groupId : groupIds) {
                Vector<Long> informs = groupTable.get(groupId);
                for (Long informId : informs) {
                    notReadInformMapper.insert(userId, groupId, informId);
                }
            }
        }
    }

    /**
     * informModel 对象转换位 Inform对象
     * @param informModel
     * @return
     */
    private Inform informModelConvent(InformModel informModel) {
        Inform inform = new Inform();
        inform.setId(informModel.getId());
        inform.setSendUserId(LoginUtil.releaseUserId(informModel.getSendUser().getUserIdStr()));
        inform.setGroupId(informModel.getGroupId());
        inform.setMessage(informModel.getMessage());
        inform.setSendDate(new Date(informModel.getSendDate()));
        return inform;
    }


    /**
     * 查询数据库中的未读消息
     * @param uid
     * @param groupId
     * @return
     */
    public Vector<InformModel> queryNotReadInform(Integer uid, Integer groupId) {
        List<Inform> informs = informMapper.queryNotReadInform(uid, groupId);

        Vector<InformModel> v = new Vector<>();
        HashMap<Integer, UserModel> map = new HashMap<>();
        for (Inform inform : informs) {
            InformModel temp = informConvent(inform, map);
            v.add(temp);
        }
        return v;
    }

    /**
     *   inform转换为InformModel 对象， map为暂存InformModel 中 UserModel， 需外部传入
     * 用于列表转换时节约数据库开销
     * @param inform
     * @param map
     * @return
     */
    private InformModel informConvent(Inform inform, HashMap<Integer, UserModel> map){
        InformModel temp = new InformModel();
        temp.setId(inform.getId());
        temp.setGroupId(inform.getGroupId());
        temp.setNotRead(true);
        temp.setSendDate(inform.getSendDate().getTime());
        temp.setMessage(inform.getMessage());
        UserModel userModel = map.getOrDefault(inform.getSendUserId(), null);
        if(userModel == null){
            userModel = getUserModel(inform.getSendUserId());
            map.put(inform.getSendUserId(), userModel);
        }
        temp.setSendUser(userModel);
        return temp;
    }

    /**
     * 获取userModel对象
     * @param uid
     * @return
     */
    private UserModel getUserModel(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        UserModel userModel = new UserModel();
        userModel.setUsername(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        userModel.setUserIdStr(UserIDBase64.encoderUserID(uid));
        return userModel;
    }

    /**
     * 分页查询消息记录
     * @param informQuery
     * @return
     */
    public PageInfo<InformModel> pageQueryInformRecord(InformQuery informQuery) {
        List<Integer> list = groupMapper.queryMemberIdList(informQuery.getGroupId());
        if(list!=null && !list.contains(informQuery.getUserId())){
            AssertUtil.isTrue(true, "非小组成员无法查询消息记录");
        }

        PageHelper.startPage(informQuery.getPage(), informQuery.getLimit());
        List<Inform> all = informMapper.queryInformByGroupId(informQuery.getGroupId());
        PageInfo<Inform> informPageInfo = new PageInfo<>(all);

        HashMap<Integer, UserModel> map = new HashMap<>();
        List<InformModel> informModels = new ArrayList<>();
        for (Inform inform : all) {
            //填入inform中没有的数据
            InformModel informModel = informConvent(inform, map);
            informModel.setNotRead(false);
            informModels.add(informModel);
        }

        PageInfo<InformModel> info = cloneBasePageInfo(informPageInfo);
        info.setList(informModels);
        return info;
    }


    /**
     * 将PageInfo<Inform> 的所包含的分页信息复制到新创建的PageInfo<InformModel> 并返回
     * @param info
     * @return
     */
    private PageInfo<InformModel> cloneBasePageInfo(PageInfo<Inform> info){
        PageInfo<InformModel> res = new PageInfo<>();
        res.setEndRow(info.getEndRow());
        res.setHasNextPage(info.isHasNextPage());
        res.setHasPreviousPage(info.isHasPreviousPage());
        res.setIsFirstPage(info.isIsFirstPage());
        res.setIsLastPage(info.isIsLastPage());
        res.setNavigateFirstPage(info.getNavigateFirstPage());
        res.setNavigateLastPage(info.getNavigateLastPage());
        res.setNavigatePages(info.getNavigatePages());
        res.setNavigatepageNums(info.getNavigatepageNums());
        res.setNextPage(info.getNextPage());
        res.setPageNum(info.getPageNum());
        res.setPageSize(info.getPageSize());
        res.setPages(info.getPages());
        res.setPrePage(info.getPrePage());
        res.setSize(info.getSize());
        res.setStartRow(info.getStartRow());
        res.setTotal(info.getTotal());
        return res;
    }

    /**
     * 清除未读消息
     * @param userId
     * @param groupId
     * @param notReadList
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void clearNotRead(Integer userId, Integer groupId, List<Long> notReadList) {
        for (Long id : notReadList) {
            if(WebSocketServer.notReadMessage.get(userId).get(groupId) != null){
                //去除内存的
                WebSocketServer.notReadMessage.get(userId).get(groupId).remove(id);
            }
        }
        //清除数据库的
        informMapper.removeNotRead(userId,groupId);
    }
}
