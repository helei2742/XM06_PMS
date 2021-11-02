package org.xm06.pms.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.exception.NotLoginException;
import org.xm06.pms.exception.ParamsException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception e) {

        e.printStackTrace();

        //为登录异常，直接返回登录视图
        if(e instanceof NotLoginException){
            ModelAndView mv = new ModelAndView("redirect:/index");
            return mv;
        }

        //设置默认错误视图
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("code", 400);
        mv.addObject("msg", "系统异常，请稍后重试");

        if(handler instanceof HandlerMethod){

            //获取方法上的ResponseBody注解
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);

            //没有该注解，说明返回视图
            if(null == responseBody){
                //参数异常
                if(e instanceof ParamsException) {
                    ParamsException pe = (ParamsException) e;
                    mv.addObject("code", pe.getCode());
                    try {
                        mv.addObject("msg", pe.getMsg());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    return mv;
                }
            }else {
                //有注解，返回JSON字符串，参数异常
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统异常，请稍后重试");
                if(e instanceof ParamsException) {
                    ParamsException pe = (ParamsException) e;

                    try {
                        resultInfo.setMsg(URLEncoder.encode(pe.getMsg(), "UTF-8"));
                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                        unsupportedEncodingException.printStackTrace();
                    }
                    resultInfo.setCode(pe.getCode());

                    //设置格式
                    response.setContentType("application/json;charset=utf-8");

                    //由于controller层也会返回数据，调用response.getWriter,
                    // 所以这里使用response.getOutputStream()避免报错
                    ServletOutputStream out = null;
                    OutputStreamWriter ow = null;
                    try{
                        out = response.getOutputStream();
                        ow = new OutputStreamWriter(out);
                        ow.append(JSON.toJSONString(resultInfo));
                        ow.flush();
                    } catch (IOException ioException) {

                        ioException.printStackTrace();
                    }finally {
                        try {
                            if (out != null) {
                                out.close();
                            }
                            if (ow != null)
                                ow.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    return null;
                }
            }
        }

        return null;
    }
}
