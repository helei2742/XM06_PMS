package org.xm06.pms.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FaceUtil {

/*

    public final static Integer MINNEEDFACECOUNT = 50;


    */
/**
     * 图片字节转换为mat
     * @param b
     * @return
     *//*

    public static Mat byteToMat(byte[] b){
       Mat mat = Imgcodecs.imdecode(new MatOfByte(b), IMREAD_UNCHANGED);
       return mat;
    }

    */
/**
     * 读取路径图片，灰度化识别人脸后返回人脸的mat，图片需要为原图
     * @param imgSrc 图片路径
     * @return  未灰度化人脸 大小 w345 h345
     *//*

    public static Mat conv_Mat(String imgSrc, CascadeClassifier faceDetector){
        if(StringUtils.isBlank(imgSrc)){
            return null;
        }
        Mat image0 = imread(imgSrc);
        return conv_Mat(image0,faceDetector);
    }

    */
/**
     * 将图片Mat识别后返回人脸的mat，图片需要为原图，
     * @param  image0 需转换的图片Mat
     * @return  未灰度化人脸 大小 w345 h345
     *//*

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

    */
/**
     * 识别图片人脸转换为灰度图
     * @param image0
     * @param faceDetector
     * @return 灰度化人脸 大小 w345 h345
     *//*

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

    */
/**
     * 训练模型的方法，传入人脸图片所在的文件夹路径，和模型输出的路径
     * 图片需要为未灰度化的人脸大小统一
     * 训练结束后模型文件会在模型输出路径里边
     *
     * 存放文件格式：   "/face_model_"+userIdStr+".yml"
     *//*

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
            Mat mat = Imgcodecs.imread(file.getAbsolutePath());
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


    */
/**
     * 面部识别方法， 图片必须为灰度化的人脸图片
     * @param face 需识别的图片mat,灰度化的人脸
     * @param faceCascade  脸部识别模块
     * @param faceRecognizer 指定的face的识别器
     * @return boolean 代表是否验证通过
     *//*

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


    */
/**
     * 根据图片路径获取图片
     * @param imgSrc
     * @return
     *//*

    public static Mat readImg(String imgSrc) {
        return imread(imgSrc);
    }


    public static void restoreImg(Mat img, String src) {
        imwrite(src, img);
    }


    private static long startTime;
    */
/**
     * 读取视频文件，获取有人像的帧，保存这些帧
     * @param videoPath      视频路径
     * @param frameSavePath 视频中读取的图像帧保存路径
     * @param username      用户名。用户生成图像名
     * @param faceCascade
     * @return  保存的帧的路径
     *//*

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
                        Imgcodecs.imwrite(
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
*/

}
