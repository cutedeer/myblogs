package com.sushuzhuang.myblogs.controller;


import com.sushuzhuang.myblogs.utils.ChineseToPinYinUtil;
import com.sushuzhuang.myblogs.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 图片上传接口
 */
@RestController
@CrossOrigin
@RequestMapping("/img/**")
public class ImgController {

    //这里获取配置的路径，直接在这里写好路径也行
    @Value("${classPath.uploadImg}")
    private String classPath;

    @Autowired
    private UploadUtil uploadUtil;

    @PostMapping("/upload/imgs")
    public String upLoadimg(@RequestParam MultipartFile file,@RequestParam String key) {
        String newPackage=ChineseToPinYinUtil.getPinYin(key);
        String result="";
        try {
            if(file!=null){

                //调用文件上传工具类
                return uploadUtil.commonUpload(1, file,classPath,newPackage);
            }

        } catch (Exception e) {
            result="出错";
            e.printStackTrace();

        }
        return result;
    }
}
