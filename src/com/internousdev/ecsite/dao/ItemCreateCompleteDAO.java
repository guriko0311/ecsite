package com.internousdev.ecsite.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;

public class ItemCreateCompleteDAO {
	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();
	private DateUtil dateUtil = new DateUtil();

	private String sql = "INSERT INTO item_info_transaction(item_name,item_price,item_stock,insert_date,update_date)VALUES(?,?,?,?,now())";

	public void createItem(String itemName,int itemPrice,int itemStock)throws SQLException{
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,itemName);
			ps.setInt(2,itemPrice);
			ps.setInt(3,itemStock);
			ps.setString(4,dateUtil.getDate());

			ps.execute();

		}catch(Exception e){
			e.printStackTrace();

		}finally{
			connection.close();
		}
	}

}
