package org.xm06.pms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.query.DeviceQuery;
import org.xm06.pms.service.DeviceService;
import org.xm06.pms.vo.Device;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/device")
@Api(value = "DeviceController", tags = "设备管理模块接口")
public class DeviceController  extends BaseController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping(value = "/pageQueryAllDevice", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo device_index(@RequestBody @Valid DeviceQuery deviceQuery) {
        List<Device> devices = deviceService.pageQueryAllDevice(deviceQuery);
        return success("查询设备成功", 200, devices);
    }

    @PostMapping(value = "/add", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "新建设备接口")
    public ResultInfo addDevice(@RequestBody @Valid Device device) {
        // System.out.println(device);
        deviceService.addDevice(device);
        return success("新建设备成功", 200, null);
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiModelProperty(value = "删除设备记录")
    public ResultInfo deleteDevice(@RequestBody @Valid Device device) {
        System.out.println(device.getUserId());
        deviceService.deleteDevice(device.getId(), device.getUserId());
        return success("删除设备成功", 200, null);
    }
    @PostMapping(value = "/modify", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiModelProperty(value = "修改设备记录")
    public ResultInfo modifyDevice(@RequestBody @Valid Device device) {
        deviceService.modifyDevice(device, device.getCreatorId());
        return success("修改设备信息成功", 200, null);
    }

}
