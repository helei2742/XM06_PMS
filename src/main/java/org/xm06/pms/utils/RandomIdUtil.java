package org.xm06.pms.utils;

import io.swagger.models.auth.In;

import java.util.Date;
import java.util.Random;

public class RandomIdUtil {
    private static Random random = new Random();
    public static Long getRandomId(){
        return new Date().getTime() + random.nextInt(100);
    }
    public static Long getRandomId(Integer num){
        return getRandomId() + random.nextInt(num);
    }
}
