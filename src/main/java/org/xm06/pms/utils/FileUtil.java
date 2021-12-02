package org.xm06.pms.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class FileUtil {
    /**
     * 获取文件名
     * @param url
     * @return
     */
    public static String getFileFromUrl(String url){

        String[] split = url.split("\\\\");
        return split[split.length-1];
    }

    /**
     *  文件存储的基础路径
     */
    private static String baseSavePath = "D:/webapps/teach/yjykfsj2021/XM06_upload";

    /**
     * 保存文件
     * @param file 文件
     * @param path
     * @return  返回保存路径
     * @throws IOException
     */
    public static String saveFile(MultipartFile file, String path) throws IOException {
        System.out.println(file.getContentType());

        if(file==null) return "";
        String fileName = file.getOriginalFilename();

        fileName = getSaveFileName(fileName);
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        f = new File(f, fileName);
        file.transferTo(f);
        return f.getAbsolutePath();
    }


    public static void createPath(String path){
        File file = new File(path);
        if(!file.exists()) file.exists();
    }
    /**
     * 获取保存时的文件名
     * @param fileName
     * @return
     */
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


    /**
     * 获取用户上传任务提交文件的文件夹
     * @param username
     * @return
     */
    public static String getUserUploadTaskFileSavePath(String username){
        return baseSavePath + "\\uploadfile\\"+ username +"\\task";
    }

    /**
     * 获取用户上传的提交记录的文件夹
     * @param username
     * @return
     */
    public static String getUserSubmitProjectRecordPath(String username, Integer projectId){
        return baseSavePath + "\\uploadfile\\"+ username +"\\project_"+projectId;
    }

    /**
     * 获取用户图片保存文件夹
     * @param username
     * @return
     */
    public static String getUserUploadFaceImgSavePath(String username){
        return  baseSavePath+"\\userface\\" + username + "\\faceimg";
    }

    /**
     * 获取用户人脸信息保存文件夹
     * @param username
     * @return
     */
    public static String getUserFaceYmlSavePath(String username){
        return baseSavePath+"\\userface\\"+ username + "\\model";
    }

    /**
     * 获取从视频中获取的用户人脸图片文件夹
     * @param username
     * @return
     */
    public static String getUserVideoFaceSavePath(String username){
        return baseSavePath+"\\userface\\"+username+"\\video\\frameimg";
    }

    /**
     * 获取保存用户上传视频的文件夹
     * @param username
     * @return
     */
    public static String getUserVideoMp4SavePath(String username){
        return baseSavePath+"\\userface\\"+username+"\\video\\mp4";
    }


    /**
     * 获取保存用户人脸登录时上传的图片暂存文件夹
     * @param username
     * @return
     */
    public static String getFaceLoginImgSavePath(String username){
        return baseSavePath+"\\userface\\"+username+"\\temp";
    }

    /**
     * 获取用户人脸信息根文件夹路径
     * @param username
     * @return
     */
    public static String getUserFacePath(String username){
        return baseSavePath+"\\userface\\"+username;
    }
    /**
     * 清除文件夹
     * @param file
     */
    public static void deleteFile(File file){
        if (file == null || !file.exists()){
            return;
        }
        File[] files = file.listFiles();
        if(files != null){
            for (File f: files){
                if (f.isDirectory()){
                    deleteFile(f);
                }else {
                    f.delete();
                }
            }
        }
        file.delete();
    }

    /**
     * base64转换为byte[]
     * @param base64
     * @return
     */
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
