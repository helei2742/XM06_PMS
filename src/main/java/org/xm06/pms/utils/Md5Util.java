package org.xm06.pms.utils;

import io.swagger.models.auth.In;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Md5Util {
    public static String encode(String msg){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            return Base64.getEncoder().encodeToString(messageDigest.digest(msg.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getInvitationCode(String groupName, Integer managerId){
        return encode(groupName + managerId);
    }

}
