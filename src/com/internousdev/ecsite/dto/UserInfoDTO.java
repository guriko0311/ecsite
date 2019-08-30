package com.internousdev.ecsite.dto;

import java.util.Date;

public class UserInfoDTO {
	public String id;
	public String loginId;
	public String loginPass;
	public String userName;
	public Date insert_date;
	private Date updated_date;

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getLoginId(){
		return loginId;
	}

	public void setLoginId(String loginId){
		this.loginId = loginId;
	}

	public String getLoginPass(){
		return loginPass;
	}

	public void setLoginPass(String loginPass){
		this.loginPass = loginPass;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public Date getInsert_date(){
		return insert_date;
	}

	public void setInsert_date(Date insert_date){
		this.insert_date = insert_date;
	}

	public Date getUpdated_date(){
		return updated_date;
	}

	public void setUpdated_date(Date updated_date){
		this.updated_date = updated_date;
	}

}
