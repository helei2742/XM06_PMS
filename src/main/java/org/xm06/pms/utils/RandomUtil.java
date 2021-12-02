package org.xm06.pms.utils;

import io.swagger.models.auth.In;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();
    public static Long getRandomId(){
        return new Date().getTime() + random.nextInt(100);
    }
    public static Long getRandomId(Integer num){
        return getRandomId() + random.nextInt(num);
    }

    private static final Random RANDOM = new SecureRandom();
    private static final String SYMBOLS = "0123456789";
    public static String generateVerCode() {
        char[] nonceChars = new char[6];
        for (int i = 0; i < nonceChars.length; i++) {
            nonceChars[i] = SYMBOLS.charAt(RANDOM.nextInt(nonceChars.length));
        }
        return new String(nonceChars);
    }
}
