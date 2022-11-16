package com.bd.rj.car.vip;
/**
 * @desc   供应商管理的M层接口
 * @author 张坡
 * @time   2022-01-19
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;


public class VipService {
	
	Dao dao = new DaoImpl();
	
	public void delteArr(String[] array) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		if(array!=null){
			for(int i=0;i<array.length;i++){
				String sql="delete from car Where CaId1=?";
				dao.executeUpdate(sql, new int[]{Types.INTEGER}, new Object[]{array[i]});
			}
			
		}

	}

	public List<Map<String, Object>> query(String vip_name, String vip_phone, String vip_idertity) throws ClassNotFoundException, SQLException {
		String sql="select * from vip where vip_name like ? and vip_phone like ?  and vip_idertity like ?";
		if(vip_name==null){
			vip_name="";
	}
	if( vip_phone==null){
		vip_phone="";
	}
	if(vip_idertity==null){
		vip_idertity="";
	}
	
	
	String sql2="select * from (SELECT p.price,z.CaPlate,z.CaGongYing2,z.CaId1,z.CaModel,z.CaName,z.Cacolor FROM (SELECT s.CaGongYing2,c.* FROM gy s LEFT OUTER JOIN car c ON s.CaGongYing2=c.CaGongYing1)z,price p WHERE z.CaId1=p.CaId2) h where CaGongYing2 like ? and CaId1 like ?  and CaModel like ?";
	int[] types=new int[3];
	types[0]=Types.VARCHAR;
	types[1]=Types.VARCHAR;
	types[2]=Types.VARCHAR;
	Object[] values=new Object[3];
	values[0]="%"+vip_name+"%";
	values[1]="%"+vip_phone+"%";
	values[2]="%"+vip_idertity+"%";
	return dao.executeQueryForList(sql2, types, values);
	}
}



