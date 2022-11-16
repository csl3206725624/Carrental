package com.bd.rj.dao.jiao;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Norepetition {
	
	
	/**
	 * @deesc 生成+转向
	 * @param request
	 * @param response
	 * @param page
	 * @throws ServletException
	 * @throws IOException
	 */
	public static  void login(HttpServletRequest request, HttpServletResponse response,String page) throws ServletException, IOException {
		
		//2.生成token
		String token=UUID.randomUUID().toString().substring(16);
		
		System.out.println("生成的token:"+token);
		
		 request.getSession().setAttribute("sessionToken", token);
		
		 request.getRequestDispatcher(page).forward(request, response);
	}
		
	
	
	/**
	 * @desc 判断方法
	 * @param request
	 * @param response
	 * @return
	 */
	public static  boolean isTokenRight(HttpServletRequest request, HttpServletResponse response) 
	{
		//1.接收add_user.jsp页面传递过来的token值
		String token=request.getParameter("token");
		
		//2.判断token是否为空
		if (token==null) 
		{
		  System.out.println("token为null,服务器端拒绝执行");
		  return false;
		}
		
		//3.取出服务器端的session作用域中的token值
		String sessionToken = (String) request.getSession().getAttribute("sessionToken");
		if (sessionToken==null)
		{
			System.out.println("sessionToken为null，服务器程序将拒绝处理用户提交的表单请求");
			return false;
		}
		
		//4.判断页面传递过来的token与服务器端的session作用域中的token值是否一样
		if (!sessionToken.equals(token)) 
		{
			System.out.println("请不要伪造token值\t"+sessionToken+"\t"+token);
			return false;
		}
	    //5.删除/移除服务器端的session作用域中的token值
		request.getSession().removeAttribute("sessionToken");
		return true;
	}

}
