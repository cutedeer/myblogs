package com.sushuzhuang.myblogs.utils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sushuzhuang.myblogs.domain.ExportObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;


public class ExportExcel {


    public static byte[] export(String sheetTitle, String[] title, List<Object> list) {

        HSSFWorkbook wb = new HSSFWorkbook();//创建excel表
        HSSFSheet sheet = wb.createSheet(sheetTitle);
        sheet.setDefaultColumnWidth(10);//设置默认行宽

        //表头样式（加粗，水平居中，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //设置边框样式
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        HSSFFont fontStyle = wb.createFont();
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        cellStyle.setFont(fontStyle);

        //标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        cellStyle2.setFont(fontStyle);

        //设置边框样式
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        //字段样式（垂直居中）
        HSSFCellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

        //设置边框样式
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        //创建标题
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(20);//行高

        HSSFCell cell = row.createCell(0);
        cell.setCellValue(sheetTitle);
        cell.setCellStyle(cellStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (title.length - 1)));

        //创建表头
        HSSFRow rowTitle = sheet.createRow(1);
        rowTitle.setHeightInPoints(20);

        //创建表头
        HSSFRow rowTitle1 = sheet.createRow(1);
        rowTitle1.setHeightInPoints(20);

        HSSFCell hc;
        for (int i = 0; i < title.length-1; i++) {

            hc = rowTitle.createCell(i);
            hc.setCellValue(title[i]);
            hc.setCellStyle(cellStyle2);
        }
        hc = rowTitle1.createCell(2);
        hc.setCellValue(title[2]);
        hc.setCellStyle(cellStyle2);



        byte result[] = null;

        ByteArrayOutputStream out = null;

        try {
            //创建表格数据
            Field[] fields;
            int i = 2;

            for (Object obj : list) {
                fields = obj.getClass().getDeclaredFields();
                HSSFRow rowBody = sheet.createRow(i);
                rowBody.setHeightInPoints(20);
                int j = 0;
                for (Field f : fields) {

                    f.setAccessible(true);
                    Object va = f.get(obj);
                    if (null == va) {
                        va = "";
                    }
                    String vai = va.toString();
                    String[] ss = vai.split("\n");// 就可以了
                    // System.out.println("gnfnhdfnhfdbdd"+ss.length);
                    for (String d : ss) {
                        System.out.print(d + ":");
                    }
                    //System.out.println(vai);
                    hc = rowBody.createCell(j);
                    hc.setCellValue(va.toString());
                    hc.setCellStyle(cellStyle3);

                    j++;
                }

                i++;
            }

            out = new ByteArrayOutputStream();
            wb.write(out);
            result = out.toByteArray();
        } catch (Exception ex) {
            Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    wb.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        DateFormatTool dateFormatTool = new DateFormatTool();
        String sheetTitle = "用户信息";
        String name = sheetTitle + dateFormatTool.format(new Date(),"");
        String[] title = {"sequence", "name", "age"};

        List<Object> list = new ArrayList<Object>();

        for (int i = 0; i < 10; i++) {
            ExportObject u = new ExportObject();
            u.setSequence(i + 1);
            u.setName("张三" + i);
            u.setAge(10 + i);
            list.add(u);
        }

        byte b[] = ExportExcel.export(sheetTitle, title, list);

        File f = new File("G:\\tmp\\" + name + ".xls");
        try {
            FileUtils.writeByteArrayToFile(f, b, true);
            // Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, "修改成功");
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /***
     * 根据要求，排序显示字段
     *
     */

    public void sort(String[] title,List<Object> list) {

       for(Object obj:list){
           Field[] fields=obj.getClass().getDeclaredFields();;
           for(Field field:fields){
               field.setAccessible(true);
               try {
                   System.out.println(field.getName()+field.get(obj));
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
       }


    }
}
