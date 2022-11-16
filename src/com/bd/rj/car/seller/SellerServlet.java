package com.bd.rj.car.seller;
/**
 * @desc   用户信息的c层
 * @author ACER
 * @time   2022-01-20
 */
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bd.rj.utils.ExcelUtils;
@SuppressWarnings("serial")
@WebServlet(urlPatterns="/seller.do",asyncSupported=true)


public class SellerServlet extends HttpServlet {

	SellerService seller = new SellerService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String method = request.getParameter("method");
			if(method.equals("query"))
			{
				this.query(request,response);
			}
			else if(method.equals("editpage"))
			{
				this.editpage(request,response);
			}
			else if(method.equals("edit"))
			{
				this.edit(request,response);
			}
			else if(method.equals("delete"))
			{
				this.delete(request,response);
			}
			else if(method.equals("deleteAll"))
			{
				this.deleteAll(request,response);
			}
			else if (method.equals("addPage")) {
				this.addPage(request,response);
			}
			else if (method.equals("add")) {
				this.add(request,response);
			}
			else if (method.equals("export"))
			{
				this.export(request,response);	
			}
			else if (method.equals("importExcel"))
			{
				this.importExcel(request,response);	
			}
		} 
		catch (SQLException | ClassNotFoundException e)
		{
			System.out.println("1.首先需要打印出异常信息为："+e.getMessage());
			System.out.println("2.其次是异常发生的内存地址："+e.getStackTrace());
			System.out.println("3.转向到公共错误处理页面");
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}
	}

/**
 * @desc  1.导入用户信息
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws FileNotFoundException 
 * @throws ClassNotFoundException 
 */
private void importExcel(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException 
{
	String fed=request.getParameter("fed");
	System.out.println("fed:"+fed);
	Workbook  wb = null;//备注：导入的包一定都是ss下的
	Sheet  sheet;
	Row row;
	
	File file=new File(fed);
	FileInputStream fileInputStream=new FileInputStream(file);
	
	 if (file.isFile() && file.exists()) 
	 {
		//System.out.println(excel.getName());
		String excelName = file.getName();//获取当前文件的名字
		int st = excelName.lastIndexOf("."); //截取后缀开始的前一位
		System.out.println(st); 
		String suffix = (String) excelName.subSequence(st+1, excelName.length());//截取文件的后缀
		System.out.println(suffix);
		if (suffix.equals("xlsx"))
		{
		  System.out.println("走2007的解析......");	
		  wb=new XSSFWorkbook(fileInputStream);
		}
		else if (suffix.equals("xls")) 
		{
		  System.out.println("走2003的解析......");	
          wb=new HSSFWorkbook(fileInputStream);
		}
		else {
            System.out.println("文件类型错误!");
            System.exit(0);//退出当前程序
        }
		 
	 }
	
	 sheet=wb.getSheetAt(0);  //获取当前excel工作簿中的第一个sheet
	 //获取当前sheet中有多少个行
	 int totalRowNums = sheet.getPhysicalNumberOfRows();
	 
	 for(int i =1;i<totalRowNums;i++)
	 {
		  row = sheet.getRow(i);//获取每一行
		 int totalCellNums = row.getPhysicalNumberOfCells();//获取每一行中有多少列
		 List<String> columnValuesList=new ArrayList<String>();
		 for(int k =0;k<totalCellNums;k++)
		 {
		 Cell cell = row.getCell(k);//获取每一列对象   
		 String cellValue = ExcelUtils.getFormatValue(cell);
		  //System.out.print(cellValue+"\t");
		 columnValuesList.add(k, cellValue);//将每一列上的值都转入到list容器中	 
		 }
		 seller.saveExamineeByImportExcel(columnValuesList);//每整完一行保存一次
		 //System.out.println("开始读取下一行数据:");
	 }
	 
	//重定向
		response.sendRedirect(request.getContextPath()+"/seller.do?method=query");	 
	}
	
	


/**
 * @desc  2.导出excel
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void export(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException 
{
	System.out.println("开始导出excel");
	 //调用excel处理的工具类并设置本次导出excel的相关信息
	   
	ExcelUtils.settings(response, "xx汽车租赁用户名单.xls");
   //0.接值
	String key = request.getParameter("ky");
	String seller_id=request.getParameter("clasId");
	
	System.out.println("----->"+key);
	System.out.println("----->"+seller_id);
	
	
	//1.调用M层获取到数据
	 List<Map<String, Object>> list=seller.queryExamineeAll(seller_id, key);
	
	//2.利用poi实现将获取到的数据写入到excel中
	 //2.1初始化poi的核心类
	 HSSFWorkbook workbook=new HSSFWorkbook();
	 String sheetname="xx汽车租赁用户名单sheet";

	 HSSFSheet sheet = workbook.createSheet(sheetname);
	 
	 //2.2调用poi中的相关API把数据写入到excel中
	 HSSFRow row = sheet.createRow(0);//创建第一行，即表头
	 String [] tableTop={"用户编号", "用户姓名", "用户性别", "身份证号","电话","地址","租车时间","租车价格","租车状态" };
	 //像第一行中添加数据（创建表头）
	 for(int i =0;i<tableTop.length;i++)
	 {
		 row.createCell(i).setCellValue(tableTop[i]);
	 }
	 
	 
	 //从第二行开始向里面写入数据
	 String[] columnName = { "seller_id","seller_name","seller_sex","seller_idertity","seller_phone","seller_dizhi","seller_zc_time","seller_zc_money","seller_zc_zhuangtai"};
	 for(int i =0;i<list.size();i++)
	 {
	    HSSFRow row2 = sheet.createRow(i+1);
		
	    Map<String, Object> map = list.get(i);//从list中取出数据 
		for(int k=0;k<columnName.length;k++)//将获取到的每一个map写入到一个新的row2对象中（一个row2对象即是excel表格中的一行）
		{
			sheet.autoSizeColumn(k, true);//设定表格进行自动适配，但是对中文不是特别友好
			row2.createCell(k).setCellValue((String)map.get(columnName[k]));
		}
	 }
	 ExcelUtils.setColumnAutoAdapter(sheet, list.size()+1);
	 //2.3保存
	 OutputStream outputStream = response.getOutputStream();
	 workbook.write(outputStream);
	 outputStream.close();
	
	
}

/**
 * @desc  3.保存添加页面的数据
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws FileNotFoundException 
 * @throws ClassNotFoundException 
 */
private void add(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException 
{
	//接值
	//订单编号
	String seller_id = request.getParameter("seller_id");
	//用户名
	String seller_name = request.getParameter("seller_name");
	//性别
	String seller_sex = request.getParameter("seller_sex");
	//身份证号
	String seller_idertity = request.getParameter("seller_idertity");
	//手机
	String seller_phone = request.getParameter("seller_phone");
	//地址
	String seller_dizhi = request.getParameter("seller_dizhi");
	//租车时间
	String seller_zc_time = request.getParameter("seller_zc_time");
	//租车价格
	String seller_zc_money = request.getParameter("seller_zc_money");
	//租车状态
	String seller_zc_zhuangtai = request.getParameter("seller_zc_zhuangtai");
	
	//如果用户手机，姓名，身份证号没有填写，就返回添加页面
	if(seller_phone.length()<=0||seller_name.length()<=0||seller_idertity.length()<=0)
	{
		response.sendRedirect(request.getContextPath()+"/seller.do?method=addPage");
	}
	else 
	{
		seller.add(seller_id,seller_name,seller_sex,seller_idertity,seller_phone,seller_dizhi,seller_zc_time,seller_zc_money,seller_zc_zhuangtai);
		response.sendRedirect(request.getContextPath()+"/seller.do?method=query");
	}

	
}

/**
 * @desc  4.进入添加页面
 * @param request
 * @param response
 * @throws IOException 
 * @throws ServletException 
 */
private void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	request.getRequestDispatcher("/car/seller/seller_add.jsp").forward(request,response);
}


/**
 * @desc  7.批量删除
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
	private void deleteAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		String[] ids = request.getParameterValues("ids");
		if(ids!=null)
		{
			seller.deleteAll(ids);
			response.sendRedirect(request.getContextPath()+"/seller.do?method=query");
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/seller.do?method=query");

		}
	}
/**
 * @desc  8.单条删除
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		String seller_idertity = request.getParameter("seller_idertity");
		seller.delete(seller_idertity);
		response.sendRedirect(request.getContextPath()+"/seller.do?method=query");
	}
/**
 * @desc  9.保存修改的数据	
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		
		String seller_id = request.getParameter("seller_id");
		String seller_name = request.getParameter("seller_name");
		String seller_sex = request.getParameter("seller_sex");
		String seller_phone = request.getParameter("seller_phone");
		String seller_dizhi = request.getParameter("seller_dizhi");
		String seller_zc_money = request.getParameter("seller_zc_money");
		String seller_idertity = request.getParameter("seller_idertity");
		String seller_zc_zhuangtai = request.getParameter("seller_zc_zhuangtai");
		String seller_zc_time = request.getParameter("seller_zc_time");
		
		
		
		seller.edit(seller_id,seller_name,seller_sex,seller_phone,seller_dizhi,seller_zc_money,seller_idertity,seller_zc_zhuangtai,seller_zc_time);
		response.sendRedirect(request.getContextPath()+"/seller.do?method=query");
	}
/**
 * @desc  10.进入修改页面
 * @param request
 * @param response
 * @throws IOException 
 * @throws ServletException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
	private void editpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		String seller_idertity = request.getParameter("seller_idertity");
		Map<String, Object> map = seller.editpage(seller_idertity);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/car/seller/seller_edit.jsp").forward(request, response);
	}
/**
 * @desc  11.查看全部卖家信息
 * @param request
 * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 * @throws ServletException 
 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		
		//接值
		String seller_id=request.getParameter("seller_id");
		String key = request.getParameter("key");
		
		//2.1查询身份证信息
		List<Map<String, Object>> list_examinee=seller.queryExamineeAll(seller_id,key);
		//2.2查询全部信息
		List<Map<String, Object>> list_classs=seller.query();
		
		System.out.println(list_examinee.toString());
		System.out.println(list_classs.toString());
		
		request.setAttribute("list_examinee", list_examinee);
		request.setAttribute("list_classs", list_classs);
	    request.setAttribute("key", key);
	    request.setAttribute("seller_id", seller_id);
		
		request.getRequestDispatcher("/car/seller/seller_list.jsp").forward(request, response);
		
		
	}
	
	
	
}
