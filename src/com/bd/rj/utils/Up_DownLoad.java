package com.bd.rj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@SuppressWarnings("serial")
public class Up_DownLoad extends HttpServlet{
/**
 * @desc  获取传入数据库的路径名称
 * @param request
 * @param response
 * @return
 * @throws FileUploadException
 * @throws IOException
 */
	public String getVirtualName(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException
	{
		//设定类型和编码
		response.setContentType("text/html;charset=utf-8");
		//定义变量：
		String fileSaveName = null;//文件保存的名字
		List<FileItem> formItemsList=null;//解析结果
		//设定图片保存在服务器上的路径
		String path = this.getServletContext().getRealPath("/") + "WEB-INF/image";
	    System.out.println("图片存储路径：" + path);
	    //根据路径名创建一个 File实例其目的是为了创建存储的路径目录
        File file = new File(path);
        if (!file.exists())
        {
            file.mkdir();// 如果不存在则创建此路径的目录
        }
        //将request对象在factory工厂中最终转变成FileItem
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        ServletFileUpload upload = new ServletFileUpload(factory);
        //中文处理
        upload.setHeaderEncoding("utf-8");
        formItemsList = upload.parseRequest(request);//获取解析结果的list
        
        if(formItemsList!=null&&formItemsList.size()>0)
        {
        	for (FileItem fileItem : formItemsList) 
        	{
        		if(!fileItem.isFormField())
        		{
        			String fileName = fileItem.getName();//获取本次上传文件的名字
    				System.out.println("fileName----->" + fileName);
    				// 获取文件名后缀, 返回 "."在文件名最后出现的索引, 就是文件后缀名
    				String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
    				System.out.println("prefix----->"+prefix);
    				// id可以绑定sessionid来区分用户，同时绑定UUID实现不重复
                    String id = UUID.randomUUID().toString();
                    fileSaveName = id + "." + prefix; // id.后缀
                    System.out.println("最终在服务器保存的名字是："+fileSaveName);
                     
                    FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(path+"/"+fileSaveName));
        		}
        	}
        }
        return fileSaveName;
	}
	
	/**
	 * @desc  下载图片(使图片显示在list.jsp)
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//接值
			String manager_imgpath = request.getParameter("manager_imgpath");
			String path= this.getServletContext().getRealPath("/")+"WEB-INF/image/";
			System.out.println("path---->"+path);
			
			File file = new File(path+manager_imgpath);
			FileInputStream fileInputStream = new FileInputStream(file);
			response.setContentType("image/jpg");//设置返回的文件类型
			response.setHeader("Access-Control-Allow-Origin", "*");//设置该图片允许跨域访问
			IOUtils.copy(fileInputStream, response.getOutputStream());
		} 
		catch (FileNotFoundException e) //如果没有上传图片的话，会有一张默认的图片（项目内的图片）
		{
			String path = this.getServletContext().getRealPath("/")+"WEB-INF/image/mao.jpg";
			File file = new File(path);
			FileInputStream fileInputStream = new FileInputStream(file);
			response.setContentType("image/jpg");//设置返回的文件类型
			response.setHeader("Access-Control-Allow-Origin", "*");//设置该图片允许跨域访问
			IOUtils.copy(fileInputStream, response.getOutputStream());
//			this.downloadOutSide(request,response);//使用网络图片充当默认图片
		}
	}
/**
 * @desc  获取外链图片资源
 * @param request
 * @param response
 * @throws IOException 
 */
	private void downloadOutSide(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String imgUrl="https://www.baidu.com/img/bd_logo1.png";
		URL url=new URL(imgUrl);//java.net包下的一个类，其作用是能够让操作者操作网络资源想本地的一样
		URLConnection conn = url.openConnection();//打开与外链图片的连接
		InputStream inputStream = conn.getInputStream();//将外链资源转变为流对象
		response.setContentType("image/jpg");
		response.setHeader("Access-Control-Allow-Origin", "*");
	    IOUtils.copy(inputStream, response.getOutputStream());
	    
	}
	
}
