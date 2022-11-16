package com.bd.rj.car.commodity;
/**
 * @desc:    订单管理M层
 * @author:  WZY
 * @time:    2022年1月18日
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import com.bd.rj.dao.Dao;
import com.bd.rj.dao.DaoImpl;

public class CommodityService {
	
	Dao dao = new DaoImpl();

	/**
	 * @desc 查看数据 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public List<Map<String, Object>> queryLike(String CaPlate) throws ClassNotFoundException, SQLException {
		String sql = "SELECT DISTINCT   d.*,f.*,dan.*,c.* FROM  rent_dengji d LEFT JOIN rent_feiyong f  ON d.RId = f.RId JOIN rent_dingdan dan  ON f.RId = d.RId JOIN Car c ON dan.CaId1 = c.CaId1 WHERE d.CaPlate = ?";
		if (CaPlate == null) {
			CaPlate = "";
		}
		int[] types = new int[1];
		types[0] = Types.VARCHAR;

		Object[] values = new Object[1];
		values[0] = "%" + CaPlate + "%";
		return dao.executeQueryForList(sql, types, values);
	}

	public List<Map<String, Object>> query() throws ClassNotFoundException, SQLException {
		String sql = "SELECT DISTINCT d.*, f.*, dan.*,  c.* FROM rent_dengji d LEFT JOIN rent_feiyong f ON d.RId = f.RId  JOIN rent_dingdan dan ON dan.RId = d.RId  JOIN Car c  ON c.CaId1 = dan.CaId1";
		return dao.executeQueryForList(sql);
	}

	/**
	 * @desc 删除单条数据
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void delete(String Rid) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		String sql = "DELETE FROM car WHERE CaPlate IN(SELECT CaPlate FROM  rent_dingdan WHERE RId = ?)";
		String sql1 = "DELETE FROM rent_dingdan WHERE RId =?";
		String sql2 = "DELETE FROM rent_dengji WHERE RId =?";
		String sql3 = "DELETE FROM rent_feiyong WHERE RId =?";

		
		dao.executeUpdate(sql, new int[]{Types.VARCHAR}, new Object[]{Rid});
		dao.executeUpdate(sql1, new int[]{Types.VARCHAR}, new Object[]{Rid});
		dao.executeUpdate(sql2, new int[]{Types.VARCHAR}, new Object[]{Rid});
		dao.executeUpdate(sql3, new int[]{Types.VARCHAR}, new Object[]{Rid});
		
	}

	/**
	  * @desc 批量删除
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void deleteAll(String[] Rids) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		for (int i = 0; i < Rids.length; i++) {
			String sql = "DELETE FROM car WHERE CaPlate IN(SELECT CaPlate FROM  rent_dingdan WHERE RId = ?)";
			String sql1 = "DELETE FROM rent_dingdan WHERE RId =?";
			String sql2 = "DELETE FROM rent_dengji WHERE RId =?";
			String sql3 = "DELETE FROM rent_feiyong WHERE RId =?";
			
			dao.executeUpdate(sql, new int[]{Types.VARCHAR}, new Object[]{Rids[i]});
			dao.executeUpdate(sql1, new int[]{Types.VARCHAR}, new Object[]{Rids[i]});
			dao.executeUpdate(sql2, new int[]{Types.VARCHAR}, new Object[]{Rids[i]});
			dao.executeUpdate(sql3, new int[]{Types.VARCHAR}, new Object[]{Rids[i]});
		}
	}

}
