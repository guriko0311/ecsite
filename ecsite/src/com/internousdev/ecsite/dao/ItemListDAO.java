package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.ecsite.dto.ItemInfoDTO;
import com.internousdev.ecsite.util.DBConnector;

public class ItemListDAO {

	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

	public List<ItemInfoDTO>getItemList()throws SQLException{
		List<ItemInfoDTO>ItemInfoDTOList = new ArrayList<ItemInfoDTO>();
		String sql =
				"SELECT*FROM item_info_transaction ORDER BY id";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				ItemInfoDTO dto = new ItemInfoDTO();
				dto.setId(rs.getString("id"));
				dto.setItemName(rs.getString("item_Name"));
				dto.setItemPrice(rs.getString("item_price"));
				dto.setItemStock(rs.getString("item_stock"));
				dto.setInsert_date(rs.getDate("insert_date"));
				dto.setUpdate_date(rs.getDate("update_date"));
				ItemInfoDTOList.add(dto);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return ItemInfoDTOList;
	}

}
