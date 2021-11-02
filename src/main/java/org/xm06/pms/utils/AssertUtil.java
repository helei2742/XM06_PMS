package org.xm06.pms.utils;


import org.xm06.pms.exception.ParamsException;


public class AssertUtil {
    /**
     * 进行校验并抛出参数异常
     * @param flag
     * @param msg
     */
    public static void isTrue(boolean flag, String msg) {
        if(flag) {
            throw new ParamsException(msg);
        }
    }
}
