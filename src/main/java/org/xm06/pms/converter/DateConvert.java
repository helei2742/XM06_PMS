package org.xm06.pms.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        Date data = null;
        if(s!=null && !"".equals(s)){
            data =  new Date(Long.parseLong(s));
        }
        return data;
    }
}
