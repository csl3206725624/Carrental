package com.bd.rj.car.login;
/**
 * @desc   登陆界面的c层
 * @author LQ
 * @time   2021-01-18
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;
import com.bd.rj.utils.CodeUtils;
import com.bd.rj.utils.EmailUtils;




@SuppressWarnings("serial")
@WebServlet(urlPatterns="/login.do",asyncSupported=true)


public class LoginServlet extends HttpServlet{
	LoginService loginservice = new LoginService();
	Dao dao = new DaoImpl();
	HttpSession session;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login------service登录的c层");
	     try 
	     {
			String method=request.getParameter("method");
			 if(method.equals("shouye"))
			 {
				 this.shouye(request,response);
			 }
			 else if(method.equals("loginOut"))
			 {
				 this.loginOut(request,response);
			 }
			 else if (method.equals("phone")) 
			 {
				this.phoneLogin(request,response);
			 }
			 else if(method.equals("page"))
			 {
				 request.getRequestDispatcher("/index.jsp").forward(request, response);
			 }
			 else if(method.equals("register"))
			 {
				 this.register(request,response);
			 }
			 else if(method.equals("fenxiang"))
			 {
				 this.fenxiang(request,response);
			 }
			 else if(method.equals("sj"))
			 {
				 this.sj(request,response);
			 }
			 else if(method.equals("hq"))
			 {
				 this.hq(request,response);
			 }
			 else if(method.equals("sjdl"))
			 {
				 this.sjdl(request,response);
			 }
			
		} 
	     catch (Exception e) {
			// TODO: handle exception
		}
			
	}

	private void sjdl(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		//查询验证码是否正确
		String yzm =request.getParameter("yzm");
		String sj =request.getParameter("phone2");
		Map<String,Object> map=loginservice.sjquery(yzm, sj);
		
		if(map==null)
		{
			System.out.println("验证码或手机号错误!");
			request.setAttribute("phoner", sj);
			request.getRequestDispatcher("/car/phone/phonedl.jsp").forward(request, response);
		}
		else
		{
			Map<String,Object> map2=dao.executeQueryForMap("SELECT * FROM  manager WHERE manager_phone = '"+sj+"'");
			String manager_name1 ="manager_name";
			String manager_password1 ="manager_password";
			 String name=(String) map2.get(manager_name1);
			 String password=(String) map2.get(manager_password1 );
			 System.out.println(name);
			 System.out.println(password);
			 Map<String,Object> map3=loginservice.query(name,password);
			 
		 	System.out.println("登陆成功");
		 	//获取session，将当前登陆的用户的信息存入到session中
		 	 session = request.getSession();
		 	 session.setAttribute("user", map3);
		 	  String sessionId = session.getId();
		 	  System.out.println("sessionId:"+sessionId);
			request.getRequestDispatcher("/car/index/wodeyemian.jsp").forward(request, response);
			
		
		}
	}

	private void hq(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		//查看数据库中是否存在此用户手机号
		String phone=request.getParameter("hao");
		Map<String,Object> map=dao.executeQueryForMap("SELECT * FROM  manager WHERE manager_phone = '"+phone+"'");
		
		
		if(map==null)
		{
			System.out.println("登录失败,您尚未注册");
			request.getRequestDispatcher("/car/login/phonelogin.jsp").forward(request, response);
			
		}
		else
		{
			
		 	//有本人的记录信息
			//生成四位数验证码储存到数据库中
			Random ne=new Random();//实例化一个random的对象ne
			int x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
			
			//储存到数据库中
			dao.executeUpdate("UPDATE sjdl SET sjdl_yzm = '"+x+"' WHERE sjdl_sj = '"+phone+"'");
			
		    loginservice.fas(x, phone);
			request.setAttribute("phoner", phone);
			request.setAttribute("thedai", 1);
			request.getRequestDispatcher("/car/phone/phonedl.jsp").forward(request, response);
		}
		}


	private void sj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//跳转手机登录页面
		String phoner = "用户手机号码";
		request.setAttribute("phoner", phoner);
		request.setAttribute("thedai", 0);
			// TODO Auto-generated method stub
		request.getRequestDispatcher("/car/phone/phonedl.jsp").forward(request, response);
		}
	
	
/**
 * @desc  跳转分享页面
 * @param request
 * @param response
 * @throws IOException 
 * @throws ServletException 
 */
private void fenxiang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	
	request.getRequestDispatcher("/car/login/fenxiang.jsp").forward(request, response);
}


/**
 * @desc  发送邮件
 * @param request
 * @param response
 * @throws MessagingException 
 * @throws IOException 
 * @throws ServletException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void register(HttpServletRequest request, HttpServletResponse response) throws MessagingException, ServletException, IOException, ClassNotFoundException, SQLException 
{
System.out.println("发送邮件哦");		

	//1.接值：接user_add.jsp页面传递过来的新注册用户的信息
	String  manager_name=request.getParameter("manager_name");
	String  manager_sex=request.getParameter("manager_sex");
	String  manager_phone=request.getParameter("manager_phone");
	String  manager_carid=request.getParameter("manager_carid");
	String  email=request.getParameter("email");
	
	String  ucode=CodeUtils.createCode();//调用注册码工具类产生注册码
	String  manager_password= ucode;
  
 
	//2.保存新用户的信息，并发送邮件
	EmailUtils.createMineMessage(request, email, ucode);
	//调用M层将注册页面上的内容添加到数据库中
	loginservice.save(manager_name,manager_password,email,ucode,manager_sex,manager_phone,manager_carid);
	loginservice.SJsave(" ", manager_phone);
	//3、将注册成功的信息填入到requset中
//	request.setAttribute("message", "恭喜您已经注册成功，我们已经发了一封带了注册信息的电子邮件，请注意查收，如果没有收到，可能是网络原因，请耐心等待了！！");
  
	//4.转向
	request.getRequestDispatcher("/car/login/success.jsp").forward(request, response);
	
}

/**
 * @desc  3.跳转注册界面
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
private void phoneLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.getRequestDispatcher("/car/login/phonelogin.jsp").forward(request, response);
	
}


/**
 * @desc 2.切换账号页面
 * @param request
 * @param response
 * @throws IOException 
 * @throws ServletException 
 */
private void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("切换账号页面---loginOut");
	session.invalidate();
	request.getRequestDispatcher("/index.jsp").forward(request, response);
	
}
/**
 * @desc 1.登录成功
 * @param request
 * @param response
 * @throws IOException 
 * @throws ServletException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void shouye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	String name=request.getParameter("name");
	String password=request.getParameter("password");
	Map<String,Object> map=loginservice.query(name,password);
	if(map==null)
	{
		System.out.println("登录失败，请重新填写登录信息！");
		String error="你的密码或者用户名错误，请重新填写登录信息！";
		request.setAttribute("error", error);
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
	else
	{
	 	System.out.println("登陆成功");
	 	//获取session，将当前登陆的用户的信息存入到session中
	 	 session = request.getSession();
	 	 session.setAttribute("user", map);
	 	  String sessionId = session.getId();
	 	  System.out.println("sessionId:"+sessionId);
		request.getRequestDispatcher("/car/index/wodeyemian.jsp").forward(request, response);
	}	
	}
	
}
