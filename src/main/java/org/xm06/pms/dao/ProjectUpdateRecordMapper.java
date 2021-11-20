package org.xm06.pms.dao;

import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.ProjectUpdateRecord;

import java.util.List;

public interface ProjectUpdateRecordMapper extends BaseMapper<ProjectUpdateRecord, Integer> {

    /**
     * 查提交记录，默认时间降序
     * @param projectId
     * @return
     */
    List<ProjectUpdateRecord> queryUpdateRecord(Integer projectId);
}