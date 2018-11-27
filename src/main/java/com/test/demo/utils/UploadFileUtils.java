package com.test.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author wangxl
 * @date 2018/11/26 11:07
 */
public class UploadFileUtils {
    private final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
    public String uploadImage(MultipartFile file){
        String fileName = file.getOriginalFilename();
        if(fileName.indexOf("\\") != -1){
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        String filePath = "D:/WorkPlace/SpringBoot_Test/src/main/resources/static/images/";
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath+fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("上传失败!!!");
            return null;
        }
        logger.info("上传成功！！！");
        return fileName;
    }

}
