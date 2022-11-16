package com.bd.rj.car.user;
/**
 * @desc   车辆管理的C层
 * @author MZY
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
import java.util.ArrayList;
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
import org.apache.commons.lang3.StringEscapeUtils;

import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;
import com.bd.rj.dao.jiao.Norepetition;
import com.sun.org.apache.bcel.internal.generic.Select;
@WebServlet(urlPatterns="/user.do")
@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
  public int abc=0;
  UserService user = new UserService();
  Dao dao = new DaoImpl();
	
	String path;//图片的路径
	String fileSaveName = null;//最终保存的名字
	
	
   @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String method = request.getParameter("method");
			if(method.equals("query"))
			{
				this.query(request,response);
			}
			else if(method.equals("deleteArr"))
			{
				this.deleteArr(request,response);
			}
			else  if(method.equals("addPage")) 
			{
				 this.addPage(request,response);
			}
			else if(method.equals("add"))
			{
				this.add(request,response);
			}
			else if(method.equals("editPage"))
			{
				this.editPage(request,response);
			}
			else if(method.equals("edit"))
			{
				this.edit(request,response);
			}
			else  if(method.equals("deleteOne"))
			{
				this.deleteOne(request,response);
			}
			else if(method.equals("ajaxpaixu"))
			{
				this.ajaxpaixu(request,response);
			}
			else if(method.equals("download"))
			{
				this.download(request,response);
			}
			else if(method.equals("favoritePage"))
			{
				this.favoritePage(request,response);
			}
			else if(method.equals("fanshouye"))
			{
				this.fanshouye(request,response);
			}
			else if(method.equals("deletefavorite"))
			{
				this.deletefavorite(request,response);
			}
			else if(method.equals("tofavorite"))
			{
				this.tofavorite(request,response);
			}
			else if(method.equals("favoritezan"))
			{
				this.favoritezan(request,response);
			}
			else if(method.equals("quanjing"))
			{
				this.quanjing(request,response);
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
				System.out.println(e.getMessage());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
   }
   
/**
* @desc  进入汽车全景页面
* @param request
* @param response
 * @throws IOException 
 * @throws ServletException 
*/
private void quanjing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	 request.getRequestDispatcher("/pano.autohome.com.cn/27887__c8e162b6fafe467a885428b9c0856f54.html").forward(request, response);
}
/**
    * @desc 收藏夹点赞功能
    * @param request
    * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
    */
   private void favoritezan(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
	   String Favorite_id=request.getParameter("Favorite_id");
	   
	   Map map=dao.executeQueryForMap("select Favorite_rd from myfavorite where Favorite_id= '"+Favorite_id+"'");
	  
	   int  Favorite_rd=  Integer.parseInt((String)(map.get("Favorite_rd")));
	   System.out.println("Favorite_rd");
	   System.out.println("查询到了");
	   dao.executeUpdate(" UPDATE myfavorite SET Favorite_rd='"+(Favorite_rd+1)+"' where Favorite_id= '"+Favorite_id+"'");
	   System.out.println("跟新了");
	   response.sendRedirect(request.getContextPath()+"/user.do?method=favoritePage");
		  
		
}

/**
    * @desc 添加一个收藏收藏
    * @param request
    * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 * @throws ServletException 
    */
   private void tofavorite(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
	// TODO Auto-generated method stub
	   int i = 0;
	   System.out.println("添加到收藏夹");
	  String user_id=request.getParameter("user_id");
	  Map map=dao.executeQueryForMap("select user_Model from user where user_id= '"+user_id+"'");
	  String  user_Model=(String) map.get("user_Model");
			String user_Model1;
			try {
				Map map1=dao.executeQueryForMap("select Favorite_name from myfavorite where Favorite_name= '"+user_Model+"'");
				user_Model1 = (String) map1.get("Favorite_name");
			} catch (Exception e) {
				i++;
				
				dao.executeUpdate("INSERT INTO myfavorite (Favorite_id, Favorite_name, Favorite_rd) VALUES (0, '"+user_Model+"', 6000);");
				response.sendRedirect(request.getContextPath()+"/user.do?method=query");
			}
			if(i==0)
			{
				
				response.sendRedirect(request.getContextPath()+"/user.do?method=query");
			}

}


/**
    * @desc 删除收藏夹收藏
    * @param request
    * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
    */
   private void deletefavorite(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {

	  String Favorite_id=request.getParameter("Favorite_id");
	  dao.executeUpdate("delete from myfavorite where Favorite_id = '"+Favorite_id+"'");
	  response.sendRedirect(request.getContextPath()+"/user.do?method=favoritePage");
	
}



/**
    * @desc 收藏夹返回首页
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
 * @throws SQLException 
 * @throws ClassNotFoundException 
    */
   private void fanshouye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub 
	   //查询车辆信息
	 
	   request.getRequestDispatcher("/car/index/wodeyemian.jsp").forward(request, response);
	
}
/**
    * @desc  12.跳转收藏页
    * @param request
    * @param response
 * @throws ServletException 
    * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
    */
private void favoritePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	  List list=dao.executeQueryForList("SELECT f.*,u.* FROM myfavorite f,USER u WHERE f.Favorite_name =user_Model;");
	   request.setAttribute("list", list);
	request.getRequestDispatcher("/car/favorite/favoritePageshow.jsp").forward(request, response);
}

/**
 * @desc  11.下载图片
 * @param request
 * @param response
 * @throws IOException 
 */
private void download(HttpServletRequest request, HttpServletResponse response) throws IOException 
{
	String user_Imgpath=request.getParameter("user_Imgpath");
	try {
		String path=this.getServletContext().getRealPath("/") + "WEB-INF/image/";
		//String path= "E:\\service\\";
		File file=new File(path+user_Imgpath);
		FileInputStream fileInputStream=new FileInputStream(file);
		//response.setContentType("image/jpg,png,gif"); //设置返回的文件类型 
		response.setHeader("Access-Control-Allow-Origin", "*");//设置该图片允许跨域访问
		IOUtils.copy(fileInputStream, response.getOutputStream());
	} 
	catch (Exception e)
	{

		this.downloadOutSider(request, response);
	}
}
/**
 * @desc  10.下载外链图片
 * @param request
 * @param response
 * @throws IOException 
 */
private void downloadOutSider(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String utlhtml="https://2sc2.autoimg.cn/escimg/g29/M06/93/9A/f_440x0_0_q87_autohomecar__ChwFk2HnsgOAQ75ZAALk7Kmft4E236.jpg";
    URL url=new URL(utlhtml);
    URLConnection connection = url.openConnection();
    InputStream inputStream = connection.getInputStream();
    response.setHeader("Access-Control-Allow-Origin", "*");
    IOUtils.copy(inputStream, response.getOutputStream());
	
}
/**
* @desc 9.用ajax分类排序
* @param request
* @param response
* @throws SQLException 
* @throws IOException 
* @throws ServletException 
* @throws ClassNotFoundException 
*/
private void ajaxpaixu(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException, SQLException {
	 System.out.println(" 用ajax分类排序");
	 abc=abc+1;
	 if(abc>4)
	 {
		 abc=0;
	 }
   //通过状态排序
 System.out.println("abc:"+abc);
 List<Map<String, Object>> list1=new ArrayList<Map<String,Object>>();
 List<Map<String, Object>> list2=new ArrayList<Map<String,Object>>();
 List<Map<String, Object>> list3=new ArrayList<Map<String, Object>>();
 List<Map<String, Object>> list=query1(request, response);
	 if(abc==1){
	 for(int i=0;i<list.size();i++){
		
			 if(list.get(i).get("user_staff").equals("常来往客户")){
				 list1.add(list.get(i));
			 }else{
				 list2.add(list.get(i));
			 }
		 }
	 for(int i=0;i<list1.size();i++){
		 list3.add(list1.get(i));
	 }
	 for(int i=0;i<list2.size();i++){
		 list3.add(list2.get(i));
	 }
	 request.setAttribute("list", list3);
	
	 }
		 if(abc==2){
			 for(int i=0;i<list.size();i++){
			 if(list.get(i).get("user_staff").equals("不常来往客户")){
				 list1.add(list.get(i));
			 }else{
				 list2.add(list.get(i));
			 }
		 }
			 for(int i=0;i<list1.size();i++){
				 list3.add(list1.get(i));
			 }
			 for(int i=0;i<list2.size();i++){
				 list3.add(list2.get(i));
			 }
			 request.setAttribute("list", list3);
			
		 }
		 if(abc==3){
			 for(int i=0;i<list.size();i++){
			 if(list.get(i).get("user_staff").equals("可发展长期客户")){
				 list1.add(list.get(i));
			 }else{
				 list2.add(list.get(i));
			 }
		 }
			 for(int i=0;i<list1.size();i++){
				 list3.add(list1.get(i));
			 }
			 for(int i=0;i<list2.size();i++){
				 list3.add(list2.get(i));
			 }
			 request.setAttribute("list", list3);
			
	}
		 
		 if(abc==4){
			 for(int i=0;i<list.size();i++){
			 if(list.get(i).get("user_staff").equals("不发展客户")){
				 list1.add(list.get(i));
			 }else{
				 list2.add(list.get(i));
			 }
		 }
			 for(int i=0;i<list1.size();i++){
				 list3.add(list1.get(i));
			 }
			 for(int i=0;i<list2.size();i++){
				 list3.add(list2.get(i));
			 }
			 request.setAttribute("list", list3);
			
	 }
		 if(abc==0){
			 for(int i=0;i<list.size();i++){
			
				 list1.add(list.get(i));
			 
		 }
			 for(int i=0;i<list1.size();i++){
				 list3.add(list1.get(i));
			 }
			 for(int i=0;i<list2.size();i++){
				 list3.add(list2.get(i));
			 }
			 request.setAttribute("list", list3);
			
	 }
		 
		 
  request.getRequestDispatcher("/car/user/user_list.jsp").forward(request, response);
 }

/**
* @desc  8.单个删除车辆信息
* @param request
* @param response
* @throws IOException 
* @throws SQLException 
* @throws ClassNotFoundException 
*/
private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
System.out.println("单个删除车辆信息");
String user_id=request.getParameter("user_id");
user.delteOne(user_id);
response.sendRedirect(request.getContextPath()+"/user.do?method=query");
}
/**
 * @desc  7.修改车辆信息
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws FileNotFoundException 
 * @throws ClassNotFoundException 
 * @throws FileUploadException 
 */
@SuppressWarnings("unchecked")
private void edit(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, FileUploadException {

	//设定类型和编码
	response.setContentType("text/html;charset=utf-8");
	
	//1、保存在服务器硬盘的路径
	//但是第一次运行时没有image文件夹
	path = this.getServletContext().getRealPath("/")+"WEB-INF/image";
	System.out.println("图片的路径为"+path);
	
	//2、创建服务器硬盘保存的路径(image文件夹)
	File file = new File("path");
	if(!file.exists())
	{
		file.mkdir();
	}
	
	//3、利用commons-fileuplosd.jar实现上传
	//初始化核心类
	DiskFileItemFactory factory = new DiskFileItemFactory();//管理磁盘文件
	
	//4、加载解析器
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	//5、设置解析器相关参数
	//设置编码集为utf-8
	upload.setHeaderEncoding("utf-8");
	//设置上传的大小为1MB
	//upload.setSizeMax(1024*1024);
	
	//6、解析request对象
	//把添加页面上的（名字、密码、头像）这三项看作是一个fileitem，然后装在一起，变成一个list
	List<FileItem> formFileItemList = upload.parseRequest(request);
	
	//7、上传
	if(formFileItemList.size()>0 && formFileItemList!=null)//非空判断
	{
		for (FileItem everyfileItem : formFileItemList)//遍历
		{
			if(!everyfileItem.isFormField())//如果是一个文件域
			{
				String filename = everyfileItem.getName();
				System.out.println("当前上传文件的名字为:"+filename);
				String prefix = filename.substring(filename.lastIndexOf(".")+1);
				System.out.println("本次上传文件的后缀为:"+prefix);
				
				//最终文件保存的名字
				fileSaveName = UUID.randomUUID().toString()+"."+prefix;
				System.out.println("++++++++++++++++++++"+fileSaveName);
				
				//上传
				FileUtils.copyInputStreamToFile(everyfileItem.getInputStream(), new File(path+"/"+fileSaveName));
				
			}
		}
	}	
	String user_id=guardXSS(formFileItemList.get(0).getString("utf-8"));
	String user_Plate=guardXSS(formFileItemList.get(1).getString("utf-8"));
	String user_Model=guardXSS(formFileItemList.get(2).getString("utf-8"));
	String user_Color=guardXSS(formFileItemList.get(3).getString("utf-8"));
	String user_Imgpath=fileSaveName;
	String user_State=guardXSS(formFileItemList.get(5).getString("utf-8"));
	String user_Gongying=guardXSS(formFileItemList.get(6).getString("utf-8"));
	String user_Yonghu=guardXSS(formFileItemList.get(7).getString("utf-8"));
	user.edit(user_Yonghu,user_Plate,user_Model,user_Color,user_Imgpath,user_State,user_Gongying,user_id);
	response.sendRedirect(request.getContextPath()+"/user.do?method=query");
}

/**
 * @desc 6.进入修改页面
 * @param request
 * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 * @throws ServletException 
 */
private void editPage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
	String user_id=request.getParameter("user_id");
	Map<String,Object> map=user.editPage(user_id);
	request.setAttribute("map", map);
	request.getRequestDispatcher("/car/user/user-edit.jsp").forward(request, response);
	
	
	
}

/**
 * @desc  5.添加内容
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws FileUploadException 
 * @throws InterruptedException 
 */
@SuppressWarnings("unchecked")
private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, FileUploadException, InterruptedException {
	//设定类型和编码
	
	
	
	
	
			response.setContentType("text/html;charset=utf-8");	
			//1、保存在服务器硬盘的路径
			//但是第一次运行时没有image文件夹
			path = this.getServletContext().getRealPath("/")+"WEB-INF/image";
			System.out.println("图片的路径为"+path);
			
			//2、创建服务器硬盘保存的路径(image文件夹)
			File file = new File("path");
			if(!file.exists())
			{
				file.mkdir();
			}
			
			//3、利用commons-fileuplosd.jar实现上传
			//初始化核心类
			DiskFileItemFactory factory = new DiskFileItemFactory();//管理磁盘文件
			
			//4、加载解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//5、设置解析器相关参数
			//设置编码集为utf-8
			upload.setHeaderEncoding("utf-8");
			//设置上传的大小为1MB
			//upload.setSizeMax(1024*1024);
			
			//6、解析request对象
			//把添加页面上的（名字、密码、头像）这三项看作是一个fileitem，然后装在一起，变成一个list
			List<FileItem> formFileItemList = upload.parseRequest(request);
			
			//7、上传
			if(formFileItemList.size()>0 && formFileItemList!=null)//非空判断
			{
				for (FileItem everyfileItem : formFileItemList)//遍历
				{
					if(!everyfileItem.isFormField())//如果是一个文件域
					{
						String filename = everyfileItem.getName();
						System.out.println("当前上传文件的名字为:"+filename);
						String prefix = filename.substring(filename.lastIndexOf(".")+1);
						System.out.println("本次上传文件的后缀为:"+prefix);
						
						//最终文件保存的名字
						fileSaveName = UUID.randomUUID().toString()+"."+prefix;
						System.out.println("++++++++++++++++++++"+fileSaveName);
						
						//上传
						FileUtils.copyInputStreamToFile(everyfileItem.getInputStream(), new File(path+"/"+fileSaveName));
						
					}
				}
			}
	 
			System.out.println("添加内容---------------");
			
			String user_id= guardXSS(formFileItemList.get(0).getString("utf-8"));
			String user_Plate=guardXSS(formFileItemList.get(1).getString("utf-8"));
			String user_Model=guardXSS(formFileItemList.get(2).getString("utf-8"));
			String user_Color=guardXSS(formFileItemList.get(3).getString("utf-8"));
			String user_Imgpath=fileSaveName;
			String user_State=guardXSS(formFileItemList.get(5).getString("utf-8"));
			String user_Gongying=guardXSS(formFileItemList.get(6).getString("utf-8"));
			String user_Yonghu=guardXSS(formFileItemList.get(7).getString("utf-8"));
			String token=guardXSS(formFileItemList.get(8).getString("utf-8"));
			System.out.println(user_Plate+"---------"+user_Model);
			System.out.println(user_Imgpath);
			System.out.println("token------------------------------"+token);
			
			
			if (!isTokenRight(request, response, token))//如果令牌不正确的处理
			{
				 Thread.sleep(2000);
				 response.sendRedirect(request.getContextPath()+"/user.do?method=query");
				 System.out.println("用户重复提交....程序中止其操作");
				 return ;
			}
			
			
			user.add(user_id,user_Plate,user_Model,user_Color,user_Imgpath,user_State,user_Gongying,user_Yonghu);
			response.sendRedirect(request.getContextPath()+"/user.do?method=query");
}



public static  boolean isTokenRight(HttpServletRequest request, HttpServletResponse response,String token) 
{
	//1.接收add_user.jsp页面传递过来的token值
	
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


/**
 * @desc  预防xss攻击方法
 * @param value
 * @return
 */
public  String guardXSS(String value)
{
	return StringEscapeUtils.escapeHtml4(value);
	
}

/**
 * @desc  3.进入添加页面
 * @param request
 * @param response
 * @throws IOException 
 * @throws ServletException 
 */
private void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Norepetition.login(request, response,"/car/user/user-add.jsp");
	System.out.println("跳转+token----------------------------------------------------------------");
	
}
/**
 * @desc 2.批量删除顾客信息
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void deleteArr(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
	System.out.println("批量删除车辆信息");
	String[] userArray=request.getParameterValues("userArray");	
	if(userArray != null && userArray.length>0){
		user.delteArr(userArray);
	}
	
	response.sendRedirect(request.getContextPath()+"/user.do?method=query");
}
/**
 * @desc 1.全部查询车辆页面
 * @param request
 * @param response
 * @return 
 * @throws IOException 
 * @throws ServletException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	
	System.out.println("进入车辆信息页面");
	String user_Plate=request.getParameter("user_Plate");
	String user_Model=request.getParameter("user_Model");
	String user_Color=request.getParameter("user_Color");
	String user_Imgpath=request.getParameter("user_Imgpath");
	
	List<Map<String,Object>> list=user.query(user_Plate,user_Model,user_Color,user_Imgpath);
	request.setAttribute("list", list);
	request.setAttribute("i", 1);
	request.setAttribute("user_Plate", user_Plate);
	request.setAttribute("user_Model", user_Model);
	request.setAttribute("user_Color", user_Color);	
	request.setAttribute("user_Imgpath", user_Imgpath);	
	request.getRequestDispatcher("/car/user/user_list.jsp").forward(request, response);
	
	
}
/**
 * @desc  0.进入车辆管理页面
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws SQLException
 */
private List<Map<String, Object>> query1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	String user_name=request.getParameter("user_name");
	String user_phone=request.getParameter("user_phone");
	String user_Imgpath=request.getParameter("user_Imgpath");
	List<Map<String,Object>> list=user.query(user_name,user_phone,user_Imgpath, user_Imgpath);
	request.setAttribute("list", list);
	return list;
	
}
	
	
	
	
}
