package org.xm06.pms.dao;

import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.User;

public interface UserMapper extends BaseMapper<User, Integer> {

    Integer queryIdByUsername(String username);
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据主键查找修改用户信息，不修改密码
     * @param user
     * @return
     */
    Integer updateUserNoPwd(User user);

    /**
     * 根据主键查找用户修改密码
     * @param user
     * @return
     */
    Integer alterPassword(User user);

    Integer confirm(Integer userId);
}