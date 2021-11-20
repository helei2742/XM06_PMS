package org.xm06.pms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.xm06.pms.base.BaseQuery;


@ApiModel(value = "ProjectRecordQuery",description = "项目进度更新记录查询参数的封装类")
public class ProjectRecordQuery extends BaseQuery {

    @ApiModelProperty("项目id")
    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
