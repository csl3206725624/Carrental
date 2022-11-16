package com.bd.rj.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @desc   PropertyUtils配置文件的处理工具类
 * @author ACER
 * @time   2022-01-06
 */
public class PropertyUtils {

	public static void main(String[] args) throws IOException {
		
		getPropertyInfo("mail.properties");
		
		
	}

	public static Map<String, Object> getPropertyInfo(String propertyPath) throws IOException
	{
		 Map<String, Object> map=new HashMap<String, Object>();
		 //实例化工具类
		 Properties prop=new Properties();
	     //通过类加载器将当前的配置文件进行加载
		 InputStream inputFile=PropertyUtils.class.getClassLoader().getResourceAsStream(propertyPath);
	     //将inputfile流放入到bufferedreader中
		 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputFile,"utf-8")); 
	     //进行加载
		 prop.load(bufferedReader);
	     Iterator<String> it=prop.stringPropertyNames().iterator();
	     //迭代输出
	     while(it.hasNext())
	     {
	         String key=it.next();
	         System.out.println(key+":"+prop.getProperty(key));
	         map.put(key, prop.getProperty(key));
	         
	     }
	     inputFile.close();
	     
		return map;
		
	}
	
}
