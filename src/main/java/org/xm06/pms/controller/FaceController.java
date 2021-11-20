package org.xm06.pms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.model.FaceUploadModel;
import org.xm06.pms.model.UserModel;
import org.xm06.pms.service.FaceService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/face")
@Api(value="FaceController", tags = "人脸模块接口")
public class FaceController extends BaseController {

    @Autowired
    FaceService faceService;

    @PostMapping("/faceImgUpload")
    @ResponseBody
    @ApiOperation("获取用户的图片字符串，将图片保存并训练出人脸信息yml文件")
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "userId", value = "上传的用户id",required = true,paramType = "body",dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "facesJson", value = "上传base64图片数组的json字符串",required = true,paramType = "body",dataType = "String", dataTypeClass = String.class)
    })
    public ResultInfo faceImageBase64Upload(@RequestBody @Valid FaceUploadModel faceUploadModel) throws IOException {
        faceService.trainAndSaveFaceInfo(faceUploadModel);
        return success("上传成功", 200, null);
    }

    @PostMapping("/faceFileUpload")
    @ResponseBody
    @ApiOperation(value = "上传视频文件，返回包含保存路径的对象",notes = "需传入userId,file(视频文件，FormData上传")
    public ResultInfo faceFileUpload(@RequestParam Map<String,String> map,
                                     @RequestBody MultipartFile file) throws IOException {
        Integer userId = Integer.parseInt(map.get("userId"));
        System.out.println(userId);
        String path = faceService.saveUploadFaceMp4(userId, file);
        return new ResultInfo("成功",200, path);
    }

    @PostMapping("/trainVideoFaceInfo")
    @ResponseBody
    @ApiOperation(value = "训练用户保存的视频文件，生成人脸信息yml文件",notes = "需要userId,savePath(服务器资源路径)")
    public ResultInfo trainFromFaceInfo(@RequestBody Map<String,String> map) throws IOException {
        Integer userId = Integer.parseInt(map.get("userId"));
        String savePath = map.get("savePath");
        faceService.videoTran(userId, savePath);
        return new ResultInfo("成功",200, null);
    }

    @PostMapping("/faceLogin")
    @ResponseBody
    @ApiOperation("视频上传的图片，进行人脸识别登录")
    public ResultInfo faceLogin(@RequestBody @Valid FaceUploadModel faceUploadModel){
        UserModel userModel = faceService.checkUserFace(faceUploadModel);
        return new ResultInfo("人脸识别成功", 200, userModel);
    }

    @PostMapping("/dropFaceInfo")
    @ResponseBody
    @ApiOperation(value = "删除用户的人脸信息",notes = "需要userId")
    public ResultInfo dropFaceInfo(@RequestBody Map<String, String> map) {
        faceService.setValid(Integer.parseInt(map.get("userId")));
        return new ResultInfo("已提交申请，请前往邮箱确认", 200, null);
    }


    @RequestMapping(value = "/dropConfirm/{id}", method = {RequestMethod.GET,RequestMethod.POST})
    public String dropConfirm(@PathVariable(value = "id") Integer userId){
        faceService.dropUserFaceInfo(userId);
        return "dropSuccess";

    }

    @RequestMapping(value = "/dropCancel/{id}", method = {RequestMethod.GET,RequestMethod.POST})
    public String dropCancel(@PathVariable(value = "id") Integer userId){
        faceService.dropUserFaceInfoCancel(userId);
        return "dropCancelSuccess";
    }
}
