package com.sushuzhuang.myblogs.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatTool {

   private SimpleDateFormat simpleDateFormat;

    public   String format(Date date,String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return  simpleDateFormat.format(date);
    }

    public Date parse(String date){
        Date d=null;
        try {
            d=simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
