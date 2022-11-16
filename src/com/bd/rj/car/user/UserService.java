package com.bd.rj.car.user;
/**
 * @desc   车辆管理的M层
 * @author MZY
 * @time   2022-01-19
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;

public class UserService {
	
	Dao dao = new DaoImpl();
	
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @desc  查询全部
	 */
	public List<Map<String, Object>> query(String user_name, String user_phone, String user_Imgpath,
			String user_Imgpath2) throws ClassNotFoundException, SQLException {
		return dao.executeQueryForList(" select * from user ");

	}

	/**
	 * @desc  批量删除车辆信息
	 * @param userArray
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void delteArr(String[] userArray) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		System.out.println("UserServiceImpl-----批量删除车辆信息delteArr");
		for(int i=0;i<userArray.length;i++){
			String sql="delete from user where user_id=?";
			
			dao.executeUpdate(sql, new int[]{Types.VARCHAR}, new Object[]{userArray[i]});
		}

	}
	/**
	 * @desc  添加车辆信息
	 * @param user_id
	 * @param user_Plate
	 * @param user_Model
	 * @param user_Color
	 * @param user_Imgpath
	 * @param user_State
	 * @param user_Gongying
	 * @param user_Yonghu
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void add(String user_id, String user_Plate, String user_Model, String user_Color, String user_Imgpath,
			String user_State, String user_Gongying, String user_Yonghu) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		System.out.println("UserServiceImpl-----添加车辆信息delteArr");
		String sql="insert into user values(?,?,?,?,?,?,?,?) ";
		int[] types=new int[8];
		types[0]=Types.VARCHAR;
		types[1]=Types.VARCHAR;
		types[2]=Types.VARCHAR;
		types[3]=Types.VARCHAR;
		types[4]=Types.VARCHAR;
		types[5]=Types.VARCHAR;
		types[6]=Types.VARCHAR;
		types[7]=Types.VARCHAR;
		
		Object[] values=new Object[8];
		values[0]=user_id;
		values[1]=user_Plate;
		values[2]=user_Model;
		values[3]=user_Color;
		values[4]=user_Imgpath;
		values[5]=user_State;
		values[6]=user_Gongying;
		values[7]=user_Yonghu;
		
		
		
		dao.executeUpdate(sql, types, values);

	}

	/**
	 * @desc  进入修改车辆信息
	 * @param user_id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map<String, Object> editPage(String user_id) throws ClassNotFoundException, SQLException {
		System.out.println("UserServiceImpl-----进入修改车辆信息editPage");
		return dao.executeQueryForMap("select * from user where user_id='"+user_id+"'");

	}

	/**
	 * @desc  保存修改页面
	 * @param user_Yonghu
	 * @param user_Plate
	 * @param user_Model
	 * @param user_Color
	 * @param user_Imgpath
	 * @param user_State
	 * @param user_Gongying
	 * @param user_id
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void edit(String user_Yonghu, String user_Plate, String user_Model, String user_Color, String user_Imgpath,
			String user_State, String user_Gongying, String user_id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql="update user set user_Yonghu=?,user_Plate=?,user_Model=?,user_Color=?,user_Imgpath=?,user_State=? ,user_Gongying=? where user_id=? ";
		int[] types=new int[8];
		types[0]=Types.VARCHAR;
		types[1]=Types.VARCHAR;
		types[2]=Types.VARCHAR;
		types[3]=Types.VARCHAR;
		types[4]=Types.VARCHAR;
		types[5]=Types.VARCHAR;
		types[6]=Types.VARCHAR;
		types[7]=Types.VARCHAR;
		
		Object[] values=new Object[8];
		
		values[0]=user_Yonghu;
		values[1]=user_Plate;
		values[2]=user_Model;
		values[3]=user_Color;
		values[4]=user_Imgpath;
		values[5]=user_State;
		values[6]=user_Gongying;
		values[7]=user_id;
		
		dao.executeUpdate(sql, types, values);

	}
/**
 * @desc  单个删除车辆信息
 * @param user_id
 * @throws ClassNotFoundException
 * @throws SQLException
 */
	public void delteOne(String user_id) throws ClassNotFoundException, SQLException {
		dao.executeUpdate("delete from user where user_id='"+user_id+"'");

	}

	

}
