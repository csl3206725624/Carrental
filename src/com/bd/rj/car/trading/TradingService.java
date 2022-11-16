package com.bd.rj.car.trading;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;

public class TradingService {
	Dao dao = new DaoImpl();

	public void deleteAll(String[] ids) throws ClassNotFoundException, SQLException {
		for(int i=0;i<ids.length;i++)
		{
			String sql = "delete from gy where CaGongYing2 = '"+ids[i]+"'";
			dao.executeUpdate(sql);
		}

	}

	public void add(String user_name, String user_phone, String user_staff, String user_email, String commodity_name,
			String trading_time) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException, InterruptedException {
	
			//向user表添加数据
			String sql = "insert into gy values(?,?,?,?,?,?)";
			int[] types = new int[6];
			types[0] = Types.VARCHAR;
			types[1] = Types.VARCHAR;
			types[2] = Types.VARCHAR;
			types[3] = Types.VARCHAR;
			types[4] = Types.VARCHAR;
			types[5] = Types.VARCHAR;
			Object[] values = new Object[6];
			values[0] = user_name;
			values[1] = user_phone;
			values[2] = user_staff;
			values[3] = user_email;
			values[4] = commodity_name;
			values[5] = trading_time;
			dao.executeUpdate(sql, types, values);
			System.out.println("向gy表添加数据");
			Thread.sleep(500);
		
		
	

	}

	public List<Map<String, Object>> query(String start, String end) throws ClassNotFoundException, SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String data = format.format(now);
		String a = "2000-01-01";
		if(start==null)
		{
			start=a;
		}
		else if(start=="")
		{
			start=a;
		}
		if(end==null)
		{
			end = data;
		}
		else if(end=="")
		{
			end = data;
		}
		String sql = "  select * from gy where GY_Time between '"+start+"' and '"+end+"' ";
		return dao.executeQueryForList(sql);
	}
	

}
