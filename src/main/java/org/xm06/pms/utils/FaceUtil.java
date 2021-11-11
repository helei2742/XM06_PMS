package org.xm06.pms.utils;

import org.apache.commons.lang3.StringUtils;
import org.opencv.core.*;
import org.opencv.face.FaceRecognizer;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.opencv.imgcodecs.Imgcodecs.*;

public class FaceUtil {
//    /**
//     * 人脸探测模块位置
//     */
//    private final static String faceDetectorXML2URL =
//            "D:\\opencv\\install\\etc\\haarcascades\\haarcascade_frontalface_alt.xml";
//
//    /**
//     * 脸部识别
//     */
//    private static CascadeClassifier faceCascade;
//
//    static {
//        // 解决awt报错问题
//        System.setProperty("java.awt.headless", "false");
//        System.out.println(System.getProperty("java.library.path"));
//        // 加载动态库
//        URL url = ClassLoader.getSystemResource("lib/openecv/opencv_java454.dll");
//        System.load(url.getPath());
////        faceCascade = new CascadeClassifier(faceDetectorXML2URL);
//    }


    public static Mat byteToMat(byte[] b){
       Mat mat = Imgcodecs.imdecode(new MatOfByte(b), IMREAD_UNCHANGED);
       return mat;
    }

    /**
     * 读取路径图片，灰度化识别人脸后返回人脸的mat，图片需要为原图
     * @param imgSrc 图片路径
     * @return  灰度化人脸 大小 w345 h345
     */
    public static Mat conv_Mat(String imgSrc, CascadeClassifier faceDetector){
        if(StringUtils.isBlank(imgSrc)){
            return null;
        }
        Mat image0 = imread(imgSrc);
        return conv_Mat(image0,faceDetector);
    }

    /**
     * 将图片Mat，灰度化识别人脸后返回人脸的mat，图片需要为原图
     * @param  image0 需转换的图片Mat
     * @return  灰度化人脸 大小 w345 h345
     */
    public static Mat conv_Mat(Mat image0, CascadeClassifier faceDetector){

        Mat image1 = new Mat();
        // 灰度化
        Imgproc.cvtColor(image0, image1, Imgproc.COLOR_BGR2GRAY);
        MatOfRect faceDetections = new MatOfRect();
        //人脸识别
        faceDetector.detectMultiScale(image1, faceDetections);
        // rect为人脸图片的范围
        Mat face = null;
        for (Rect rect : faceDetections.toArray()) {
            face = new Mat(image1, rect);
        }
        if(face != null){
            Imgproc.resize(face,face,new Size(345,345));
        }
        return face;
    }


    /**
     * 训练模型的方法，传入人脸图片所在的文件夹路径，和模型输出的路径
     * 图片需要为未灰度化的人脸大小 同一
     * 训练结束后模型文件会在模型输出路径里边
     */
    public static void train(String imageFolder, String saveFolder, String userIdStr)
            throws IOException {
// opencv的模型
        FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
// 读取文件于数组中
        File[] files = new File(imageFolder).listFiles();
        Map<String, Integer> nameMapId = new HashMap<String, Integer>();
// 图片集合
        List<Mat> images = new ArrayList<Mat>(files.length);
// 名称集合
        List<String> names = new ArrayList<String>(files.length);
        List<Integer> ids = new ArrayList<Integer>(files.length);
        for (int index = 0; index < files.length; index++ ) {
// 解析文件名 获取名称
            File file = files[index];
            String name = file.getName().split("\\.")[0];
            Integer id = nameMapId.get(name);
            if (id == null) {
                id = names.size();
                names.add(name);
                nameMapId.put(name, id);
                faceRecognizer.setLabelInfo(id, name);
            }
            Mat mat = Imgcodecs.imread(file.getAbsolutePath());
            Mat gray = new Mat();
            Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);
            images.add(gray);
            System.out.println("add total " + images.size());
            ids.add(id);
        }
        int[] idsInt = new int[ids.size()];

        for (int i = 0; i < idsInt.length; i++) {
            idsInt[i] = ids.get(i).intValue();
        }
// 显示标签
        MatOfInt labels = new MatOfInt(idsInt);
// 调用训练方法
        faceRecognizer.train(images, labels);
// 输出持久化模型文件 训练一次后就可以一直调用
        faceRecognizer.save(saveFolder + "/face_model_"+userIdStr+".yml");
    }

    /**
     * 面部识别方法， 图片必须为灰度化的人脸图片
     * @param face 需识别的图片mat
     * @param faceCascade  脸部识别模块
     * @param faceRecognizer 指定的face的识别器
     * @return boolean 代表是否验证通过
     */
    public static boolean faceRecognize_getBoolean(Mat face, CascadeClassifier faceCascade,
                                        FaceRecognizer faceRecognizer){
        MatOfRect faces = new MatOfRect();
        Imgproc.equalizeHist(face, face);

        // detect faces
        faceCascade.detectMultiScale(face, faces);
        Imgproc.equalizeHist(face, face);

        Rect[] facesArray = faces.toArray();

        for (Rect rect : facesArray) {
            int[] label = new int[1];
            double[] confidence = new double[1];
            faceRecognizer.predict(face.submat(rect), label, confidence);
            String name = faceRecognizer.getLabelInfo(label[0]);

            System.out.println(confidence[0]);
            System.out.println(label[0]);
            System.out.println(name);

            if (confidence[0] < 55) {
                return true;
            }
        }
        return false;
    }
    /**
     * 面部识别方法，返回数字代表结果，
     * 传入的mat图为原图，无需进行灰度化或传入识别后的人脸图片否则可能导致识别出现偏差
     * 1、 -1 表示未识别到面部
     * 2、 0 表示识别到面部，但并不是该人脸
     * 3、 1 表示正确识别到面部，且在置信度范围内（可信任）
     *
     * 注意， 不在置信度范围内的仍然辨别为不是人脸，返回0
     * @param img 识别的图片
     * @param faceCascade 辨别识别模块
     * @param faceRecognizer  需识别的人脸的识别器（载入过yml等文件）
     * @return -1，0，1
     */
    public static Integer faceRecognize_getInteger(Mat img, CascadeClassifier faceCascade,
                                        FaceRecognizer faceRecognizer){
        Mat face = conv_Mat(img, faceCascade);
        if(face == null) return -1;
        boolean b = faceRecognize_getBoolean(face, faceCascade, faceRecognizer);
        if(b) return 1;
        return 0;
    }


    /**
     * 识别图片中的人脸并画框
     * @param img 图片
     * @param faceCascade 人脸识别模块
     * @param color 颜色
     * @param thickness 粗细
     * @return 画框后的图片
     */
    public static Mat selectFace(Mat img, CascadeClassifier faceCascade,
                                 Scalar color, Integer thickness){
        Mat image1 = new Mat();
        Mat image2 = img.clone();
        Imgproc.cvtColor(img, image1, Imgproc.COLOR_BGR2GRAY);

        MatOfRect faceDetections = new MatOfRect();
        faceCascade.detectMultiScale(image1, faceDetections);
        for (Rect rect : faceDetections.toArray()) {
            //给图片上画框框 参数1是图片 参数2是矩形 参数3是颜色 参数四是画出来的线条大小

            if(color == null) color = new Scalar(0,255,0);
            if(thickness == null) thickness = 2;
            Imgproc.rectangle(image2,rect,color,thickness);
        }
        return image2;
    }

    /**
     * 根据图片路径获取人脸，对人脸部分画框后返回
     * @param imgSrc 图片路径
     * @param faceCascade
     * @param color
     * @param thickness
     * @return
     */
    public static Mat selectFace(String imgSrc, CascadeClassifier faceCascade,
                                 Scalar color, Integer thickness){
        if(StringUtils.isBlank(imgSrc)){
            return null;
        }
        Mat image = imread(imgSrc);
        return selectFace(image,faceCascade, color, thickness);
    }

    /**
     * 根据图片路径获取图片
     * @param imgSrc
     * @return
     */
    public static Mat readImg(String imgSrc) {
        return imread(imgSrc);
    }


    public static void restoreImg(Mat img, String src) {
        imwrite(src, img);
    }

    /**
     * 灰度线性变换
     * @param gray
     * @param c
     * @return
     */
    public static Mat grayTransform(Mat gray, int c)
    {
        double fa = c, fb = 1;
        Mat transed = new Mat(gray.size(), 0);
        for (int i = 0; i <  gray.rows(); i++){
            for (int j = 0; j < gray.cols(); j++) {
                double[] p = gray.get(i, j);
                double newValue = fa * p[0] + fb;
                transed.put(i, j, newValue);
            }
        }
        return transed;
    }
}
