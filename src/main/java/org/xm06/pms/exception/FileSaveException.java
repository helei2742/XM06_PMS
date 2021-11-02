package org.xm06.pms.exception;

public class FileSaveException extends RuntimeException{
    private Integer code = 503;
    private String msg = "文件保存失败！";
    public FileSaveException(){
        super("文件保存失败！");
    }
    public FileSaveException(String msg){
        super(msg);
        this.msg = msg;
    }
    public FileSaveException(Integer code){
        super("文件保存失败！");
        this.code = code;
    }
    public FileSaveException(Integer code, String msg){
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
