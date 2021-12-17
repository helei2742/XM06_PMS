package org.xm06.pms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.xm06.pms.base.BaseQuery;

@ApiModel(value = "DeviceQuery",description = "设备查询参数封装类")
public class DeviceQuery extends BaseQuery {

    /*
     * 查询条件
     */
    public static final Integer PAGEQUERYDEVICEBYID = 1;
    public static final Integer PAGEQUERYDEVICEBYNAME = 2;
    public static final Integer PAGEQUERYDEVICEBYUSERID = 3;
    public static final Integer PAGEQUERYDEVICEBYGROUPID = 4;
    public static final Integer PAGEQUERYDEVICEBYIDANDNAME = 5;
    public static final Integer PAGEQUERYDEVICEBYIDANDCREATORID = 6;
    public static final Integer PAGEQUERYDEVICEBYIDANDGROUPID = 7;
    public static final Integer PAGEQUERYDEVICEBYNAMEANDUSERID = 8;
    public static final Integer PAGEQUERYDEVICEBYNAMEANDGROUPID = 9;
    public static final Integer PAGEQUERYDEVICEBYUSERIDANDGROUPID = 10;
    public static final Integer PAGEQUERYDEVICEBYIDANDNAMEANDUSERID = 11;
    public static final Integer PAGEQUERYDEVICEBYIDANDNAMEANDGROUPID = 12;
    public static final Integer PAGEQUERYDEVICEBYIDANDUSERIDANDGROUPID = 13;
    public static final Integer PAGEQUERYDEVICEBYNAMEANDUSERIDANDGROUPID = 14;
    public static final Integer PAGEQUERYDEVICEBYALL = 15;

    @ApiModelProperty("设备ID")
    private String deviceId;

    @ApiModelProperty("设备名字")
    private String deviceName;

    @ApiModelProperty("小组id")
    private Integer groupId;

    @ApiModelProperty("创建者id")
    private Integer creatorId;

    @ApiModelProperty("设备id")
    public String getDeviceId() {
        return deviceId;
    }

    @ApiModelProperty(value = "查询类型")
    private Integer type;

    /************************************************外部操作接口*****************************************************/
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
