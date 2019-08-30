package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

//		BuyItemActionがActionSupportを継承し、SessionAwareが実装される
//							↓↓
public class BuyItemAction extends ActionSupport implements SessionAware{

//				定義
//				↓↓
	public Map<String,Object>session;
	private int count;
	private String pay;
	private String errorMessge;

//	executeメソッドが実行され、resultがSUCCESSになり、countの値をkeyのcountに入れてsessionに格納し、
//	keyのcountとLoginActionでsessionに格納したkeyのbuyItem_priceを取り出してString型に変換して後に
//	int型に変換してのをint CountとintPriceに代入し、intCountとintPriceを掛けた値をkeyのtotal_priceに
//	入れてsessionに格納している
//					↓↓
	public String execute(){
		String result = SUCCESS;
		session.put("count",count);
		int intCount = Integer.parseInt(session.get("count").toString());
		int intPrice = Integer.parseInt(session.get("buyItem_price").toString());
		int intStock = Integer.parseInt(session.get("buyItem_stock").toString());
		session.put("total_price",intCount*intPrice);

		if(intStock < count){
			result = ERROR;
			errorMessge = ("在庫が足りません");
		}

//		String型のpaymentを定義し、もし、送られてきたpayの値が1と等しければpaymentに現金払いを代入し、
//		paymentをkeyのpayに入れてsessionに格納し、もし、1と等しくなければpaymentにクレッジトカードを代入し、
//		keyのpayに入れてsessionに格納し、result(SUCCESS)をstrutsに返す
//					↓↓
		String payment;
		if(pay.equals("1")){
			payment = "現金払い";
			session.put("pay",payment);
		}else{
			payment = "クレッジトカード";
			session.put("pay",payment);
		}
		return result;
	}

//	BuyItem.jspから送られてきたcountがvaluestockにより赤のcountに入り青のcountに代入している
//					↓↓
	public void setCount(int count){
		this.count = count;
	}

//	BuyItem.jspから送られてきたpayがvaluestockにより赤のpayに入り青のpayに代入している
//					↓↓
	public void setPay(String pay){
		this.pay = pay;
	}


	public String getErrorMessage(){
		return errorMessge;
	}
	public void setErrorMessage(String errorMessage){
		this.errorMessge = errorMessage;
	}

	@Override
	public void setSession(Map<String,Object>session){
		this.session = session;
	}

}
