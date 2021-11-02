package org.xm06.pms;


/*import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.*;*/


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestOpencv {
    /**
     * @return
     * @Description
     * @Param
     * @Author zhangsan
     * @Date 2020.09.05 9:43
     **/
/*    @Test
    public void testOpencv() throws Exception {
        // 解决awt报错问题
        System.setProperty("java.awt.headless", "false");
        System.out.println(System.getProperty("java.library.path"));
        // 加载动态库
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java454.dll");
        System.load(url.getPath());
        // 读取图像
        Mat image = imread("D:\\test.png");
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);

        // 创建输出单通道图像
        Mat grayImage = new Mat(image.rows(), image.cols(), CvType.CV_8SC1);
        // 进行图像色彩空间转换
        cvtColor(image, grayImage, COLOR_RGB2GRAY);
        imshow("Processed Image", grayImage);
        imwrite("D://hello.jpg", grayImage);
        waitKey();
    }

    private final static String faceDetectorXML2URL =
            "D:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
    private static CascadeClassifier faceDetector;

    @Test
    public void testOpencv2(){

        Mat image0 = imread("D:\\test.png");
        Mat image1 = new Mat();

        // 灰度化
        Imgproc.cvtColor(image0, image1, Imgproc.COLOR_BGR2GRAY);
        // 探测人脸
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image0, faceDetections);

        // rect中人脸图片的范围
        for (Rect rect : faceDetections.toArray()) {
            // 进行图片化线
            line(image0,new Point(rect.x,rect.y)
                    ,new Point(rect.x+rect.width,rect.y)
                    , new Scalar(0,255,0),2);
            line(image0,new Point(rect.x,rect.y),new Point(rect.x,rect.y+rect.height)
                    , new Scalar(0,255,0),2);
            line(image0,new Point(rect.x+rect.width,rect.y),new Point(rect.x+rect.width,rect.y+rect.height)
                    , new Scalar(0,255,0),2);
            line(image0,new Point(rect.x,rect.y+rect.height),new Point(rect.x+rect.width,rect.y+rect.height)
                    , new Scalar(0,255,0),2);
        }
        imshow("hhhh",image0);
        waitKey();
    }

    public void before(){
        // 解决awt报错问题
        System.setProperty("java.awt.headless", "false");
        System.out.println(System.getProperty("java.library.path"));
        // 加载动态库
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java454.dll");
        System.load(url.getPath());
        *//**
         * 初始化人脸探测器
         *//*
        faceDetector = new CascadeClassifier(faceDetectorXML2URL);
    }

    @Test
    public void testOpencv3() {
        before();

        String img_1 = "D:\\test.png";
        String img_2 = "D:\\test4.png";

        Mat mat_1 = conv_Mat(img_1);
        Mat mat_2 = conv_Mat(img_2);
        imshow("1",mat_1);
        imshow("2",mat_2);
        Mat hist_1 = new Mat();
        Mat hist_2 = new Mat();

        //颜色范围
        MatOfFloat ranges = new MatOfFloat(0f, 256f);
        int  Matching_Accuracy = 10000000;
        //直方图大小， 越大匹配越精确 (越慢)
        MatOfInt histSize = new MatOfInt(Matching_Accuracy);

        Imgproc.calcHist(Arrays.asList(mat_1), new MatOfInt(0), new Mat(), hist_1, histSize, ranges);
        Imgproc.calcHist(Arrays.asList(mat_2), new MatOfInt(0), new Mat(), hist_2, histSize, ranges);

        // CORREL 相关系数
        double res = Imgproc.compareHist(hist_1, hist_2, Imgproc.CV_COMP_CORREL);
        System.out.println(res);
        waitKey();
    }

    private Mat conv_Mat(String img){
        System.out.println(img+"====");
        if(StringUtils.isBlank(img)){
            return null;
        }
        Mat image0 = imread(img);
        Mat image1 = new Mat();
        //Mat image2 = new Mat();
        // 灰度化
        Imgproc.cvtColor(image0, image1, Imgproc.COLOR_BGR2GRAY);
        //直方均匀
        //Imgproc.equalizeHist(image1, image2);
        // 探测人脸
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image1, faceDetections);
        //探测人眼
//        MatOfRect eyeDetections = new MatOfRect();
//        eyeDetector.detectMultiScale(image1, eyeDetections);

        // rect中人脸图片的范围
        Mat face = null;
        for (Rect rect : faceDetections.toArray()) {
            //给图片上画框框 参数1是图片 参数2是矩形 参数3是颜色 参数四是画出来的线条大小
//            Imgproc.rectangle(image0,rect,new Scalar(0,0,255),2);
//            imshow("000",image0);
            //输出图片
            //Imgcodecs.imwrite(img+"_.jpg",image0);
            face = new Mat(image1, rect);
        }

        return face;
    }*/
}
