package org.xm06.pms.controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.utils.FileUtil;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/download")
    public void fileDownLoad(String filePath,
                               HttpServletResponse response,
                               HttpServletRequest request) throws IOException {

        AssertUtil.isTrue(StringUtils.isBlank(filePath), "文件路径为空");

        String filename = FileUtil.getFileFromUrl(filePath);
        System.out.println(filename);

        FileInputStream fis = null;
        try {
             fis = new FileInputStream(filePath);
        }catch (FileNotFoundException e){
            AssertUtil.isTrue(true, "该文件不存在");
        }

        String type = new MimetypesFileTypeMap().getContentType(filename);
        response.setHeader("Content-type",type);
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        response.setHeader("Access-Control-Expose-Headers", "filename");
        response.setHeader("filename", URLEncoder.encode(filename,"utf-8"));
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename,"utf-8"));

        ServletOutputStream sos = response.getOutputStream();
        int len=0;
        byte[] bytes=new byte[1024];
        while((len=fis.read(bytes))!=-1){
            sos.write(bytes,0,len);
        }
        fis.close();
        sos.close();
    }
}
