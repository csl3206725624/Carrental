package com.bd.rj.utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @desc   为XSSFilter提供功能支撑的工具类
 * @author mzy
 * @time  2021-01-22
 */

public class XssTrackHttpServletRequestWrapper  extends HttpServletRequestWrapper{
	
	HttpServletRequest request;//声明一个request对象
/**	
 * @desc  重写父类中的构造器,目的是为了给上面声明的request对象赋值的
 * @param request
 */
public XssTrackHttpServletRequestWrapper(HttpServletRequest request) {
	super(request);
	this.request = request;
}
@Override//重写getParameter()就能实现防止xss攻击了
public String getParameter(String name)
{
	String value = request.getParameter(name);
	System.out.println("name:" + name + "," + value);
	if (!StringUtils.isEmpty(value)) 
	{
		// 转换Html
		System.out.println("jsp页面传递的数据原来是："+value);
		value = StringEscapeUtils.escapeHtml4(value);
		System.out.println("经过StringEscapeUtils再加工之后的数据为："+value);
	}
	return   value;
}


}
