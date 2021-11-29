package org.xm06.pms.service;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xm06.pms.dao.UserFaceInfoMapper;
import org.xm06.pms.model.FaceUploadModel;
import org.xm06.pms.model.UserModel;
import org.xm06.pms.utils.*;
import org.xm06.pms.vo.User;
import org.xm06.pms.vo.UserFaceInfo;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FaceService {
//
//
//    private final static String faceDetectorXML2URL =
//            "D:\\ideaWorkSpace\\webhomework\\pms\\src\\main\\resources\\haarcascade_frontalface_alt.xml";
//    private static CascadeClassifier faceCascade;
//    static {
//        // 解决awt报错问题
//        System.setProperty("java.awt.headless", "false");
//        System.out.println(System.getProperty("java.library.path"));
//        // 加载动态库
//        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java3416.dll");
//        System.load(url.getPath());
//        faceCascade = new CascadeClassifier(faceDetectorXML2URL);
//    }
//
//    @Resource
//    UserService userService;
//
//    @Resource
//    UserFaceInfoMapper userFaceInfoMapper;
//
//
//    /**
//     * 获取用户上传图片的base64码， 将其转换为图片后进行训练
//     * @param faceUploadModel
//     * @throws IOException
//     */
//    public void trainAndSaveFaceInfo(FaceUploadModel faceUploadModel) throws IOException {
//
//        AssertUtil.isTrue(faceUploadModel.getUserId()==null, "未获取到用户id");
//        User user = userService.selectByPrimaryKey(faceUploadModel.getUserId());
//        AssertUtil.isTrue(user==null,
//                "系统不存在该用户或已失效");
//
//        UserFaceInfo userFaceInfo = userFaceInfoMapper.queryByUserId(faceUploadModel.getUserId());
//        AssertUtil.isTrue(userFaceInfo!=null&&!userFaceInfo.getIsValid(),
//                "用户人脸信息正在注销中，请前往邮箱确认");
//
//
//        List<Mat> faces = getFaceMatListFromJson(faceUploadModel.getFacesJson());
//        AssertUtil.isTrue(faces.size() < FaceUtil.MINNEEDFACECOUNT, "采集人脸数量不足，请重新采集");
//
//        String username = user.getUserName();
//
//        String faceImgSavePath = FileUtil.getUserUploadFaceImgSavePath(username);
//        createPath(faceImgSavePath);
//
//        int c = 0;
//        for (Mat face : faces) {
//            Imgcodecs.imwrite(faceImgSavePath +"\\img_"+username+"_"+ c++ +".jpg", face);
//        }
//
//        String ymlSavePath = FileUtil.getUserFaceYmlSavePath(username);
//        createPath(ymlSavePath);
//
//        String trainPath = FaceUtil.train(faceImgSavePath, ymlSavePath, username);
//
//        this.faceInfoSaveInDatabase(trainPath, faceImgSavePath ,user.getId(),userFaceInfo);
//    }
//
//
//    /**
//     * 从json 字符串中获取图片mat， 字符串为图片base64编码
//     * @param facesJson
//     * @return Mat,  灰度化的人脸
//     */
//    private List<Mat> getFaceMatListFromJson(String facesJson){
//        JSONArray array = JSONArray.parseArray(facesJson);
//        ArrayList<Mat> faces = new ArrayList<>();
//        for (Object o : array) {
//            byte[] bytes = FileUtil.base64ToByte(o.toString());
//
//            Mat uploadImg = FaceUtil.byteToMat(bytes);
//            Mat grayFace = FaceUtil.conv_Mat_gray(uploadImg, faceCascade);
//            if(grayFace != null) {
//                faces.add(grayFace);
//            }
//        }
//        return faces;
//    }
//
//    /**
//     * 保存人脸信息到数据库
//     * @param ymlSrc            训练文件路径
//     * @param imgFolderPath     存放图片文件夹路径
//     * @param userId            用户id
//     * @param faceInfo            数据库查出的用户人脸信息对象
//     */
//    @Transactional(propagation = Propagation.REQUIRED)
//    public  void faceInfoSaveInDatabase(String ymlSrc, String imgFolderPath,Integer userId ,UserFaceInfo faceInfo) {
//
//        if(faceInfo == null){
//            //没有注册过人脸信息
//            faceInfo = new UserFaceInfo();
//            faceInfo.setFaceYmlSrc(ymlSrc);
//            faceInfo.setFaceImgFolderSrc(imgFolderPath);
//            faceInfo.setIsValid(true);
//            faceInfo.setCreateDate(new Date());
//            faceInfo.setUserId(userId);
//            faceInfo.setUpdateDate(new Date());
//            userFaceInfoMapper.insertSelective(faceInfo);
//        }else  {
//            //注册过人脸信息
//            faceInfo.setFaceYmlSrc(ymlSrc);
//            faceInfo.setUpdateDate(new Date());
//            faceInfo.setFaceImgFolderSrc(imgFolderPath);
//            userFaceInfoMapper.updateByPrimaryKeySelective(faceInfo);
//        }
//    }
//
//
//    /**
//     * 如果没有路径，则创建该路径
//     * @param path
//     */
//    private void createPath(String path){
//        File file = new File(path);
//        if(!file.exists()) {
//            file.mkdirs();
//        }
//    }
//
//    /**
//     * 保存上传的用于人脸识别的文件
//     * @param userId
//     * @param file
//     * @throws IOException
//     */
//    public String saveUploadFaceMp4(Integer userId, MultipartFile file) throws IOException {
//        AssertUtil.isTrue(!"video/mp4".equals(file.getContentType()), "上传格式不正确，请上传mp4");
//
//        User user = userService.selectByPrimaryKey(userId);
//
//        AssertUtil.isTrue(user == null, "不存在该用户或用户已失效");
//
//        String path = FileUtil.getUserVideoMp4SavePath(user.getUserName());
//
//        File f = new File(path);
//        if(f.exists()){
//            File[] files = f.listFiles();
//            if(files != null)
//            for (File file1 : files) {
//                file1.delete();
//            }
//        }
//        String savePath = FileUtil.saveFile(file, path);
//
//        return savePath;
//    }
//
//
//    /**
//     * 根据视频进行训练
//     * @param userId
//     * @param savePath
//     * @throws IOException
//     */
//    public void videoTran(Integer userId, String savePath) throws IOException {
//        User user = userService.selectByPrimaryKey(userId);
//        AssertUtil.isTrue(user == null, "不存在该用户或用户已失效");
//        AssertUtil.isTrue(StringUtils.isBlank(savePath), "路径不能含空");
//
//        File file = new File(savePath);
//        AssertUtil.isTrue(!file.exists(), "该路径文件不存在");
//
//        String username = user.getUserName();
//        String path = FileUtil.getUserVideoFaceSavePath(username);//
//        createPath(path);
//
//        FaceUtil.readImgFromVideo(savePath, path, username,faceCascade);
//
//        String ymlSavePath = FileUtil.getUserFaceYmlSavePath(username);
//
//        FaceUtil.train(path, ymlSavePath, username);
//    }
//
//    /**
//     * 人脸校验
//     * @param faceUploadModel
//     */
//    public UserModel checkUserFace(FaceUploadModel faceUploadModel) {
//
//        User user = userService.queryByUsername(faceUploadModel.getUsername());
//        AssertUtil.isTrue(user == null, "该用户不存在或已失效");
//
//        UserFaceInfo userFaceInfo = userFaceInfoMapper.queryByUserId(user.getId());
//        AssertUtil.isTrue(userFaceInfo == null, "尚未上传过人脸信息，请使用账号密码登录");
//        AssertUtil.isTrue(!userFaceInfo.getIsValid(), "用户人脸信息正在注销中，请前往邮箱确认");
//
//        //获取图片mat
//        List<Mat> faces = this.getFaceMatListFromJson(faceUploadModel.getFacesJson());
//
//        //创建该用户训练模型识别对象
//        FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
//        faceRecognizer.read(userFaceInfo.getFaceYmlSrc());
//
//        //识别
//        int c = 0;
////        int x = 0;
//        for (Mat face : faces) {
//            boolean f = FaceUtil.faceRecognize_getBoolean(face, faceCascade, faceRecognizer);
////            Imgcodecs.imwrite("D:\\test\\helei5\\helie"+x+++".jpg", face);
//            if(f) {
//                c++;
//            }
//        }
//
//        System.out.println("识别出"+c+"--"+c*1.0/faces.size());
//
//        AssertUtil.isTrue(c*1.0/faces.size() <= 0.6, "身份验证失败，请重试");
//
//        return getUserModel(user);
//    }
//
//    public UserModel getUserModel(User user) {
//        UserModel userModel = new UserModel();
//        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
//        userModel.setUsername(user.getUserName());
//        userModel.setTrueName(user.getTrueName());
//        return userModel;
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void setValid(Integer userId) {
//        User user = userService.selectByPrimaryKey(userId);
//        AssertUtil.isTrue(user == null, "不存在该用户");
//
//        UserFaceInfo faceInfo = userFaceInfoMapper.queryByUserId(userId);
//        AssertUtil.isTrue(faceInfo == null, "用户尚未注册过人脸信息");
//        AssertUtil.isTrue(!faceInfo.getIsValid(), "人脸信息已在注销中，请前往邮箱确认");
//
//        UserFaceInfo uf = new UserFaceInfo();
//        uf.setId(faceInfo.getId());
//        uf.setIsValid(false);
//        AssertUtil.isTrue(userFaceInfoMapper.updateByPrimaryKeySelective(uf)<1,"注销人脸信息失败");
//
//
//        sendConfirmMail(user.getId(), user.getEmail());
//    }
//
//
//    /**
//     * 清除用户的人脸信息，包括训练文件，图片，数据库记录
//     * @param userId
//     */
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void dropUserFaceInfo(Integer userId) {
//        User user = userService.selectByPrimaryKey(userId);
//        AssertUtil.isTrue(user==null, "不存在该用户");
//        UserFaceInfo faceInfo = userFaceInfoMapper.queryByUserId(userId);
//        AssertUtil.isTrue(faceInfo.getIsValid(), "必须在界面点击删除");
//
//        String facePath = FileUtil.getUserFacePath(user.getUserName());
//        FileUtil.deleteFile(new File(facePath));
//
//        AssertUtil.isTrue(userFaceInfoMapper.deleteByPrimaryKey(faceInfo.getId())<1,"删除失败");
//    }
//
//
//    /**
//     * 取消人脸信息注销
//     * @param userId
//     */
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void dropUserFaceInfoCancel(Integer userId) {
//        User user = userService.selectByPrimaryKey(userId);
//        AssertUtil.isTrue(user == null, "不存在该用户");
//        UserFaceInfo info = userFaceInfoMapper.queryByUserId(userId);
//        if(info !=null) {
//            info.setIsValid(true);
//            info.setUpdateDate(null);
//            info.setCreateDate(null);
//            info.setFaceImgFolderSrc(null);
//            info.setFaceYmlSrc(null);
//            info.setUserId(null);
//            userFaceInfoMapper.updateByPrimaryKeySelective(info);
//        }
//    }
//
//    @Autowired
//    private MailService mailService;
//    @Autowired
//    private TemplateEngine templateEngine;
//
//    private void sendConfirmMail(Integer id, String email){
//        Context context = new Context();
//        context.setVariable("id", id);
//        String emailContent = templateEngine.process("faceInfoDropConfirm", context);
//        mailService.sendHtmlMail(email, "人脸信息注销确认", emailContent);
//    }

}
