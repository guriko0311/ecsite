package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite.dto.LoginDTO;
import com.internousdev.ecsite.util.DBConnector;

public class LoginDAO {
	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();
//				インスタンス化
//					↓↓
	private LoginDTO loginDTO = new LoginDTO();

//	Actionで呼ばれたgetLoginUserInfoメソッドが実行され、Actionから送られてきた値がloginUserId.loginPasswordに入り、
//	SQLのSELECT文がsqlに代入され、PreparedStatementでSQLと認識させ、prepareStatement(sql)でSQL文があっているか確認し、
//	接続させ、preparedStatementに代入し、SQL文の?にActionから送られてきたloginUserId.loginPasswordがsetされ、
//	executeQueryでpreparedStatementのSQL文を実行し、DBから抽出したlogin_idとlogin_passがActionから送られてきた
//	loginUserIdとloginPasswordと合っていたら、resultSetに代入する
//									↓↓
	public LoginDTO getLoginUserInfo(String loginUserId,String loginPassword){
		String sql = "SELECT*FROM login_user_transaction where login_id = ? AND login_pass = ?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1,loginUserId);
			preparedStatement.setString(2,loginPassword);

			ResultSet resultSet = preparedStatement.executeQuery();

//			resultSetからlogin_id.login_pass.user_nameを取得して、loginDTOに格納し、
//			もし、resultSetから取得したlogin_idがnullじゃなければ
//			loginDTOのLoginFlgをtrueにする
//						↓↓
			if(resultSet.next()){
				loginDTO.setLoginId(resultSet.getString("login_id"));
				loginDTO.setLoginPassword(resultSet.getString("login_pass"));
				loginDTO.setUserName(resultSet.getString("user_name"));

				if(resultSet.getString("login_id")!= null){
					loginDTO.setLoginFlg(true);
				}
			}
//		SQL文でエラーがあればエラーが表示される
//					↓↓
		}catch(Exception e){
			e.printStackTrace();
		}
//		ActionのloginDTOにreturnで返す
//				↓↓
		return loginDTO;
	}

}
