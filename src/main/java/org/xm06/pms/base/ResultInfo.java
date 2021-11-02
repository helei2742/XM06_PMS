package org.xm06.pms.base;

public class ResultInfo {
    private String msg;
    private int code;
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
