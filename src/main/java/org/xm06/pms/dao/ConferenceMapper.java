package org.xm06.pms.dao;

import org.xm06.pms.vo.Conference;

public interface ConferenceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Conference record);

    int insertSelective(Conference record);

    Conference selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Conference record);

    int updateByPrimaryKey(Conference record);
}