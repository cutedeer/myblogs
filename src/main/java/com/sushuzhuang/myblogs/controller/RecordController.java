package com.sushuzhuang.myblogs.controller;

import com.sushuzhuang.myblogs.domain.Record;
import com.sushuzhuang.myblogs.domain.Share;
import com.sushuzhuang.myblogs.services.impl.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record/**")
@CrossOrigin
public class RecordController {

    @Autowired
    private RecordServiceImpl recordService;

    @GetMapping("/all")
    public List<Record> loadMyFriendIntro(){
        List<Record> recordList= recordService.loadAll();
;        return  recordList;
    }
    @GetMapping("/by/tag")
    public List<Share>  loadShareBYTag(@RequestParam Share share){
        return null;
    }

    @PostMapping("/read/amount")
    public String  increaseReadAmount(@RequestBody Record record){
        int result = recordService.increaseReadAmount(record);
        if(result>0){
            return "修改成功";
        }
        return "修改shibai";

    }
}
