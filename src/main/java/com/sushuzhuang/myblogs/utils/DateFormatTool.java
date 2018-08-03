package com.sushuzhuang.myblogs.utils;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTool {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public  String format(Date date) {
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
