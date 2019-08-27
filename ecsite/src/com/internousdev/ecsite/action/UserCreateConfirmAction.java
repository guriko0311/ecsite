package com.internousdev.ecsite.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

//UserCreateConfirmActionがActionSupportを継承し、SessionAwareを実装する
//								↓↓
public class UserCreateConfirmAction extends ActionSupport implements SessionAware{

//			定義
//			↓↓
	private String loginUserId;
	private String loginPassword;
	private String userName;
	public Map<String,Object>session;
	private String errorMessage;

//	executeメソッドが実行され、結果がSUCCESSになり、もし、loginUserId.loginPassword.userNameが空じゃなければ
//	keyのloginUserId.loginpassword.userNameに入れてsessionに格納し、result(SUCCESS)がstrutsに返る
//	もし、ひとつでも空ならsetErrorMessageが呼ばれsetterのerrorMessageに""の文字列が入り
//	resultがERRORになり、strutsに返る
//					↓↓
	public String execute(){

		String result = SUCCESS;

		if(!(loginUserId.equals(""))
			&& !(loginPassword.equals(""))
			&& !(userName.equals(""))){
				session.put("loginUserId",loginUserId);
				session.put("loginPassword",loginPassword);
				session.put("userName",userName);
		}else{
			setErrorMessage("未入力の項目があります。");
			result = ERROR;
		}
		return result;
	}

	public String getLoginUserId(){
		return loginUserId;
	}

//	userCreate.jspから送られてきたloginUserIdが赤に入り、青のloginUserIdに代入している
//						↓↓
	public void setLoginUserId(String loginUserId){
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword(){
		return loginPassword;
	}

//	userCreate.jspから送られてきたloginPasswordが赤に入り、青のloginPasswordに代入している
//						↓↓
	public void setLoginPassword(String loginPassword){
		this.loginPassword = loginPassword;
	}

	public String getUserName(){
		return userName;
	}

//	userCreate.jspから送られてきたuserNameが赤に入り、青のuserNameに代入している
//						↓↓
	public void setUserName(String userName){
		this.userName = userName;
	}

	@Override
	public void setSession(Map<String,Object>session){
		this.session = session;
	}

//	青のerrorMessageをuserCreate.jspに返してる
//				↓↓
	public String getErrorMessage(){
		return errorMessage;
	}

//	setMessageメソッドで呼ばれた("")の文字列が赤のerrorMessageに入り青のerrorMessageに代入している
//						↓↓
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

}
