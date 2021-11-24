package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.vo.NotReadInform;

public interface NotReadInformMapper {
    Integer insertObject(NotReadInform record);

    Integer insertSelective(NotReadInform record);

    Integer insert(@Param("userId") Integer userId,
                   @Param("groupId") Integer groupId,
                   @Param("informId") Long informId);
}