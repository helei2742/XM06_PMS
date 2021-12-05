package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.vo.Announce;

import java.util.List;

public interface AnnounceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Announce record);

    int insertSelective(Announce record);

    Announce selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Announce record);

    int updateByPrimaryKey(Announce record);

    List<Announce> queryAllAnnounce(Integer orderType);
    List<Announce> queryUserAnnounce(@Param("userId") Integer userId,@Param("orderType") Integer orderType);

    Integer deleteByIds(@Param("announceIds") List<Integer> announceIds,@Param("userId") Integer userId);
}