package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite.dto.MyPageDTO;
import com.internousdev.ecsite.util.DBConnector;

public class MyPageDAO {

//							定義
//							↓↓
	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

//	ArrayList<MyPageDTO>がDBから購入履歴を取得するためのメソッドとgetMyPageUserInfoメソッド実行し、
//	Actionから送られてきたitem_transaction_id.user_master_idの値が入れられて、ArrayListをmyPageDTOに
//	インスタンス化し、SELECT文をsqlに代入し、PreparedStatementでpreparedStatementをSQL文として
//	認識させ、preparedStatement(sql)でSQL文があっているか確認し、DBと接続させたのをpreparedStatemenに
//	代入し、SELECT文の?にActionから送られてきたitem_transaction_id.user_master_idを入れて
//	executeQueryでpareparedStetamentを実行し、実行結果がresultSetに入れられてMyPageDTOをインスタンス化し、
//	resultSetのSELECT文で抽出したid.item_name.total_price.total_count.pay.insert_dateを取得して、
//	dtoに格納し、dtoをMyPageDTOに格納しwhile文のループが終わり(抽出した件数が複数になるためwhile文で
//	ループ処理している)エラーがあればcatchでエラー画面が表示され、なければ接続を切り、Actionの
//	myPageDTOに返す
//							↓↓
	public ArrayList<MyPageDTO>getMyPageUserInfo
			(String item_transaction_id,String user_master_id) throws SQLException{
			ArrayList<MyPageDTO>myPageDTO = new ArrayList<MyPageDTO>();
			String sql =
					"SELECT "
							+ "ubit.id,iit.item_name,ubit.total_price,ubit.total_count,"
							+ "ubit.pay,ubit.insert_date"
						  + " FROM"
						  + ""
							+ " user_buy_item_transaction ubit"
						  + " LEFT JOIN "
						  + " item_info_transaction iit"
						   + " ON"
						   + " ubit.item_transaction_id = iit.id"
						  + " WHERE"
						   + " ubit.item_transaction_id = ? AND ubit.user_master_id = ?"
						   + " ORDER BY"
						   + " insert_date DESC";
	try{
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,item_transaction_id);
		preparedStatement.setString(2,user_master_id);

		ResultSet resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			MyPageDTO dto = new MyPageDTO();
			dto.setId(resultSet.getString("id"));
			dto.setItemName(resultSet.getString("item_name"));
			dto.setTotalPrice(resultSet.getString("total_price"));
			dto.setTotalCount(resultSet.getString("total_count"));
			dto.setPayment(resultSet.getString("pay"));
			dto.setInsert_date(resultSet.getString("insert_date"));
			myPageDTO.add(dto);
		}

	}catch(Exception e){
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return myPageDTO;
	}

//Actionから呼ばれてActionから送られてきた値をを入れて、DELETE文をsqlに代入し、PreparedStatementでpreparedStatementを
//SQL文として認識させ、int型のresultを定義し、もし、prepareStatement(sql)のDELETE文があっていればDBに接続いたのを
//preparedStatementに代入し、DELETE文の?にActionから送られてきたuser_buy_item_transactionとuser_master_idを入れて、
//executeUpdateでpreparedStatementに代入しているDELETE文を実行し、resultに代入し、実行したSQL文にエラーがあればエラーが
//表示され、エラーがなければDBとの接続を切り、削除した件数の数字がresult=0にUpdateで上書きされてActionに返る
//									↓↓
	public int buyItemHistoryDelete
			(String item_transaction_id,String user_master_id)throws SQLException{

			String sql  =
				"DELETE FROM "
					+ "user_buy_item_transaction"
				+ " WHERE "
					+ "	item_transaction_id = ? AND user_master_id = ?";
			PreparedStatement preparedStatement;
			int result = 0;
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,item_transaction_id);
				preparedStatement.setString(2,user_master_id);
				result = preparedStatement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				connection.close();
			}
			return result;
	}

}
