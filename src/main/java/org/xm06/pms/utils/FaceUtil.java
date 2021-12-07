package org.xm06.pms.utils;

import org.apache.commons.lang3.StringUtils;
import org.opencv.core.*;
import org.opencv.face.FaceRecognizer;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.opencv.imgcodecs.Imgcodecs.*;


public class FaceUtil {


    public final static Integer MINNEEDFACECOUNT = 50;



    public static Mat byteToMat(byte[] b){
       Mat mat = Imgcodecs.imdecode(new MatOfByte(b), IMREAD_UNCHANGED);
       return mat;
    }



    public static Mat conv_Mat(String imgSrc, CascadeClassifier faceDetector){
        if(StringUtils.isBlank(imgSrc)){
            return null;
        }
        Mat image0 = imread(imgSrc);
        return conv_Mat(image0,faceDetector);
    }




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
            face = new Mat(image0, rect);
        }
        if(face != null){
            Imgproc.resize(face,face,new Size(345,345));
        }
        return face;
    }



    public static Mat conv_Mat_gray(Mat image0, CascadeClassifier faceDetector){
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




    public static String train(String imageFolder, String saveFolder, String userIdStr)
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

            String name = userIdStr;
            Integer id = nameMapId.get(name);
            if (id == null) {
                id = names.size();
                names.add(name);
                nameMapId.put(name, id);
                faceRecognizer.setLabelInfo(id, name);
            }
            Mat mat = imread(file.getAbsolutePath());
            Mat gray = new Mat();
            Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);
            images.add(gray);

//            restoreImg(gray,"D:\\test\\gray\\" + System.currentTimeMillis()+".jpg");
//            System.out.println("add total " + images.size());
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
        String ap = saveFolder + "/face_model_"+userIdStr+".yml";
        faceRecognizer.save(ap);
        return ap;
    }





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
            System.out.println("置信度"+confidence[0]+"  用户名:"+name);
            if (confidence[0] < 50) {
                return true;
            }
        }
        return false;
    }



    public static Mat readImg(String imgSrc) {
        return imread(imgSrc);
    }


    public static void restoreImg(Mat img, String src) {
        imwrite(src, img);
    }


    private static long startTime;

    public static String readImgFromVideo(String videoPath, String frameSavePath ,String username, CascadeClassifier faceCascade){
        VideoCapture capture = new VideoCapture(videoPath);
        int connection = 0;
        startTime = System.currentTimeMillis();

        while(connection <= 50
                && System.currentTimeMillis() - startTime < 60000
                && capture.isOpened()){

            if(System.currentTimeMillis() - startTime > 300){
                Mat img = new Mat();
                Mat grayFrame = new Mat();
                MatOfRect faces = new MatOfRect();

                capture.read(img);
                Imgproc.cvtColor(img, grayFrame, Imgproc.COLOR_BGR2GRAY);

                faceCascade.detectMultiScale(grayFrame, faces);
                Rect[] facesArray = faces.toArray();

                if(facesArray.length >= 1){
                    if (System.currentTimeMillis() - startTime > 300) {
                        startTime = System.currentTimeMillis();
                        connection++;
                        System.out.println("image: " + connection);
                        imwrite(
                        frameSavePath + "\\face_"+username+"_"+connection + ".jpg",
                                img.submat(facesArray[0]));
                    }
                }
            }
        }
        capture.release();
        AssertUtil.isTrue(connection<50, "视频人脸图像不足");
        return frameSavePath;
    }

}
