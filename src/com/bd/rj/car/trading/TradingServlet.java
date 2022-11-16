package com.bd.rj.car.trading;
/**
 * @desc   供应商合作的C层
 * @author 张坡
 * @time   2022-01-19
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;
import com.bd.rj.dao.execl.execl;
import com.bd.rj.dao.jiao.Norepetition;
@WebServlet(urlPatterns="/trading.do" )
@SuppressWarnings("serial")
public class TradingServlet extends HttpServlet {

	TradingService trading = new TradingService();
	Dao dao = new DaoImpl();
	execl execl = new execl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String method = request.getParameter("method");
			if(method.equals("query"))
			{
				this.query(request,response);
			}
			else if(method.equals("deleteAll"))
			{
				this.deleteAll(request,response);
			}
			else if(method.equals("add"))
			{
				this.add(request,response);
			}
			else if(method.equals("execl"))
			{
				this.execl(request,response);
			}
			else if(method.equals("cha"))
			{
				this.cha(request,response);
			}
			else if(method.equals("addPage"))
			{
				this.addPage(request,response);
			}
			
		} catch (SQLException | ClassNotFoundException e)
		{
			System.out.println("1.首先需要打印出异常信息为："+e.getMessage());
			System.out.println("2.其次是异常发生的内存地址："+e.getStackTrace());
			System.out.println("3.转向到公共错误处理页面");
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
private void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	Norepetition.login(request, response,"/car/trading/trading_add.jsp");
	System.out.println("跳转+token");
		
		
	}
private void cha(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
	
		// TODO Auto-generated method stub
	String start = request.getParameter("time_start");
	String end = request.getParameter("time_end");
	System.out.println(start);
	System.out.println(end);
	List<Map<String, Object>> list = trading.query(start,end);
	request.setAttribute("list", list);
	request.getRequestDispatcher("/car/trading/trading_list.jsp").forward(request, response);
		
	}
@SuppressWarnings("static-access")
private void execl(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
	
	List<Map<String, Object>> list = dao.executeQueryForList("select * from gy");
	execl.excelExport(list);
	response.sendRedirect(request.getContextPath()+"/trading.do?method=query");
		
	}




/**
 * @desc  添加交易记录
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws InterruptedException 
 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		String CaGongYing2 = request.getParameter("CaGongYing2");
		String GY_name = request.getParameter("GY_name");
		String GY_fs = request.getParameter("GY_fs");
		String GY_Address = request.getParameter("GY_Address");
		String GY_Phone = request.getParameter("GY_Phone");
		String GY_Time = request.getParameter("GY_Time");
		System.out.println("进入add添加");
		
		
		boolean ist = Norepetition.isTokenRight(request, response);
		if (!ist)//如果令牌不正确的处理
		{
			 Thread.sleep(2000);
			 response.sendRedirect(request.getContextPath()+"/trading.do?method=query");
			 System.out.println("用户重复提交....程序中止其操作");
			 return ;
		}
	
		trading.add(CaGongYing2,GY_name,GY_fs,GY_Phone,GY_Address,GY_Time);
		response.sendRedirect(request.getContextPath()+"/trading.do?method=query");
	}
/**
 * @desc  批量删除	
 * @param request
 * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 */
	private void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		System.out.println("a");
		String[] ids = request.getParameterValues("CaGongYing2");
		System.out.println(ids);
		if(ids!=null)
		{
			trading.deleteAll(ids);
			response.sendRedirect(request.getContextPath()+"/trading.do?method=query");
		}
		else {
			response.sendRedirect(request.getContextPath()+"/trading.do?method=query");
		}
		
		
	}
/**
 * @desc  查询全部数据
 * @param request
 * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 * @throws ServletException 
 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		List<Map<String, Object>> list = dao.executeQueryForList("select * from gy");
		request.setAttribute("list", list);
		request.getRequestDispatcher("/car/trading/trading_list.jsp").forward(request, response);
	}
	
}
