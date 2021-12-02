package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.service.InformService;
import org.xm06.pms.vo.Inform;

import java.util.List;

public interface InformMapper extends BaseMapper<Inform, Integer> {

    List<Inform> queryNotReadInform(@Param("userId") Integer uid,@Param("groupId") Integer groupId);

    Integer removeNotRead(@Param("userId") Integer uid,@Param("groupId") Integer groupId);

    List<Inform> queryInformByGroupId(Integer groupId);

    /**
     * 移除数据库中小组的消息
     * @param groupId
     * @return
     */
    Integer removeInformOfGroup(Integer groupId);
}