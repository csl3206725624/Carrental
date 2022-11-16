package com.bd.rj.car.filter;
/**
 * @desc   设定字符集
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="CharacterEncodingFilter" ,asyncSupported=true,
urlPatterns="/*")

public class CharacterEncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//1.获取request和Response对象
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		//2.设定编码集
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//3.放行
		chain.doFilter(request, response);
		  
		  

	}

	@Override
	public void destroy() {
		
	}

}
