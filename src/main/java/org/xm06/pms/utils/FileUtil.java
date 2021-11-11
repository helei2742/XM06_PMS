package org.xm06.pms.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class FileUtil {
    public static String getFileFromUrl(String url){

        String[] split = url.split("\\\\");
        return split[split.length-1];
    }

    public static String getFileName(String agent, String filename) throws UnsupportedEncodingException {

        if (agent.contains("MSIE")) {

            // IE浏览器

            filename = URLEncoder.encode(filename, "utf-8");

            filename = filename.replace("+", " ");

        } else if (agent.contains("Firefox")) {

            // 火狐浏览器

            Base64.Encoder encoder = Base64.getEncoder();

            String str = encoder.encodeToString(filename.getBytes("utf-8"));

            filename = "=?utf-8?B?" + str + "?=";

//            BASE64Encoder base64 = new BASE64Encoder();

//            filename = "=?utf-8?B?" + base64.encode(filename.getBytes("utf-8")) + "?=";

        } else {

            // 其它浏览器

            filename = URLEncoder.encode(filename, "utf-8");

        }

        return filename;

    }


    public static String saveFile(MultipartFile file, String userIdStr) throws IOException {
        System.out.println(file.getContentType());

        if(file==null) return "";
        String fileName = file.getOriginalFilename();
        fileName = getSaveFileName(fileName);
        File f = new File("D:/yjykfsj2021_xw_XM06/" + userIdStr);
        if(!f.exists()){
            f.mkdirs();
        }
        f = new File(f, fileName);
        file.transferTo(f);
        return f.getAbsolutePath();
    }

    public static String getSaveFileName(String fileName){
        String[] split = fileName.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            sb.append(".").append(split[i]);
        }
        return split[0] +
                new Date().getTime()+new Random().nextInt(99)
                + sb.toString();
    }

    public static String getUserFaceImgSavePath(String username){
        return  "D:\\userface\\"+Md5Util.encode(username) + "\\face";
    }
    public static String getUserFaceYmlSavePath(String username){
        return "D:\\userface\\"+Md5Util.encode(username) + "\\model";
    }

    public static String getPrefix(String fileName){
        return fileName.split("\\.")[0];
    }
    public static String getSuffix(String fileName){
        String[] split = fileName.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            sb.append(".").append(split[i]);
        }
        return sb.toString();
    }

    public static byte[] base64ToByte(String base64){
        if (base64 == null) // 图像数据为空
            return null;
        base64 = base64.split("base64,")[1];
        return Base64.getDecoder().decode(base64);
    }

    public static boolean generateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        imgData = imgData.split("base64,")[1];
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = Base64.getDecoder().decode(imgData);

            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(out !=null){
                out.flush();
                out.close();
            }
        }
        return true;
    }
}
