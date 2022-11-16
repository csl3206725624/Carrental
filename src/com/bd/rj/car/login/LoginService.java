package com.bd.rj.car.login;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
/**
 * @desc   登录页面的M层
 * @author LQ
 * @time   2022-01-19
 */
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;


public class LoginService {
	
	Dao dao = new DaoImpl();
	
	
	
	
	
	
	public Map<String, Object> sjquery(String yzm, String sj) throws ClassNotFoundException, SQLException {
		System.out.println("LoginServiceImpl----手机验证码query");
		String sql = null;
		Object[] values = null;
		int[] types = null;
		if(yzm!=null){
			sql="select * from sjdl where sjdl_yzm=?  and sjdl_sj=? ";
			 types=new int[2];
			types[0]=Types.VARCHAR;
			types[1]=Types.VARCHAR;
			 values=new Object[2];
			values[0]=yzm;
			values[1]=sj;
			
		}
		
		return dao.executeQueryForMap(sql, types, values);
	}
	
	
	
	
	public Map<String, Object> query(String name, String password) throws ClassNotFoundException, SQLException {
		System.out.println("LoginServiceImpl----进入首页query");
		String sql = null;
		Object[] values = null;
		int[] types = null;
		if(password!=null){
			sql="select * from manager where manager_name=?  and manager_password=? ";
			 types=new int[2];
			types[0]=Types.VARCHAR;
			types[1]=Types.VARCHAR;
			 values=new Object[2];
			values[0]=name;
			values[1]=password;
			
		}
		
		return dao.executeQueryForMap(sql, types, values);
	}

	/**
	 * @desc  发送邮件
	 * @param username
	 * @param password
	 * @param email
	 * @param ucode
	 * @param manager_carid 
	 * @param manager_phone 
	 * @param manager_sex 
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public void save(String manager_name, String manager_password, String email, String ucode, String manager_sex, String manager_phone, String manager_carid) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException 
	{
		String  sql="  insert into manager(manager_name,manager_password,email,ucode,manager_sex,manager_phone,manager_carid)  values(?,?,?,?,?,?,?)    "; 
		
		  int [] types=new int [7];
		  types[0]=Types.VARCHAR;
		  types[1]=Types.VARCHAR;
		  types[2]=Types.VARCHAR;
		  types[3]=Types.VARCHAR;
		  types[4]=Types.VARCHAR;
		  types[5]=Types.VARCHAR;
		  types[6]=Types.VARCHAR;
		 
		  
		  Object []values=new Object[7];
		  values[0]= manager_name;
		  values[1]= manager_password;
		  values[2]= email;
		  values[3]= ucode;
		  values[4]= manager_sex;
		  values[5]= manager_phone;
		  values[6]= manager_carid;
		  
		  dao.executeUpdate(sql, types, values);
	}

	public void fas(int xinxi,String phone) throws HttpException, IOException{
		
		HttpClient client = new HttpClient();

        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");

        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码

        NameValuePair[] data = { new NameValuePair("Uid", "csl17331329736"), // 注册的用户名

               new NameValuePair("Key", "d41d8cd98f00b204e980"), // 短信密钥

               new NameValuePair("smsMob", phone), // 接收短信的手机

               new NameValuePair("smsText", "验证码：'"+xinxi+"''【汽车租赁平台】") };// 要发送的短信内容

        post.setRequestBody(data);

	 

	       client.executeMethod(post);

	       Header[] headers = post.getResponseHeaders();

	       int statusCode = post.getStatusCode();

	       System.out.println("statusCode:" + statusCode);

	       for (Header h : headers) {

	           System.out.println(h.toString());

	       }

	       String result = new String(post.getResponseBodyAsString().getBytes("gbk"));

	       System.out.println(result); // 打印返回消息状态

	 

	       post.releaseConnection();
		 

		
	}
	 /**
	  * @desc 添加手机验证码
	  * @param sjdl_yzm
	  * @param sjdl_sj
	  * @throws ClassNotFoundException
	  * @throws FileNotFoundException
	  * @throws SQLException
	  * @throws IOException
	  */
	 public void SJsave(String sjdl_yzm, String sjdl_sj) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException 
	 {
	  String  sql="  insert into sjdl(sjdl_yzm,sjdl_sj)  values(?,?)   "; 
	  
	    int [] types=new int [2];
	    types[0]=Types.VARCHAR;
	    types[1]=Types.VARCHAR;
	  
	   
	    
	    Object []values=new Object[2];
	    values[0]= sjdl_yzm;
	    values[1]= sjdl_sj;
	  
	    dao.executeUpdate(sql, types, values);
	 }

}
