package com.bd.rj.car.manager;
/**
 * @desc   管理员操作的C层
 * @author WZY 
 * @time   2022-01-19
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.jasper.tagplugins.jstl.core.Url;
@SuppressWarnings("serial")
@WebServlet(urlPatterns="/manager.do")

public class ManagerServlet extends HttpServlet {

	ManagerService service = new ManagerService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		System.out.println(method);
		try 
		{
			if ("query".equalsIgnoreCase(method))
		{
				this.queryAllManager(request, response);
		} 
			else if ("addPage".equalsIgnoreCase(method))
		{
				this.addPage(request, response);
		} 
			else if ("add".equalsIgnoreCase(method)) 
		{
				this.add(request, response);
		} 
			else if ("delete".equalsIgnoreCase(method)) 
		{
				this.deleteById(request, response);
		} 
			else if ("editPage".equalsIgnoreCase(method)) 
		{
				this.edit(request, response);
		} 
			else if ("update".equalsIgnoreCase(method)) 
		{
				this.update(request, response);
		}
		} 
		catch (SQLException | ClassNotFoundException e)
		{
			System.out.println("1.首先需要打印出异常信息为："+e.getMessage());
			System.out.println("2.其次是异常发生的内存地址："+e.getStackTrace());
			System.out.println("3.转向到公共错误处理页面");
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * @desc  1.保存修改信息
 * @param request
 * @param response
 * @throws IOException
 * @throws FileUploadException
 * @throws ClassNotFoundException
 * @throws SQLException
 */
private void update(HttpServletRequest request, HttpServletResponse response)
		throws IOException, FileUploadException, ClassNotFoundException, SQLException {
	System.out.println("开始更新数据库信息");
	// 1.接值
	String manager_id = request.getParameter("manager_id");
	String manager_carid = request.getParameter("carid");
	String manager_name = request.getParameter("name");
	String manager_sex = request.getParameter("sex");
	String manager_password = request.getParameter("password");
	String manager_phone = request.getParameter("phone");
	// 2.存值

	service.updateManger(manager_carid,manager_name,manager_sex,manager_password,manager_phone,manager_id);

	// 3.重定向

	response.sendRedirect(request.getContextPath() + "/manager.do?method=query");

}
/**
 * @desc  2.更新管理员信息
 * @param request
 * @param response
 * @throws ClassNotFoundException
 * @throws SQLException
 * @throws ServletException
 * @throws IOException
 * @throws FileUploadException
 */
private void edit(HttpServletRequest request, HttpServletResponse response)
		throws ClassNotFoundException, SQLException, ServletException, IOException, FileUploadException {

	// 1.接受更改管理员的id
	String manager_id = request.getParameter("manager_id");
	// 2.通过id 进行查找
	Map<String, Object> map = service.findById(manager_id);
	// 3.存值
	request.setAttribute("editMap", map);
	// 4.转向
	request.getRequestDispatcher("/car/manager/manager-edit.jsp").forward(request, response);
}
/**
 * @desc  3.删除
 * @param request
 * @param response
 * @throws ClassNotFoundException
 * @throws SQLException
 * @throws IOException
 */
private void deleteById(HttpServletRequest request, HttpServletResponse response)
		throws ClassNotFoundException, SQLException, IOException {
	// 1.从页面获取删除的id
	String manager_id = request.getParameter("manager_id");
	
	// 2.执行删除方法
	service.delteById(manager_id);

	// 3.重定向
	response.sendRedirect(request.getContextPath() + "/manager.do?method=query");
}
/**
 * @desc  4.添加之后保存
 * @param request
 * @param response
 * @throws ClassNotFoundException
 * @throws FileNotFoundException
 * @throws SQLException
 * @throws IOException
 */
private void add(HttpServletRequest request, HttpServletResponse response)
		throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
	String manager_carid = request.getParameter("carid");
	String manager_name = request.getParameter("name");
	String manager_sex = request.getParameter("sex");
	String manager_password = request.getParameter("password");
	String manager_phone = request.getParameter("phone");
	service.save(manager_carid,manager_name,manager_sex,manager_password,manager_phone);
	// 3.重定向
	response.sendRedirect(request.getContextPath() + "/manager.do?method=query");
}
/**
 * @desc  5.进入添加页面
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
private void addPage(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	// 转向
	request.getRequestDispatcher("/car/manager/manager-add.jsp").forward(request, response);
}
/**
 * @desc  6.查询管理员
 * @param request
 * @param response
 * @throws ClassNotFoundException
 * @throws SQLException
 * @throws ServletException
 * @throws IOException
 */
private void queryAllManager(HttpServletRequest request, HttpServletResponse response)
		throws ClassNotFoundException, SQLException, ServletException, IOException {
	
	String input_manage = request.getParameter("input_manage");
	List<Map<String, Object>> list = service.findAllManger(input_manage);
	request.setAttribute("list", list);
	request.setAttribute("input_manage", input_manage);
	request.getRequestDispatcher("/car/manager/manager_list.jsp").forward(request, response);
}

}
