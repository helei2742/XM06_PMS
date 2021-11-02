package org.xm06.pms.dao;

import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.Memo;

import java.util.List;

public interface MemoMapper extends BaseMapper<Memo, Integer> {

    /**
     * 根据用户id查询用户所有的便签
     * @param userId
     * @return
     */
    List<Memo> queryUserMemos(Integer userId);

    /**
     * 将该便签更改为完成状态
     * @param memoId
     * @return
     */
    Integer confirmMemo(Integer memoId);
}