package org.xm06.pms.exception;

/**
 * 自定义参数异常
 */
public class ParamsException extends RuntimeException{
    private Integer code = 501;
    private String msg;

    public ParamsException(){
        super("参数异常");
    }

    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public ParamsException(Integer code) {
        super("参数异常");
        this.code = code;
    }
    public ParamsException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

