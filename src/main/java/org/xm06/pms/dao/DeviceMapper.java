package org.xm06.pms.dao;

import org.xm06.pms.vo.Device;

import java.util.List;

public interface DeviceMapper {

    /**
     * 根据所有条件查找设备
     * @param deviceId
     * @return
     */
    List<Device> queryDeviceByAll(String deviceId, String deviceName, Integer creatorId, Integer groupId);

    /**
     * 根据设备id查找设备
     * @param deviceId
     * @return
     */
    List<Device> queryDeviceById(String deviceId);

    /**
     * 根据设备名字查找任设备
     * @param deviceName
     * @return
     */
    List<Device> queryDeviceByName(String deviceName);

    /**
     * 根据创建人（购买人）查找设备
     * @param creatorId
     * @return
     */
    List<Device> queryDeviceByCreatorId(Integer creatorId);

    /**
     * 根据小组查找设备
     * @param groupId
     * @return
     */
    List<Device> queryDeviceByGroupId(Integer groupId);

    /**
     * 根据设备编号和名字查找设备
     * @param deviceId,deviceName
     * @return
     */
    List<Device> queryDeviceByIdAndName(String deviceId, String deviceName);

    /**
     * 根据设备编号和购买人（创建人）查找设备
     * @param deviceId,creatorId
     * @return
     */
    List<Device> queryDeviceByIdAndCreatorId(String deviceId, Integer creatorId);

    /**
     * 根据设备编号和小组查找设备
     * @param deviceId,creatorId
     * @return
     */
    List<Device> queryDeviceByIdAndGroupId(String deviceId, Integer groupId);

    /**
     * 根据名字和购买人（创建人）查找设备
     * @param deviceName,creatorId
     * @return
     */
    List<Device> queryDeviceByNameAndCreatorId(String deviceName, Integer creatorId);

    /**
     * 根据名字和小组查找设备
     * @param deviceName,groupId
     * @return
     */
    List<Device> queryDeviceByNameAndGroupId(String deviceName, Integer groupId);

    /**
     * 根据购买人（创建人）和小组查找设备
     * @param creatorId,groupId
     * @return
     */
    List<Device> queryDeviceByCreatorIdAndGroupId(Integer creatorId, Integer groupId);

    /**
     * 根据设备号、设备名字、购买人（创建人）查找设备
     * @param deviceId,deviceName,creatorId
     * @return
     */
    List<Device> queryDeviceByIdAndNameAndCreatorId(String deviceId, String deviceName, Integer creatorId);

    /**
     * 根据设备号、设备名字、小组查找设备
     * @param deviceId,deviceName,groupId
     * @return
     */
    List<Device> queryDeviceByIdAndNameAndGroupId(String deviceId, String deviceName, Integer groupId);

    /**
     * 根据设备号、购买人（创建人）、小组查找设备
     * @param deviceId,creatorId,groupId
     * @return
     */
    List<Device> queryDeviceByIdAndCreatorIdAndGroupId(String deviceId, Integer creatorId, Integer groupId);

    /**
     * 根据设备名字、购买人（创建人）、小组查找设备
     * @param deviceName,creatorId,groupId
     * @return
     */
    List<Device> queryDeviceByNameAndCreatorIdAndGroupId(String deviceName, Integer creatorId, Integer groupId);

    /**
     * 查询全部的设备
     * @return
     */
    List<Device> queryAllDevice();

    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}
