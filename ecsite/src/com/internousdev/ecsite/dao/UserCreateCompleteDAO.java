package com.internousdev.ecsite.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;

//Actionで呼ばれ、インスタンス化され。INSERT文がsqlに代入され、createUserメソッドが実行され、
//Actionから送られてきた情報が赤のloginUserId.loginUserPassword.userNameに入り、PreparedStatementがpreparedStatementを
//SQL文と認識させprepareStatement(sql)でsqlのINSERT文があっているか確認しDBと接続させたものをpreparedStatementに代入し、
//preparedStatementのINSERT文の?にActionから送られてきた値とインスタンス化したdateUtilがsetで入れてexecuteメソッドで
//INSERT文が実行され、DBのlogin_user_transactionテーブルに入力された情報が登録される
//					↓↓
public class UserCreateCompleteDAO {
	private DBConnector dbConnector = new DBConnector();

	private Connection connection = dbConnector.getConnection();

	private DateUtil dateUtil = new DateUtil();

	private String sql = "INSERT INTO login_user_transaction(login_id,login_pass,user_name,insert_date) VALUES(?,?,?,?)";

	public void cerateUser(String loginUserId,String loginUserPassword,String userName) throws SQLException{
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,loginUserId);
			preparedStatement.setString(2,loginUserPassword);
			preparedStatement.setString(3,userName);
			preparedStatement.setString(4,dateUtil.getDate());

			preparedStatement.execute();

//		SQL関連のエラーが発生した際に実行され、エラー画面表示される
//				↓↓
		}catch(Exception e){
			e.printStackTrace();

//		最後に接続を切る
//			↓↓
		}finally{
			connection.close();
		}
	}


}
