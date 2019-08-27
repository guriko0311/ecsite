package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements SessionAware{

	public Map<String,Object>session;

	public String execute(){

		String result = "admin";

		if(session.containsKey("admin")){
			result = "adminSUCCESS";
		}
		return result;
	}

	public void setSession(Map<String,Object>session){
		this .session = session;
	}

	public Map<String,Object>getSession(){
		return this.session;
	}
}
