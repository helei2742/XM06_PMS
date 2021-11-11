package org.xm06.pms.service;

import com.alibaba.fastjson.JSONArray;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Service;
import org.xm06.pms.model.FaceUploadModel;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.utils.FaceUtil;
import org.xm06.pms.utils.FileUtil;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

@Service
public class FaceService {
    private final static String faceDetectorXML2URL =
            "D:\\opencv\\install\\etc\\haarcascades\\haarcascade_frontalface_alt.xml";
    private static CascadeClassifier faceCascade;
    static {
        // 解决awt报错问题
        System.setProperty("java.awt.headless", "false");
        System.out.println(System.getProperty("java.library.path"));
        // 加载动态库
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java454.dll");
        System.load(url.getPath());
        faceCascade = new CascadeClassifier(faceDetectorXML2URL);
    }

    @Resource
    UserService userService;
    public void trainAndSaveFaceInfo(FaceUploadModel faceUploadModel) throws IOException {
        AssertUtil.isTrue(faceUploadModel.getUserId()==null, "未获取到用户id");
        User user = userService.selectByPrimaryKey(faceUploadModel.getUserId());
        AssertUtil.isTrue(user==null,
                "系统不存在该用户或已失效");

        JSONArray array = JSONArray.parseArray(faceUploadModel.getFacesJson());
        ArrayList<Mat> faces = new ArrayList<>();
        for (Object o : array) {
            byte[] bytes = FileUtil.base64ToByte(o.toString());
            Mat uploadImg = FaceUtil.byteToMat(bytes);
            Mat convMat = FaceUtil.conv_Mat(uploadImg, faceCascade);
            if(convMat != null) {
                faces.add(convMat);
            }
        }
        AssertUtil.isTrue(faces.size() < 50, "采集人脸数量不足，请重新采集");

        String username = user.getUserName();
        String path = FileUtil.getUserFaceImgSavePath(username);
        createPath(path);
        int c = 0;
        for (Mat face : faces) {
            Imgcodecs.imwrite(getSaveFilePath(path, username, c++), face);
        }

        String ymlSavePath = FileUtil.getUserFaceYmlSavePath(username);
        createPath(ymlSavePath);
        FaceUtil.train(path, ymlSavePath, username);
    }

    private void createPath(String path){
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }
    private String getSaveFilePath(String path,String username, int c){
        return path + "\\face_" + username + "_"+ c +".jpg";
    }
}
