package org.xm06.pms.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xm06.pms.dao.DeviceMapper;
import org.xm06.pms.dao.GroupMapper;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.query.DeviceQuery;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.vo.Device;
import org.xm06.pms.vo.Group;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DeviceService {
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增设备方法
     * @param device
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addDevice(Device device){
        AssertUtil.isTrue(StringUtils.isBlank(device.getDeviceId()), "设备号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(device.getDeviceName()), "设备名不能为空");

        Group group = groupMapper.selectByPrimaryKey(device.getGroupId());
        AssertUtil.isTrue(group == null, "不存在该小组");

        AssertUtil.isTrue(device.getCreatorId() == null, "创建人id不能为空");

        User user = userMapper.selectByPrimaryKey(device.getCreatorId());
        AssertUtil.isTrue(user == null, "创建者不存在");

        device.setCreateDate(new Date());


        AssertUtil.isTrue(deviceMapper.insertSelective(device)<1,"新增设备失败");
    }

    /**
     * 删除设备方法
     * @param deviceId
     * @param userId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDevice(Integer deviceId, Integer userId) {
        AssertUtil.isTrue(deviceId == null, "找不到该设备，deviceId为空");
        AssertUtil.isTrue(userId == null, "userId为空");

        AssertUtil.isTrue(!(deviceMapper.selectByPrimaryKey(deviceId)).getCreatorId().equals(userId), "该操作者不能删除该设备");

        AssertUtil.isTrue(deviceMapper.deleteByPrimaryKey(deviceId)<=0, "删除设备失败");
    }

    /**
     * 修改设备信息方法
     * @param device
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyDevice(Device device, Integer userId){
        AssertUtil.isTrue(StringUtils.isBlank(device.getDeviceId()), "设备号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(device.getDeviceName()), "设备名不能为空");

        Group group = groupMapper.selectByPrimaryKey(device.getGroupId());
        AssertUtil.isTrue(group == null, "不存在该小组");

        AssertUtil.isTrue(userId == null, "修改人id不能为空");

        User user = userMapper.selectByPrimaryKey(device.getCreatorId());
        AssertUtil.isTrue(user == null, "修改人不存在");

        AssertUtil.isTrue(!user.getId().equals(userId), "该操作者不能修改该设备");

        device.setCreateDate(new Date());

        AssertUtil.isTrue(deviceMapper.updateByPrimaryKeySelective(device)<=0,"修改设备信息失败");
    }

    /**
     * 根据type 分类查询不同条件下的设备
     * @param deviceQuery
     * deviceQuery 中
     *      type 为 1、查询用户创建会议
     *             2.查询小组的会议
     *             3.查询还没开始的会议
     *             4，查询已经结束的会议
     *
     * @return
     */
    public List<Device> pageQueryAllDevice(DeviceQuery deviceQuery) {
        Integer type = deviceQuery.getType();

        AssertUtil.isTrue(type == null, "必须传入参数type");
        List<Device> all = null;

        if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYGROUPID) || type.equals(DeviceQuery.PAGEQUERYDEVICEBYIDANDGROUPID)
        || type.equals(DeviceQuery.PAGEQUERYDEVICEBYNAMEANDGROUPID) || type.equals(DeviceQuery.PAGEQUERYDEVICEBYUSERIDANDGROUPID)
        || type.equals(DeviceQuery.PAGEQUERYDEVICEBYIDANDNAMEANDGROUPID) || type.equals(DeviceQuery.PAGEQUERYDEVICEBYNAMEANDUSERIDANDGROUPID)
        || type.equals(DeviceQuery.PAGEQUERYDEVICEBYIDANDUSERIDANDGROUPID)){
            Group group = groupMapper.selectByPrimaryKey(deviceQuery.getGroupId());
            AssertUtil.isTrue(group == null, "小组不存在");
        }
        if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYID)){
            // 1表示使用设备ID查询设备
            all = deviceMapper.queryDeviceById(deviceQuery.getDeviceId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYNAME)){
            // 2表示使用设备名字查询设备
            all = deviceMapper.queryDeviceByName(deviceQuery.getDeviceName());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYUSERID)){
            // 3表示查询用户购买或创建的设备
            all = deviceMapper.queryDeviceByCreatorId(deviceQuery.getCreatorId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYGROUPID)){
            // 4表示查询小组的设备
            all = deviceMapper.queryDeviceByGroupId(deviceQuery.getGroupId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYIDANDNAME)){
            // 5表示使用设备ID和名字查询设备
            all = deviceMapper.queryDeviceByIdAndName(deviceQuery.getDeviceId(), deviceQuery.getDeviceName());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYIDANDCREATORID)){
            // 6表示使用设备ID和创建人ID查询设备
            all = deviceMapper.queryDeviceByIdAndCreatorId(deviceQuery.getDeviceId(), deviceQuery.getCreatorId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYIDANDGROUPID)){
            // 7表示使用设备ID和小组ID查询设备
            all = deviceMapper.queryDeviceByIdAndGroupId(deviceQuery.getDeviceId(), deviceQuery.getGroupId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYNAMEANDUSERID)){
            // 8表示使用设备名字和创建人ID查询设备
            all = deviceMapper.queryDeviceByNameAndCreatorId(deviceQuery.getDeviceName(), deviceQuery.getCreatorId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYNAMEANDGROUPID)){
            // 9表示使用设备名字和小组ID查询设备
            all = deviceMapper.queryDeviceByNameAndGroupId(deviceQuery.getDeviceName(), deviceQuery.getGroupId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYUSERIDANDGROUPID)){
            // 10表示使用创建人ID和小组ID查询设备
            all = deviceMapper.queryDeviceByCreatorIdAndGroupId(deviceQuery.getCreatorId(), deviceQuery.getGroupId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYIDANDNAMEANDUSERID)){
            // 11表示使用设备ID、设备名字和创建人ID查询设备
            all = deviceMapper.queryDeviceByIdAndNameAndCreatorId(deviceQuery.getDeviceId(), deviceQuery.getDeviceName(), deviceQuery.getCreatorId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYIDANDNAMEANDGROUPID)){
            // 12表示使用设备ID、设备名字和小组ID查询设备
            all = deviceMapper.queryDeviceByIdAndNameAndGroupId(deviceQuery.getDeviceId(), deviceQuery.getDeviceName(), deviceQuery.getGroupId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYIDANDUSERIDANDGROUPID)){
            // 13表示使用设备ID、创建人ID和小组ID查询设备
            all = deviceMapper.queryDeviceByIdAndCreatorIdAndGroupId(deviceQuery.getDeviceId(), deviceQuery.getCreatorId(), deviceQuery.getGroupId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYNAMEANDUSERIDANDGROUPID)){
            // 14表示使用设备名字、创建人ID和小组ID查询设备
            all = deviceMapper.queryDeviceByNameAndCreatorIdAndGroupId(deviceQuery.getDeviceName(), deviceQuery.getCreatorId(), deviceQuery.getGroupId());
        } else if(type.equals(DeviceQuery.PAGEQUERYDEVICEBYALL)){
            // 15表示使用全部条件查询设备
            all = deviceMapper.queryDeviceByAll(deviceQuery.getDeviceId(), deviceQuery.getDeviceName(), deviceQuery.getCreatorId(), deviceQuery.getGroupId());
        } else {
            // 查询全部设备记录
            all = deviceMapper.queryAllDevice();
        }

        return all;
    }
}
