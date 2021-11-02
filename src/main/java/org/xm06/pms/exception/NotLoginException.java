package org.xm06.pms.exception;

/**
 * 未登录异常
 */
public class NotLoginException extends RuntimeException{
    private Integer code = 502;
    private String msg = "用户未登录！";
    public NotLoginException(){
        super("用户未登录！");
    }
    public NotLoginException(String msg){
        super(msg);
        this.msg = msg;
    }
    public NotLoginException(Integer code){
        super("用户未登录！");
        this.code = code;
    }
    public NotLoginException(Integer code, String msg){
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
