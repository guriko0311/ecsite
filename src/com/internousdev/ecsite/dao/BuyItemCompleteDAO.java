package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;

public class BuyItemCompleteDAO {

//BuyItemCompleteActionから呼ばれ、id.total_price.count.login_user_id.payを送られ、送られてきた値が
//item_transaction_id.total_price.total_count.user_master_id.payに入れられて(型が同じなら(ここではString型)
//Actionから送られてきた値を入れる名前は一致しなくてもよい)インスタンス化し、INSERT文をsqlに代入し、
//PreparedStatementでpreparedStatementをSQL文と認識させ、prepareStatement(sql)でSQL文があっているか確認し
//DBと接続させ、、INSERT文の?にitem_transaction_id.total_price.total_count.user_master_id.pay.dateUtilの値を入れて
//executeで実行し、user_buy_item_transactionテーブルに値を入れて、エラーがあればcatchでエラーを
//表示させ、エラーがなければDBとの接続を切る
//									↓↓
public int buyItemeInfo(String item_transaction_id,String total_price,String total_count,String user_master_id,String pay) throws SQLException{

	DBConnector dbConnector = new DBConnector();
	Connection connection = dbConnector.getConnection();
	DateUtil dateUtil = new DateUtil();

	String sql = "INSERT INTO user_buy_item_transaction "
			+ "(item_transaction_id,total_price,total_count,user_master_id,pay,insert_date)VALUES(?,?,?,?,?,?)";

	int result = 0;
	try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,item_transaction_id);
			preparedStatement.setString(2,total_price);
			preparedStatement.setString(3,total_count);
			preparedStatement.setString(4,user_master_id);
			preparedStatement.setString(5,pay);
			preparedStatement.setString(6,dateUtil.getDate());
			result = preparedStatement.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return result;
}

public void stockUpdate(int count,String id) throws SQLException{

	DBConnector dbConnector = new DBConnector();
	Connection connection = dbConnector.getConnection();

	String sql = "update item_info_transaction set item_stock = item_stock - ? where id = ?";

	try{
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,count);
		ps.setString(2,id);
		ps.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}

}

}
