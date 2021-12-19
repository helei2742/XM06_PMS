###
```
    人脸模块在 
        src/main/java/org/xm06/pms/controller/FaceController
        src/main/java/org/xm06/pms/service/FaceService
        src/main/java/org/xm06/pms/dao/UserFaceInfoMapper
        src/main/java/org/xm06/pms/util/FaceUtil
       
    使用时需更改FaceService下
private final static String faceDetectorXML2URL =
            "D:\\ideaWorkSpace\\webhomework\\pms\\src\\main\\resources\\haarcascade_frontalface_alt.xml";
    路径指到本机的haarcascade_frontalface_alt.xml 文件路径
   
    在 src/main/java/org/xm06/pms/util/FileUtil中可更改人脸图片等信息的基本路径
    private static String baseSavePath = "D:/webapps/teach/yjykfsj2021/XM06_upload";
    

    用户相关代码
            src/main/java/org/xm06/pms/controller/UserController
            src/main/java/org/xm06/pms/service/UserService
            src/main/java/org/xm06/pms/dao/UserMapper
   
```
###


