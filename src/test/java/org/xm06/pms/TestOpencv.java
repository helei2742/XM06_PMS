package org.xm06.pms;
//
//
///*import org.apache.commons.lang3.StringUtils;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.opencv.core.*;
//import org.opencv.core.Point;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.objdetect.CascadeClassifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.awt.*;
//import java.lang.reflect.Field;
//import java.net.URL;
//import java.util.Arrays;
//
//import static org.opencv.highgui.HighGui.imshow;
//import static org.opencv.highgui.HighGui.waitKey;
//import static org.opencv.imgcodecs.Imgcodecs.imread;
//import static org.opencv.imgcodecs.Imgcodecs.imwrite;
//import static org.opencv.imgproc.Imgproc.*;*/
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.xm06.pms.utils.FaceUtil;
//
//import java.io.File;
//import java.net.URL;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class TestOpencv {
//    private static CascadeClassifier faceCascade;
//    static {
//        // 解决awt报错问题
//        System.setProperty("java.awt.headless", "false");
//        System.out.println(System.getProperty("java.library.path"));
//        // 加载动态库
//        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java3416.dll");
//        URL url1 = TestOpencv.class.getClassLoader().getResource("haarcascade_frontalface_alt.xml");
//        System.out.println(url1.getPath());
//
//        System.load(url.getPath());
//        faceCascade = new CascadeClassifier("D:\\ideaWorkSpace\\webhomework\\pms\\src\\main\\resources\\haarcascade_frontalface_alt.xml");
//    }
//
//    @Test
//    public void test1(){
//        File file = new File("D:\\testvideo");
//        if(!file.exists())
//            file.mkdirs();
//        FaceUtil.readImgFromVideo(
//                "D:\\20211111_173633.mp4", "D:\\testvideo", "zhangsan", faceCascade);
//    }
//
//    @Test
//    public void test2() {
//        LBPHFaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
//        faceRecognizer.read("D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\userface\\heleidage666\\model\\face_model_heleidage666.yml");
//
//        String fpath1= "D:\\userface\\tsbEnNUHLOeo7hxqHJ66DQ==\\face";
//        String fpath2= "D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\userface\\heleidage666\\faceimg";
//        String fpath3= "D:\\ideaWorkSpace\\webhomework\\opencvtest\\face\\imagedb3";
//        String fpath4= "D:\\test\\helei3";
//
//        File[] files =
//                new File(fpath4)
//                        .listFiles();
//
//        int size = files.length;
//        int successCount = 0;
//        for (File file : files) {
//            System.out.println("=======开始==========");
//            Mat face = imread(file.getAbsolutePath());
//
//            Imgproc.cvtColor(face, face, Imgproc.COLOR_BGR2GRAY);
//
////            HighGui.imshow(file.getName(),face);
//            System.out.println(file.getName());
//            boolean b = FaceUtil.faceRecognize_getBoolean(face, faceCascade, faceRecognizer);
//            if(b) successCount++;
//            System.out.println(b);
//            System.out.println("=========end=============");
//        }
//
//        System.out.println("测试完毕");
//        System.out.println("共测试"+size+"条数据,成功"+successCount);
//        System.out.println("成功率:"+(successCount*1.0)/size);
//    }
//}
