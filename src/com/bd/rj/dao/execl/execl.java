package com.bd.rj.dao.execl;
/**
 * @desc   execl的文档下载
 * @author 张坡
 * @time   2021-01-20
 */
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class execl {
	
		/**
		 * @desc  编写execl的excelExport方法
		 * @param theSameFileData
		 */
		@SuppressWarnings("deprecation")
		public static void excelExport(List<Map<String, Object>> theSameFileData) {
	    	//创建HSSFWorkbook对象(excel的文档对象)  
	    	HSSFWorkbook wb = new HSSFWorkbook();  
	    	//建立新的sheet对象（excel的表单），即工作区间  
	    	HSSFSheet sheet=wb.createSheet("export");
	    	//设置列宽（第一个参数代表列序号，第二个参数代表宽度）
	    	sheet.setColumnWidth(0,3000); 
	    	sheet.setColumnWidth(1,3000);
	    	sheet.setColumnWidth(2,3000);
	    	sheet.setColumnWidth(4,3000);
	    	//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
	    	HSSFRow rowTitle=sheet.createRow(0);
	    	//设置行高
	    	rowTitle.setHeight((short)600); 
	    	//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
	    	HSSFCell cell=rowTitle.createCell(0); 
	    	
	    	//定义标题的样式
	    	HSSFCellStyle cellTileStyle = wb.createCellStyle();
	    	//水平对齐
	    	cellTileStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
	    	//垂直对齐
	    	cellTileStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    	//设置单元格的底边框
	    	cellTileStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    	//设置单元格的左边框
	    	cellTileStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    	//设置单元格的右边框
	    	cellTileStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    	//设置单元格的上边框
	    	cellTileStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    	//自动换行
	    	cellTileStyle.setWrapText(true);
	    	
	    	//创建标题的字体样式
	    	HSSFFont  fontTitleStyle = wb.createFont();
	    	//设置字体高度  
	    	fontTitleStyle.setFontHeightInPoints((short)12); 
	    	//加粗
	    	fontTitleStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    	//通过上面定义的cellTileStyle，设置当前样式的字体样式属性
	    	cellTileStyle.setFont(fontTitleStyle);
	    	//设置单元格内容  
	    	cell.setCellValue("供应商信息表");  
	    	//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
	    	CellRangeAddress cra= new CellRangeAddress(0,0,0,4);
	    	sheet.addMergedRegion(cra);
	    	//合并之后的上边框
		   	RegionUtil.setBorderTop(1, cra, sheet, wb);
		   	//合并之后的下边框
		   	RegionUtil.setBorderBottom(1, cra, sheet, wb);
		   	//合并之后的左边框
		   	RegionUtil.setBorderLeft(1, cra, sheet, wb);
		   	//合并之后的右边框
		   	RegionUtil.setBorderRight(1, cra, sheet, wb);
		   	
	    	//添加样式要单元格中
	    	cell.setCellStyle(cellTileStyle);
	    	//创建第二行，用于填写列名
	    	HSSFRow row2=sheet.createRow(1);
	    	//当前行第一列的值
	    	row2.createCell(0).setCellValue("供应商编号");
	    	//当前行第二列的值
	    	row2.createCell(1).setCellValue("合作车类型");
	    	//当前行第三列的值
	    	row2.createCell(2).setCellValue("合作 方式");
	    	//当前行第四列的值
	    	row2.createCell(3).setCellValue("联系电话");
	    	//当前行第五列的值
	    	row2.createCell(4).setCellValue("合作地址");
	    	//从第三行开始遍历数据，以rowIndex为索引
	    	int rowIndex = 2;
	    	String CaGongYing2="CaGongYing2";
	    	String GY_name="GY_name";
	    	String GY_fs="GY_fs";
	    	String GY_Phone="GY_Phone";
	    	String GY_Address="GY_Address";
	    	for(Map<String, Object> user : theSameFileData) {
	    		//每遍历一次，创建一个行对象，可以理解为新增一个空行，此时该行还没有列数据（即单元格的数据）
	    		HSSFRow rowContent = sheet.createRow(rowIndex++);
	    		rowContent.createCell(0).setCellValue((String)user.get(CaGongYing2));
	    		rowContent.createCell(1).setCellValue((String)user.get(GY_name));
	    		rowContent.createCell(2).setCellValue((String)user.get(GY_fs));
	    		rowContent.createCell(3).setCellValue((String)user.get(GY_Phone));
	    		rowContent.createCell(4).setCellValue((String)user.get(GY_Address));
	    	}
	    	
	    	//输出Excel文件  
	    	FileOutputStream output = null;
			try {
				//文件导出的路径及名称
				output = new FileOutputStream("d:\\test.xls");
				wb.write(output);
		    	output.flush();
		    	output.close();
		    	System.out.println("书写完成，正在打开...");
		    	File file = new File("d:\\test.xls");
		    	 Desktop desktop = Desktop.getDesktop();
		    	 desktop.open(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  finally {
				
			}
	    }
	}
	    


