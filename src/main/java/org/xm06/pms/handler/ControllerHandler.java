package org.xm06.pms.handler;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.xm06.pms.converter.DateConvert;

@ControllerAdvice
public class ControllerHandler {

    @InitBinder
    public void initBinder(WebDataBinder binder){
        GenericConversionService service = (GenericConversionService) binder.getConversionService();
        if (service != null){
            service.addConverter(new DateConvert());
        }
    }
}
