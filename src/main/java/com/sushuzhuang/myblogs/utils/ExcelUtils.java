package com.sushuzhuang.myblogs.utils;

import com.sushuzhuang.myblogs.domain.WorkSheetDetail;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public void getValue(List<WorkSheetDetail> userList, FileOutputStream fout) {
        try {
            //1.创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //1.1创建合并单元格对象
            CellRangeAddress callRangeAddress1 = new CellRangeAddress(0, 1, 0, 13);//起始行,结束行,起始列,结束列
            //单位
            CellRangeAddress callRangeAddress22 = new CellRangeAddress(2, 2, 0, 13);//起始行,结束行,起始列,结束列
            //标题
            CellRangeAddress callRangeAddress31 = new CellRangeAddress(3, 5, 0, 0);//起始行,结束行,起始列,结束列
            CellRangeAddress callRangeAddress32 = new CellRangeAddress(3, 5, 1, 1);//起始行,结束行,起始列,结束列
            CellRangeAddress callRangeAddress36 = new CellRangeAddress(3, 3, 2, 11);//起始行,结束行,起始列,结束列
            CellRangeAddress callRangeAddress37 = new CellRangeAddress(3, 4, 12, 13);//起始行,结束行,起始列,结束列

            HSSFCellStyle styleBorder = workbook.createCellStyle();
            styleBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //标题
            HSSFCellStyle erStyle = createCellStyle(workbook, (short) 13, true, true);
            //班组和时间
            HSSFCellStyle sanStyle = createCellStyle(workbook, (short) 10, false, false);
            //标题样式
            HSSFCellStyle colStyle = createCellStyle(workbook, (short) 10, true, true);
            //2.创建工作表
            HSSFSheet sheet = workbook.createSheet("派单");
            //2.1加载合并单元格对象
            sheet.addMergedRegion(callRangeAddress1);
            sheet.addMergedRegion(callRangeAddress22);
            sheet.addMergedRegion(callRangeAddress31);
            sheet.addMergedRegion(callRangeAddress32);
            sheet.addMergedRegion(callRangeAddress36);
            sheet.addMergedRegion(callRangeAddress37);

            //设置默认列宽
            sheet.setDefaultColumnWidth(10);
            //3.创建行
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell(0);
            //3.1创建头标题行;并且设置头标题
            //加载单元格样式
            cell.setCellStyle(erStyle);
            setRegionStyle(sheet,callRangeAddress1,styleBorder);
            cell.setCellValue("崂山区2018年1-6月房地产业重点企业情况表");

            HSSFRow rowsan = sheet.createRow(2);
            HSSFCell cellsan = rowsan.createCell(0);

            //加载单元格样式
            cellsan.setCellStyle(sanStyle);
            setRegionStyle(sheet,callRangeAddress22,styleBorder);
            cellsan.setCellValue("单位：万元");

            //3.2创建列标题;并且设置列标题
            HSSFRow rowtitle = sheet.createRow(3);
            String[] titles = {"序号", "企业名称", "上交区级税收", "全口径税收收入"};//""为占位字符串
            for (int i = 0; i < titles.length; i++) {
                HSSFCell cell2 = rowtitle.createCell(i);
                setRegionStyle(sheet,callRangeAddress1,styleBorder);
                cell2.setCellStyle(colStyle);
                cell2.setCellValue(titles[i]);
            }

            setRegionStyle(sheet,callRangeAddress31,styleBorder);
            setRegionStyle(sheet,callRangeAddress32,styleBorder);
            setRegionStyle(sheet,callRangeAddress36,styleBorder);
            setRegionStyle(sheet,callRangeAddress37,styleBorder);
            String[] titlefour = {"小计", "增值税", "企业所得税", "个人所得税", "其他税收"};
            HSSFRow row4 = sheet.createRow(4);
            for (int i = 0; i < titlefour.length; i++) {
                CellRangeAddress linshi = new CellRangeAddress(4, 4, (i + 1) * 2, (i + 1) * 2 + 1);//起始行,结束行,起始列,结束列
                sheet.addMergedRegion(linshi);
                HSSFCell cell3 = row4.createCell((i + 1) * 2);
                //加载单元格样式
                setRegionStyle(sheet,linshi,styleBorder);
                cell3.setCellStyle(colStyle);
                cell3.setCellValue(titlefour[i]);
            }


            HSSFRow row5 = sheet.createRow(5);
            String[] titlefive = {"金额", "增减", "金额", "增减", "金额", "增减", "金额", "增减", "金额", "增减", "金额", "增减"};

            for (int i = 0; i < titlefive.length; i++) {
                HSSFCell cell4 = row5.createCell(2 + i);
                //加载单元格样式
                cell4.setCellStyle(colStyle);
                cell4.setCellValue(titlefive[i]);
            }


//            //4.操作单元格;将用户列表写入excel
//            if(userList != null)
//            {
//                int i=1;
//                for(int j=0;j<userList.size();j++)
//                {
//                    //创建数据行,前面有两行,头标题行和列标题行
//                    HSSFRow row3 = sheet.createRow(j+5);
//                    HSSFCell cell0 = row3.createCell(0);
//                    cell0.setCellStyle(cellStyle);
//                    cell0.setCellValue(i++);
//
//                    HSSFCell cell1 = row3.createCell(1);
//                    cell1.setCellStyle(cellStyle);
//                    cell1.setCellValue(userList.get(j).getWorkCtx());
//
//                    HSSFCell cell2 = row3.createCell(2);
//                    cell2.setCellStyle(cellStyle);
//                    cell2.setCellValue(userList.get(j).getTotalHumanDays());
//
//                    HSSFCell cell3 = row3.createCell(3);
//                    cell3.setCellStyle(cellStyle);
//                    cell3.setCellValue(userList.get(j).getGwnNum());
//
//                    HSSFCell cell4 = row3.createCell(4);
//                    cell4.setCellStyle(cellStyle);
//                    cell4.setCellValue(userList.get(j).getTmnNum());
//
//                    HSSFCell cell5 = row3.createCell(5);
//                    cell5.setCellStyle(cellStyle);
//                    cell5.setCellValue(userList.get(j).getTotalHumanDays());
//
//                    HSSFCell cell6 = row3.createCell(6);
//                    cell6.setCellStyle(cellStyle);
//                    cell6.setCellValue(userList.get(j).getUnitAmount());
//
//                    HSSFCell cell7= row3.createCell(7);
//                    cell7.setCellStyle(cellStyle);
//                    cell7.setCellValue(userList.get(j).getUnitPrice());
//                }
//            }

            //5.输出
            workbook.write(fout);
//	            workbook.close();
            //out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress region,
                                      HSSFCellStyle cs) {

        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {

            HSSFRow row = sheet.getRow(i);
            if (row == null)
                row = sheet.createRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                HSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                    cell.setCellValue("");
                }
                cell.setCellStyle(cs);

            }
        }
    }
    /**
     * @param workbook
     * @param fontsize
     * @return 单元格样式
     */
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontsize, boolean flag, boolean flag1) {
        // TODO Auto-generated method stub
        //workbook,(short)13,true,true
        HSSFCellStyle style = workbook.createCellStyle();
        //设置边框样式
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        //是否水平居中
        if (flag1) {
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        }

        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //创建字体
        HSSFFont font = workbook.createFont();
        //是否加粗字体
        if (flag) {
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }
        font.setFontHeightInPoints(fontsize);
        //加载字体
        style.setFont(font);

        return style;
    }


    public static void main(String args[]) {
        //模拟部分数据
        List<WorkSheetDetail> detail = new ArrayList<WorkSheetDetail>();

        WorkSheetDetail d1 = new WorkSheetDetail("23", 23f, 43, 34, 243f, 54f, "34");
        WorkSheetDetail d2 = new WorkSheetDetail("23", 23f, 43, 34, 243f, 54f, "34");
        WorkSheetDetail d3 = new WorkSheetDetail("23", 23f, 43, 34, 243f, 54f, "34");
        WorkSheetDetail d4 = new WorkSheetDetail("23", 23f, 43, 34, 243f, 54f, "34");
        WorkSheetDetail d5 = new WorkSheetDetail("23", 23f, 43, 34, 243f, 54f, "34");
        detail.add(d1);
        detail.add(d2);
        detail.add(d3);
        detail.add(d4);
        detail.add(d5);
        try {
            // File f = new File("G:\tmp\\students.xls");
            FileOutputStream fout = new FileOutputStream("G:\\tmp\\students.xls");
            new ExcelUtils().getValue(detail, fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
