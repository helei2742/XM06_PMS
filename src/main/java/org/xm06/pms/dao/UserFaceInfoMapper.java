package org.xm06.pms.dao;

import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.UserFaceInfo;

public interface UserFaceInfoMapper extends BaseMapper<UserFaceInfo, Integer> {

    UserFaceInfo queryByUserId(Integer userId);
}