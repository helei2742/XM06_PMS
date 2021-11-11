package org.xm06.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.model.FaceUploadModel;
import org.xm06.pms.service.FaceService;

import java.io.IOException;

@Controller
@RequestMapping("/face")
public class FaceController extends BaseController {

    @Autowired
    FaceService faceService;

    @PostMapping("/faceImgUpload")
    @ResponseBody
    public ResultInfo faceImageBase64Upload(@RequestBody FaceUploadModel faceUploadModel) throws IOException {
        faceService.trainAndSaveFaceInfo(faceUploadModel);
        return success("上传成功", 200, null);
    }

}
