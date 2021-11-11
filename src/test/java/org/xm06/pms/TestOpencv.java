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


import org.junit.Test;
import org.junit.runner.RunWith;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.xm06.pms.utils.FaceUtil;

import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestOpencv {
    private final static String faceDetectorXML2URL =
            "D:\\opencv\\install\\etc\\haarcascades\\haarcascade_frontalface_alt.xml";
    private static CascadeClassifier faceCascade;
    static {
        // 解决awt报错问题
        System.setProperty("java.awt.headless", "false");
        System.out.println(System.getProperty("java.library.path"));
        System.setProperty("spring.devtools.restart.enabled", "false");
        // 加载动态库
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java454.dll");
        System.out.println(url.getPath());
        System.load(url.getPath());
        faceCascade = new CascadeClassifier(faceDetectorXML2URL);
    }

    @Test
    public void test1(){
        Mat mat = FaceUtil.byteToMat(new byte[1024]);
        HighGui.imshow("daw",mat);
        System.out.println("dwa");
    }
}
