package com.bd.rj.car.filter;
/**
 * @desc XSS攻击的过滤器
 * @author mzy
 * @time  2022-01-22
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
import com.bd.rj.utils.XssTrackHttpServletRequestWrapper;

@WebFilter(filterName="XSSFilter",urlPatterns="/*") 
public class XSSFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		//1.实例化XssTrackHttpServletRequestWrapper类
		XssTrackHttpServletRequestWrapper xssRequestWrapper = new XssTrackHttpServletRequestWrapper(req);
		chain.doFilter(xssRequestWrapper, response);//放行过滤器
		System.out.println("xss过滤器执行");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("过滤器初始化");
	}

}
