package org.xm06.pms.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginUtil {

    public static Integer releaseUserIdFromCookie(HttpServletRequest request){
        String userIdStr = CookieUtil.getCookie(request, "userIdStr");
        return releaseUserId(userIdStr);
    }

    public static Integer releaseUserId(String userIdStr) {
        if(StringUtils.isBlank(userIdStr)) {
            return 0;
        }
        return UserIDBase64.decoderUserID(userIdStr);
    }
}
