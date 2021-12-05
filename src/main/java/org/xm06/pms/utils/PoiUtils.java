package org.xm06.pms.utils;


import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.xm06.pms.model.ExcelConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PoiUtils {


    /**
     * 当初数据库中的数据为excel表
     * @param datas
     * @param excelConfig
     * @param c
     * @param <T>
     * @return
     */
    public static<T> ResponseEntity<byte[]> exportExcel(List<T> datas, ExcelConfig excelConfig, Class c) {

        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {

            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory(excelConfig.getCategory());
            //3.2设置文档管理员
            dsi.setManager(excelConfig.getManager());
            //3.3设置组织机构
            dsi.setCompany(excelConfig.getCompany());
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject(excelConfig.getSubject());
            //4.2.设置文档标题
            si.setTitle(excelConfig.getTitle());
            //4.3 设置文档作者
            si.setAuthor(excelConfig.getAuthor());
            //4.4设置文档备注
            si.setComments(excelConfig.getComments());
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet(excelConfig.getSheetName());
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            int size = datas.size();
            if(size <= 0){

            }else{
                //定义列的宽度

                for (int i = 0; i < size; i++) {
                    sheet.setColumnWidth(i, 12 * 256);
                }
                Field[] declaredFields = c.getDeclaredFields();
                int len = declaredFields.length;

                //5.设置表头
                HSSFRow headerRow = sheet.createRow(0);
                for (int i = 0; i < len; i++) {
                    HSSFCell cell0 = headerRow.createCell(i);
                    String[] split = String.valueOf(declaredFields[i]).split("\\.");
                    cell0.setCellValue(split[split.length-1]);
                    cell0.setCellStyle(headerStyle);
                }

                //6.装数据
                for (int i = 0; i < size; i++) {
                    HSSFRow row = sheet.createRow(i + 1);
                    Object data = datas.get(i);
                    for (int k = 0; k < len; k++) {
                        Field f = declaredFields[k];
                        f.setAccessible(true);
                        Object value = f.get(data);
                        if(value instanceof Date){
                            Date date = (Date) value;
                            row.createCell(k).setCellValue(
                                    new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(date));
                        }
                        else {
                            row.createCell(k).setCellValue(String.valueOf(value));
                        }
                    }
                }
            }

            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String( (excelConfig.getTitle()+".xls").getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

}
