package com.sushuzhuang.myblogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/**")
public class InitController {

    @RequestMapping("/")
    public String init(){
        return "index";
    }

    @RequestMapping("/index.html")
    public String index(){
        return "index";
    }

    @RequestMapping("/share.html")
    public String share(){
        return "share";
    }

    @RequestMapping("/about.html")
    public String about(){
        return "about";
    }

    @RequestMapping("/gbook.html")
    public String gBook(){
        return "gbook";
    }
}
