package com.sushuzhuang.myblogs.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Component
public class UploadUtil {

    @Autowired
    private DateFormatTool dateFormatTool;

    public String commonUpload(Integer type, MultipartFile filedata,String classPath,String newPackage) throws IOException {
        String fileName = dateFormatTool.format(new Date(),"yyyyMMddHHmmss");// 以获取时间戳来命名上传的文件的，避免重名
        newPackage=newPackage+dateFormatTool.format(new Date(),"yyyyMMdd");//以获取时间戳来命名上传的文件的父文件夹，避免重名
        String suffix = filedata.getOriginalFilename().substring
                (filedata.getOriginalFilename().lastIndexOf("."));
        String name = fileName +suffix;
        String imgAddr = "";

        String root = "";
        switch (type) {
            //图片
            case 1:
                root = classPath+"/"+newPackage;
                imgAddr = "images/deatils" + "/" +newPackage+"/"+ name;    //最后返回的路径
                break;
        }


        File file = new File(root);
        if(!file.exists()){
            file.mkdirs();
        }

        File destFile = new File(root, name);
        byte[] bytes = filedata.getBytes();
        FileOutputStream fos = new FileOutputStream(destFile); // 写入文件
        fos.write(bytes);
        fos.close();

        return imgAddr;
    }

}
