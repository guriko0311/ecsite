package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;

//		HomeActionはActionSupportを継承し、SessionAwareで実装する
//							↓↓
public class HomeAction extends ActionSupport implements SessionAware{

//				定義
//				↓↓
	public Map<String,Object>session;

	public String execute(){
//		executeメソッドで実行され、String型のresultにloginを代入し、returnでstrutsに返る
//								↓↓
					String result = "login";

//		もし、sessionの中にlogin_user_idがあればBuyItemDAO()とbuyItemDAOをインスタンス化し、
//		buyItemDAOのgetBuyItemInfoメソッドが呼ばれ、その実行結果をbuyItemDTOに代入し、buyItemDTOのId.ItemName.ItemPrice
//		を取得して、各keyに入れてsessionに格納し、resultがSUCCESSになり、strutsに返す
//										↓↓
					if(session.containsKey("login_user_id")){
						BuyItemDAO buyItemDAO = new BuyItemDAO();
						BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
						session.put("id",buyItemDTO.getId());
						session.put("buyItem_name",buyItemDTO.getItemName());
						session.put("buyItem_price",buyItemDTO.getItemPrice());
						session.put("buyItem_stock",buyItemDTO.getItemStock());

						result = SUCCESS;
					}
					return result;
	}

	@Override
	public void setSession(Map<String,Object>session){
		this.session = session;
	}

	public Map<String,Object>getSession(){
		return this.session;
	}

}
