package com.sushuzhuang.myblogs;



import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@MapperScan("com.sushuzhuang.myblogs.mapper")
public class MyblogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogsApplication.class, args);
    }

}
