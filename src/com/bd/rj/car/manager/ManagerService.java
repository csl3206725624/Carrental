package com.bd.rj.car.manager;
/**
 * @desc   管理员操作的M层
 * @author WZY 
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

public class ManagerService {
	
	
	Dao dao = new DaoImpl();
	
	

	/**
	 * @desc  2.添加信息
	 * @param manager_name
	 * @param manager_carid
	 * @param manager_sex
	 * @param manager_password
	 * @param manager_phone
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void save( String manager_name, String manager_carid,String manager_sex,String manager_password,String manager_phone) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql = "insert into manager values (?,?,?,?,?,?)";
		int[] types = new int[6];
		types[0] = Types.VARCHAR;
		types[1] = Types.VARCHAR;
		types[2] = Types.VARCHAR;
		types[3] = Types.VARCHAR;
		types[4] = Types.VARCHAR;
		types[5] = Types.VARCHAR;
		
		Object[] values = new Object[6];
		values[0] = 0;
		values[1] = manager_name;
		values[2] = manager_carid;
		values[3] = manager_sex;
		values[4] = manager_password;
		values[5] = manager_phone;
		dao.executeUpdate(sql, types, values);
	}
	
	
	/**
	 * @desc  3.根据管理号进行删除
	 * @param manager_id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void delteById(String manager_id) throws ClassNotFoundException, SQLException{
		dao.executeUpdate("delete from manager where manager_id='"+manager_id+"'");
	}
	
	/**
	 * @desc  4.获取指定管理员
	 * @param manager_id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map<String, Object> findById(String manager_id) throws ClassNotFoundException, SQLException{	
		return dao.executeQueryForMap(" select * from manager where manager_id='"+manager_id+"' ");
	}

	/**
	 * @desc  5.更新数据
	 * @param manager_carid
	 * @param manager_name
	 * @param manager_sex
	 * @param manager_password
	 * @param manager_phone
	 * @param manager_id
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void updateManger(String manager_carid, String manager_name, String manager_sex, String manager_password,String manager_phone,String manager_id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql = "update manager set manager_name = ?,manager_carid = ?,manager_sex = ?,manager_password = ?,manager_phone=?where manager_id =?";
		
		int[] types = new int[6];
		types[0] = Types.VARCHAR;
		types[1] = Types.VARCHAR;
		types[2] = Types.VARCHAR;
		types[3] = Types.VARCHAR;
		types[4] = Types.VARCHAR;
		types[5] = Types.VARCHAR;
		
		Object[] values = new Object[6];
		values[0] = manager_name;
		values[1] = manager_carid;
		values[2] = manager_sex;
		values[3] = manager_password;
		values[4] = manager_phone;
		values[5] = manager_id;
		dao.executeUpdate(sql, types, values);
	}
/**
 * @desc  1.查询
 * @param input_manage
 * @return
 * @throws ClassNotFoundException
 * @throws SQLException
 */
	public List<Map<String, Object>> findAllManger(String input_manage) throws ClassNotFoundException, SQLException {
		
		String sql = "  select * from manager where manager_name  like ?  ";
		if (input_manage == null) {
			input_manage = "";
		}
		int[] types = new int[1];
		types[0] = Types.VARCHAR;
		
		Object[] values = new Object[1];
		values[0] = "%" + input_manage + "%";
		return dao.executeQueryForList(sql, types, values);
	}
	
	
	
}
