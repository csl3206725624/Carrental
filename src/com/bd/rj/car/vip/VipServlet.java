package com.bd.rj.car.vip;
/**
 * @desc   供应商管理的C层接口
 * @author 张坡
 * @time   2022-01-19
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;
import com.bd.rj.dao.jiao.Norepetition;
import com.itextpdf.text.log.SysoCounter;

@WebServlet(urlPatterns="/vip.do")
@SuppressWarnings("serial")
public class VipServlet extends HttpServlet {
	VipService vipService=new VipService();
	Dao dao = new DaoImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
		System.out.println("VipServlet------会员管理service");
		try {
			String method=request.getParameter("method");
			if(method.equals("query"))
			{
				this.query(request,response);
			}
			else if(method.equals("deleteArr"))
			{
				this.deleteArr(request,response);
			}
			else if(method.equals("addPage"))
			{
				this.addPage(request,response);
			}
			else if(method.equals("add"))
			{
				this.add(request, response);
			}
			else if(method.equals("editPage"))
			{
				this.editPage(request,response);
			}
			else if(method.equals("edit"))
			{
				this.edit(request,response);
			}
			else if(method.equals("deleteOne"))
			{
				this.deleteOne(request,response);
			}
		} 
		catch (SQLException | ClassNotFoundException e)
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
	
	/**
	 * @desc  8.单个删除供应商
	 * @param request
	 * @param response
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		String CaId1=request.getParameter("CaId1");
		String sql = "delete from car where CaId1 = '"+CaId1+"'";
		String sql2 = "delete from price where CaId2 = '"+CaId1+"'";
		dao.executeUpdate(sql2);
		dao.executeUpdate(sql);
		
		response.sendRedirect(request.getContextPath()+"/vip.do?method=query");
	}


	/**
	 * @desc  7.修改供应商信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String price=request.getParameter("price");
		String CaId1=request.getParameter("CaId1");
		
		
		String sql1="update price set price='"+price+"'where CaId2= '"+CaId1+"'";
		dao.executeUpdate(sql1);
		
		
		response.sendRedirect(request.getContextPath()+"/vip.do?method=query");
		
		
		
	}
	/**
	 * @desc  6.进入修改页面
	 * @param request
	 * @param response
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void editPage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		String CaId1=request.getParameter("CaId1");
		request.setAttribute("CaId1", CaId1);
		request.getRequestDispatcher("/car/vip/vip-edit.jsp").forward(request, response);
		
	}

	/**
	 * @desc 5.添加供应商信息内容
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws InterruptedException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, InterruptedException {
		String CaModel=request.getParameter("CaModel");
		String CaId1=request.getParameter("CaId1");
		String price=request.getParameter("price");
		String CaPlate=request.getParameter("CaPlate");
		String CaName=request.getParameter("CaName");
		String CaGongYing2=request.getParameter("CaGongYing2");
		String CaColor=request.getParameter("CaColor");
		String commodity_id=request.getParameter("commodity_id");
		if(CaId1.length()<=0||CaPlate.length()<=0||price.length()<=0||CaColor.length()<=0){
			response.sendRedirect(request.getContextPath()+"/vip.do?method=addPage");
		}else {
			
			boolean ist = Norepetition.isTokenRight(request, response);
			if (!ist)//如果令牌不正确的处理
			{
				 Thread.sleep(2000);
				 response.sendRedirect(request.getContextPath()+"/vip.do?method=query");
				 System.out.println("用户重复提交....程序中止其操作");
				 return ;
			}
			
			
			//1.向chushouche 中新增一条数据
			String sql1= "INSERT INTO chushouche(CaId1,CaKc)VALUES('"+CaId1+"',60);";
			dao.executeUpdate(sql1);
			//向合作商插入一条数据
			String sql2= "INSERT INTO gy(CaGongYing2,GY_name,GY_fs,GY_Phone,GY_Address)VALUES('"+CaGongYing2+"',1,1,1,1);";
			dao.executeUpdate(sql2);
			//向商品表中添加商品id
			String sql3= "INSERT INTO commodity(commodity_id,kind_id,commodity_name,commodity_state,commodity_give_price,commodity_text,commodity_img,commodity_statr_time,commodity_end_time,seller_email)VALUES('"+commodity_id+"',1,1,1,1,1,1,1,1,1);";
			dao.executeUpdate(sql3);
			//向价格表中添加数据
			String sql4= "INSERT INTO price(price_id,commodity_id,price,CaId2)VALUES(0,'"+commodity_id+"','"+price+"','"+CaId1+"');";
			dao.executeUpdate(sql4);
			//最后添加车信息
			String sql5= "INSERT INTO car(CaId1,CaPlate,CaModel,CaColor,CaName,CaImgpath,CaState,CaGongYing1)VALUES('"+CaId1+"','"+CaPlate+"','"+CaModel+"','"+CaColor+"','"+CaName+"',1,1,'"+CaGongYing2+"');";
			dao.executeUpdate(sql5);
			
			
			//向添加汽车表信息
			
			System.out.println("添加信息成功");
			response.sendRedirect(request.getContextPath()+"/vip.do?method=query");
		}
		
	}

	/**
	 * @desc  3.进入添加供应商页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Norepetition.login(request, response,"/car/vip/vip-add.jsp");
		System.out.println("跳转+token");
		
	}

	/**
	 * @desc  2.批量删除供应商
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	private void deleteArr(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String[] array=request.getParameterValues("CaId1");
		if(array != null && array.length>0){
			vipService.delteArr(array);
		}
		
		response.sendRedirect(request.getContextPath()+"/vip.do?method=query");
	}


	/**
	 * @desc   1.查询供应商管理列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		String vip_name=request.getParameter("vip_name");
		String vip_phone=request.getParameter("vip_phone");
		String vip_idertity=request.getParameter("vip_idertity");
		List<Map<String, Object>> list=vipService.query(vip_name,vip_phone,vip_idertity);
		request.setAttribute("list", list);
		System.out.println(list);
		request.setAttribute("vip_name", vip_name);
		request.setAttribute("vip_phone", vip_phone);
		request.setAttribute("vip_idertity", vip_idertity);
		request.getRequestDispatcher("/car/vip/vip_list.jsp").forward(request, response);
		
	}
	
	

}
