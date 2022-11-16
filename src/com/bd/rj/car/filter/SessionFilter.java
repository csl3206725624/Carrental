package com.bd.rj.car.filter;
/**
 * @desc   Session过滤器
 * @author LQ
 * @time   2022-01-18
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="SessionFilter",urlPatterns="/car/*",initParams={
		@WebInitParam(name="login_jsp",value="/car/index/wodeyemian.jsp")
})
public class SessionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SessionFilter初始化");
		String login_jsp = filterConfig.getInitParameter("login_jsp");
		System.out.println(login_jsp);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("SessionFilter开始过滤");
		//1.获取request和Response对象
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		
		//2.获取特权页面
		String path = request.getServletPath();
		System.out.println("path---->"+path);
		if (("/car/index/wodeyemian.jsp".equals(path))||("/login.do".equals(path))) 
		{
			chain.doFilter(request, response);
			return ;
		}
		
		//3.URL过滤：没有登陆的就不能通过
		HttpSession session = request.getSession();
		if (session.getAttribute("user")==null) 
		{
			System.out.println("您没有登陆，不能访问");
			String errorMsg="您没有登陆，不能访问";
			request.setAttribute("error", errorMsg);
			request.getRequestDispatcher("/manage/error.jsp").forward(request, response);
			return ;
		}
		else 
		{
		  System.out.println("登陆成功，放行通过");
		  chain.doFilter(request, response);
		  return ;
		}

	}

	@Override
	public void destroy() {
		System.out.println("SessionFilter销毁");

	}

}
