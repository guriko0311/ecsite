package com.internousdev.ecsite.action;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.UserCreateCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

//UserCreateCompleteActionがActionSupportを継承し、SessionAwareを実装する
//						↓↓
public class UserCreateCompleteAction extends ActionSupport implements SessionAware{

//			定義		インスタンス化
//					↓↓
	private String loginUserId;
	private String loginPassword;
	private String userName;
	public Map<String,Object>session;
	private UserCreateCompleteDAO userCreateCompleteDAO = new UserCreateCompleteDAO();

//	executeメソッドで実行し、userCreateCompleteDAOのcereateUserメソッドが呼ばれloginCreateConfirmActionで
//	sessionに入れたloginUserId.loginPassword.userNameを取り出しString型に変換して、userCreateCompleteDAOに
//	送り、userCreateConfirmDAOの処理が終わり、result = SUCCESSになり、resultがreturnでstrutsに返る
//	処理にエラーがあればthrows SQLExceptionでエラー画面が表示される
//							↓↓
	public String execute() throws SQLException{
		userCreateCompleteDAO.cerateUser(session.get("loginUserId").toString(),
				session.get("loginPassword").toString(),
				session.get("userName").toString());

		String result = SUCCESS;

		return result;
	}

	public String getLoginUserId(){
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId){
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword(){
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword){
		this.loginPassword = loginPassword;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	@Override
	public void setSession(Map<String,Object>session){
		this.session = session;

	}

}
