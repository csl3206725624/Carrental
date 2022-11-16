package com.bd.rj.car.seller;
/**
 * @desc  用户管理M层
 * @author ACER
 * @time   2022-01-20
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;

public class SellerService {
	
	Dao dao = new DaoImpl();
	
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @desc  查看全部数据
	 */
	public List<Map<String, Object>> query() throws ClassNotFoundException, SQLException 
	{
		String sql = "select * from seller";
		return dao.executeQueryForList(sql);

	}
	
	/**
	 * @desc  导入excel
	 * @param columnValuesList
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void saveExamineeByImportExcel(List<String> columnValuesList) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException 
	{
		
		List<Map<String, Object>> list_examinee_ids = dao.executeQueryForList("select * from seller where seller_id=?",new int[]{Types.VARCHAR},new Object []{columnValuesList.get(0)});
		if (list_examinee_ids.size()<=0) 
		{
			String  sql="  insert into seller values(?,?,?,?,?,?,?,?,?)  ";
			int [] types=new int[9];
			Object []values=new Object[9];
			types[0]=Types.VARCHAR;
			types[1]=Types.VARCHAR;
			types[2]=Types.VARCHAR;
			types[3]=Types.VARCHAR;
			types[4]=Types.VARCHAR;
			types[5]=Types.VARCHAR;
			types[6]=Types.VARCHAR;
			types[7]=Types.VARCHAR;
			types[8]=Types.VARCHAR;
			
			
			values[0]=columnValuesList.get(0);
			values[1]=columnValuesList.get(1);
			values[2]=columnValuesList.get(2);
			values[3]=columnValuesList.get(3);
			values[4]=columnValuesList.get(4);
			values[5]=columnValuesList.get(5);
			values[6]=columnValuesList.get(6);
			values[7]=columnValuesList.get(7);
			values[8]=columnValuesList.get(8);
		

			dao.executeUpdate(sql, types, values);
		}

		
	}

	/**
	 * @desc  条件查询
	 * @param seller_id
	 * @param key
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryExamineeAll(String seller_id, String key) throws ClassNotFoundException, SQLException {
		if (seller_id==null) 
		   {
			seller_id="";
		   } 
		
		  if (key==null) 
		  {
			key="";
		  }
		  String sql="  select * from seller where seller_id  like ?  and  seller_name like ? ";
		 
		  int [] types=new int[2];
		  types[0]=Types.VARCHAR;
		  types[1]=Types.VARCHAR;
		  
		  Object [] values=new Object[2];
		  values[0]="%"+seller_id+"%";
		  values[1]="%"+key+"%";
		  
		return dao.executeQueryForList(sql,types,values);

	}
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @desc  批量删除
	 */
	public void deleteAll(String[] ids) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		for(int i = 0;i<ids.length;i++)
		{
			
			  int [] types=new int[1];
			  types[0]=Types.VARCHAR;
			  
			  Object [] values=new Object[1];
			  values[0]= ids[i];
			
			String sql = "delete from seller where seller_idertity= ? ";
			dao.executeUpdate(sql, types, values);
		}

		
	}
	
	/**
	 * @desc  进入修改页面
	 * @param seller_idertity
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map<String, Object> editpage(String seller_idertity) throws ClassNotFoundException, SQLException {
		String sql = "select * from seller where seller_idertity= ? ";
		
		 int [] types=new int[1];
		  types[0]=Types.VARCHAR;
		  
		  Object [] values=new Object[1];
		  values[0]= seller_idertity;
		
		return dao.executeQueryForMap(sql, types, values);

	}
	
	
	
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @desc  删除单条数据
	 */
	public void delete(String seller_idertity) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException 
	{
		String sql = "delete from seller where seller_idertity= ? ";
		
		 int [] types=new int[1];
		  types[0]=Types.VARCHAR;
		  
		  Object [] values=new Object[1];
		  values[0]= seller_idertity;
		
		dao.executeUpdate(sql, types, values);
		
	}
	/**
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @desc  保存添加页面上的数据
	 */
	public void add(String seller_id, String seller_name, String seller_sex, String seller_idertity,
			String seller_phone, String seller_dizhi, String seller_zc_time, String seller_zc_money,
			String seller_zc_zhuangtai) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql=" insert into seller values(?,?,?,?,?,?,?,?,?)  ";
		
		int[] types = new int[9];
		types[0] = Types.VARCHAR;
		types[1] = Types.VARCHAR;
		types[2] = Types.VARCHAR;
		types[3] = Types.VARCHAR;
		types[4] = Types.VARCHAR;
		types[5] = Types.VARCHAR;
		types[6] = Types.VARCHAR;
		types[7] = Types.VARCHAR;
		types[8] = Types.VARCHAR;
		
		Object[] values = new Object[9];
		values[0] = seller_idertity;
		values[1] = seller_name;
		values[2] = seller_sex;
		values[3] = seller_phone;
		values[4] = seller_dizhi;
		values[5] = seller_zc_money;
		values[6] = seller_id;
		values[7]=seller_zc_zhuangtai;
		values[8]=seller_zc_time;
		dao.executeUpdate(sql, types, values);	
	}

	public void edit(String seller_id, String seller_name, String seller_sex, String seller_phone, String seller_dizhi,
			String seller_zc_money, String seller_idertity, String seller_zc_zhuangtai, String seller_zc_time) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException
	{
		String sql="update seller set seller_name=?,seller_sex=?,seller_phone=?,seller_dizhi=?,seller_zc_money=?,seller_idertity=? ,seller_zc_zhuangtai=? ,seller_zc_time=? where seller_id=? ";
		int[] types=new int[9];
		types[0]=Types.VARCHAR;
		types[1]=Types.VARCHAR;
		types[2]=Types.VARCHAR;
		types[3]=Types.VARCHAR;
		types[4]=Types.VARCHAR;
		types[5]=Types.VARCHAR;
		types[6]=Types.VARCHAR;
		types[7]=Types.VARCHAR;
		types[8]=Types.VARCHAR;
		
		Object[] values=new Object[9];
		
		values[0]=seller_name;
		values[1]=seller_sex;
		values[2]=seller_phone;
		values[3]=seller_dizhi;
		values[4]=seller_zc_money;
		values[5]=seller_idertity;
		values[6]=seller_zc_zhuangtai;
		values[7]=seller_zc_time;
		values[8]=seller_id;
	
		dao.executeUpdate(sql, types, values);
	}

	

}
