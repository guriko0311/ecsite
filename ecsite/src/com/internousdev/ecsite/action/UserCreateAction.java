package com.internousdev.ecsite.action;

import com.opensymphony.xwork2.ActionSupport;

//UserCreateActionがActionSupportを継承し、executeメソッドが実行され、SUCCESSがstrutsに返る
//					↓↓
public class UserCreateAction extends ActionSupport{
	public String execute(){
		return SUCCESS;
	}

}
