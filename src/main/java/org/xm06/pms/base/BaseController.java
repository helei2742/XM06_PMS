package org.xm06.pms.base;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @ModelAttribute
    public void preHandler(HttpServletRequest request){
        request.setAttribute("ctx", request.getContextPath());
    }

    public ResultInfo success() {
        return new ResultInfo();
    }

    public ResultInfo success(String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }
    public ResultInfo success(String msg, Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }
    public ResultInfo success(String msg, int code, Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        resultInfo.setCode(code);
        return resultInfo;
    }
}
