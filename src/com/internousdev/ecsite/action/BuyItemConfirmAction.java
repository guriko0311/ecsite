package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

//BuyItemConfirmActionがActionSupportを継承し、SessionAwareを実装する
//							↓↓
public class BuyItemConfirmAction extends ActionSupport implements SessionAware{

//				定義		インスタンス化
//						↓↓
	public Map<String,Object>session;
	private BuyItemCompleteDAO buyItemCompleteDAO = new BuyItemCompleteDAO();

//	executeメソッドで実行し、buyItemCompleteDAOのbuyItemeInfoメソッドを呼び出して、sessionの中の
//	id.total_price.count.login_user_id.payを取り出しString型に変換してbuyItemeInfoに送って実行され、
//	resultがSUCCESSになり、resultをstrutsに返す
//							↓↓、
	public String execute() throws SQLException{
		int count = buyItemCompleteDAO.buyItemeInfo(
				session.get("id").toString(),
				session.get("total_price").toString(),
				session.get("count").toString(),
				session.get("login_user_id").toString(),
				session.get("pay").toString());

		if(count>0){
			buyItemCompleteDAO.stockUpdate(
					Integer.parseInt(
					session.get("count").toString()),
					session.get("id").toString());
		}
		String result = SUCCESS;
		return result;
	}

	@Override
	public void setSession(Map<String,Object>session){
		this.session = session;
	}

}
