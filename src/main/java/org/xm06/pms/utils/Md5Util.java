package org.xm06.pms.utils;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.xm06.pms.vo.Conference;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

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

    public static String getInvitationCode(Integer groupId, Integer managerId){
        return encode(groupId + "id"+managerId);
    }

/*    *//**
     * 根据项目名称和小组id查询，，
     * （主要用于判断小组是否创建了具有相同项目名的项目）
     * @param conferenceName
     * @param creatorId
     * @return
     */
}
