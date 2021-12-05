package org.xm06.pms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xm06.pms.dao.AnnounceMapper;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.query.AnnounceQuery;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.vo.Announce;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AnnounceService {

    @Resource
    AnnounceMapper announceMapper;
    @Resource
    UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void createAnnounce(Announce announce) {
        Integer userId = announce.getUserId();
        AssertUtil.isTrue(userId==null,"需传入userId");
        AssertUtil.isTrue(announce.getTitle().length()<1, "标题长度需大于1");
        AssertUtil.isTrue(announce.getMainBody().length()<1, "正文长度需大于1");

        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(user == null, "不存在创建者用户或已失效");

        announce.setCreateDate(new Date());
        announce.setUpdateDate(new Date());
        announce.setIsValid(true);
        AssertUtil.isTrue(announceMapper.insert(announce)<1,"发布公告失败");
    }

    public PageInfo<Announce> pageQueryAnnounce(AnnounceQuery announceQuery) {

        Integer queryType = announceQuery.getQueryType();
        Integer orderType = announceQuery.getOrderType();
        List<Announce> all = null;
        PageHelper.startPage(announceQuery.getPage(),announceQuery.getLimit());

        if(queryType.equals(AnnounceQuery.QUERYALLANNOUNCE)){
            all = announceMapper.queryAllAnnounce(orderType);
        }else if (queryType.equals(AnnounceQuery.QUERYMYANNOUNCE)){
            all = announceMapper.queryUserAnnounce(announceQuery.getUserId(), orderType);
        }else{
            AssertUtil.isTrue(true, "没有该类型的查询");
        }
        return new PageInfo<>(all);
    }

    /**
     * 根据id查询公告
     * @param announceId
     * @return
     */
    public Announce queryAnnounceById(Integer announceId) {
        return announceMapper.selectByPrimaryKey(announceId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void alterAnnounce(Announce announce) {
        Integer announceId = announce.getId();
        Announce inDB = announceMapper.selectByPrimaryKey(announceId);
        AssertUtil.isTrue(inDB==null, "不存在该公告或已失效");
        AssertUtil.isTrue(!announce.getUserId().equals(inDB.getUserId()), "无权修改公告信息");

        AssertUtil.isTrue(announceMapper.updateByPrimaryKey(announce)<1,"修改信息失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAnnounce(Announce announce) {
        Integer announceId = announce.getId();
        Announce inDB = announceMapper.selectByPrimaryKey(announceId);
        AssertUtil.isTrue(inDB==null, "不存在该公告或已失效");
        AssertUtil.isTrue(!announce.getUserId().equals(inDB.getUserId()), "无权删除公告");

        AssertUtil.isTrue(announceMapper.deleteByPrimaryKey(announceId)<1, "删除公告失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSelectedAnnounce(List<Integer> announceIds, Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(user==null, "不存在该用户");

        announceMapper.deleteByIds(announceIds,userId);
    }
}
