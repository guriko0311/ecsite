package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite.util.DBConnector;

public class UserCreateConfirmDAO {
	private DBConnector dbConnector = new DBConnector();

	private Connection connection = dbConnector.getConnection();

	public boolean isExsitsUser(String loginId) throws SQLException{
		String sql = "select count(*) as count from login_user_transaction where login_id = ?";
		boolean result = false;
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,loginId);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				if(rs.getInt("count")>0){
					result = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return result;
	}

}
