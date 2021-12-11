package org.xm06.pms.base;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ResultInfo",description = "接口返回结果的包装类")
public class ResultInfo {
    @ApiModelProperty("响应消息")
    private String msg;
    @ApiModelProperty(value = "响应代码,值为200代表响应成功，其他为失败")
    private int code;
    @ApiModelProperty("响应数据")
    private Object result;

    public ResultInfo(String msg, int code, Object result) {
        this.msg = msg;
        this.code = code;
        this.result = result;
    }
    public ResultInfo(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
    public ResultInfo() {
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
