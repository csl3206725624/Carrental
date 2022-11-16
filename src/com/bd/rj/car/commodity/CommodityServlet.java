package com.bd.rj.car.commodity;
/**
 * @desc:    订单管理的c层
 * @author:  WZY
 * @time:    2022年1月18日
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
@SuppressWarnings("serial")
@WebServlet(urlPatterns="/commodity.do")
public class CommodityServlet extends HttpServlet {
	
	CommodityService service = new CommodityService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try 
		{
			String method = request.getParameter("method");
			if (method.equals("query")) 
			{
				this.query(request, response);
			}
			else if (method.equals("delete")) 
			{
				this.delete(request, response);
			} 
			else if (method.equals("deleteAll")) 
			{
				this.deleteAll(request, response);
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
	 * @desc 批量删除
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void deleteAll(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException {
		String[] ids = request.getParameterValues("delIdArray");
		System.out.println(ids.length);
		if (ids != null) {
			service.deleteAll(ids);
			response.sendRedirect(request.getContextPath() + "/commodity.do?method=query");
		} else {
			response.sendRedirect(request.getContextPath() + "/commodity.do?method=query");
		}
	}

	/**
	 * @desc 删除单条数据
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException {
		String Rid = request.getParameter("id");
		service.delete(Rid);
		response.sendRedirect(request.getContextPath() + "/commodity.do?method=query");
	}

	/**
	 * @desc 查询
	 * @param request
	 * @param response
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		List<Map<String, Object>> list = service.query();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/car/commodity/commodity_guanli.jsp").forward(request, response);
	}

}
