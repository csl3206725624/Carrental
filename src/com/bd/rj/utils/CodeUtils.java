package com.bd.rj.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @desc   生成验证码的工具类
 * @author ACER
 * @time   2022-01-06
 */
public class CodeUtils {

	public static void main(String[] args)
	{
		for(int i = 0;i<100;i++)
		{
			createCode();
		}
	}
	
/**
 * @desc  生成验证码
 */
public static String createCode() 
{
	//定义一个数组，没有0，1是因为和o，i相似，所以舍弃掉
	String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }; 
	//把字符串数组转变为list容器
	List<String> list = Arrays.asList(beforeShuffle);
	
	//重新洗牌，重新生成一次验证码
	Collections.shuffle(list);
	
	//将结果存入到stringbuilder里面
	StringBuilder sb = new StringBuilder();
	
	//遍历取值
	for(int i = 0;i<list.size();i++)
	{
		sb.append(list.get(i));
	}
	
	String resultStr = sb.toString();
	//5-9 截取4位长度
	String result = resultStr.substring(5,9);
	System.out.println(result);
	return result;
}
}
