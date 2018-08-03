package com.sushuzhuang.myblogs.controller;

import com.sushuzhuang.myblogs.domain.Share;
import com.sushuzhuang.myblogs.services.impl.ShareServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/share/**")
public class ShareController {

    @Autowired
    private ShareServiceImpl shareService;

    @GetMapping("/all")
    public List<Share> loadAll(){
        return shareService.loadAll();
    }

    @GetMapping("/tag")
    public List<Share> loadByTag(@RequestParam String tag){
        return shareService.loadByTag("my|"+tag);
    }
}
