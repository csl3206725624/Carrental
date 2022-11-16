package com.bd.rj.utils;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 * 获得当前日期
 * @author wyh
 */
@SuppressWarnings("serial")
public final class Dates implements Serializable {

	/**
	 * @desc  1.两个日期相差的天数
	 * @param dateStr
	 * @param dateStr2
	 * @return
	 * @throws ParseException
	 */
	public static int differentDaysByMillionSeconds(String dateStr,String dateStr2) throws ParseException
	{
	 //1.初始化SimpleDateFormat类
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
	//2.将传入的日期准备为日期类型
	   Date fDate = format.parse(dateStr);
	   Date oDate = format.parse(dateStr2);
		
	//3.初始化日历工具类
	   Calendar calendar=Calendar.getInstance();
	   
	//4.计算两个日期相差的天数
	    calendar.setTime(fDate);
	    int day1 = calendar.get(Calendar.DAY_OF_YEAR);
		
	    calendar.setTime(oDate);
	    int day2 = calendar.get(Calendar.DAY_OF_YEAR);
		
		return day2-day1;
	}
	
	
	public static final String CurrentTime() {
		String curTime = "";
		// 格式化时间开始
		SimpleDateFormat formatter;
		java.util.Date currentDate = new java.util.Date();
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		currentDate = Calendar.getInstance().getTime();
		// 格式化时间结束
		curTime = formatter.format(currentDate);
		return curTime;
	}

	public static final String CurrentYMDTime() {
		String curTime = "";
		// 格式化时间开始
		SimpleDateFormat formatter;
		java.util.Date currentDate = new java.util.Date();
		formatter = new SimpleDateFormat("yyyy年MM月dd日");
		currentDate = Calendar.getInstance().getTime();
		// 格式化时间结束
		curTime = formatter.format(currentDate);
		return curTime;
	}

	public static final String CurrentYMDTime(String date) {
		String curTime = "";
		// 格式化时间开始
		SimpleDateFormat formatter;
		java.util.Date currentDate = new java.util.Date();
		formatter = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			currentDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 格式化时间结束
		curTime = formatter.format(currentDate);
		return curTime;
	}

	public static final String CurrentYMDHSMTime() {
		String curTime = "";
		// 格式化时间开始
		SimpleDateFormat formatter;
		java.util.Date currentDate = new java.util.Date();
		formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		currentDate = Calendar.getInstance().getTime();
		// 格式化时间结束
		curTime = formatter.format(currentDate);
		return curTime;
	}

	public static final String N() {
		String curTime = "";
		// 格式化时间开始
		SimpleDateFormat formatter;
		java.util.Date currentDate = new java.util.Date();
		formatter = new SimpleDateFormat("yyyy");
		currentDate = Calendar.getInstance().getTime();
		// 格式化时间结束
		curTime = formatter.format(currentDate);
		return curTime;
	}
}
