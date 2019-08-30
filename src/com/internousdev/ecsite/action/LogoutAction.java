package com.internousdev.ecsite.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

//LogoutActionはActionSupportを継承し、SessionAwareを実装し、Map型のsessionを定義し、executeメソッドで実行し、
//session.clear()でsessionの中に入っている情報を全て消してSUCCESSをreturnでstrutsに返す
//							↓↓
public class LogoutAction extends ActionSupport implements SessionAware{

	public Map<String,Object>session;
	public String execute(){
		session.clear();
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String,Object>session){
		this.session = session;
	}

}
