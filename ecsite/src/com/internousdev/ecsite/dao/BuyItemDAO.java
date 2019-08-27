package com.internousdev.ecsite.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.util.DBConnector;

public class BuyItemDAO {

//					定義	インスタンス化
//						↓↓
	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();
	private BuyItemDTO buyItemDTO = new BuyItemDTO();

//	Actionから呼ばれたBuyItemInfoメソッドが実行され、SELECT文がsqlに代入され、
//	もし、PreparedStatementでSQL文と認識させ、prepareStatement(sql)でSQL文があっていれば
//	接続させ、接続させたのをpreparedStatementに代入し、executeQueryでpreparedStatementを
//	実行し、DBからitem_info_transactionテーブルからid.item_name.item_priceを抽出し、
//	その情報をresultSetに代入し、resultSetからid.item_name.item_priceを取得して、
//	buyItemDTOに格納する
//						↓↓
	public BuyItemDTO getBuyItemInfo(){
		String sql = "SELECT id,item_name,item_price FROM item_info_transaction";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				buyItemDTO.setId(resultSet.getInt("id"));
				buyItemDTO.setItemName(resultSet.getString("item_name"));
				buyItemDTO.setItemPrice(resultSet.getString("item_price"));
			}

//		SQL関連でエラーがあればエラーを表示する
//					↓↓
		}catch(Exception e){
			e.printStackTrace();
		}

//		buyItemDTOをreturnでActionに返す
//				↓↓
		return buyItemDTO;
	}

}
