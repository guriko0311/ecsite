package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.MyPageDAO;
import com.internousdev.ecsite.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

//MyPageActionがActionSupportを継承し、SessionAwareを実装する
//							↓↓
public class MyPageAction extends ActionSupport implements SessionAware{

//			定義		インスタンス化
//					↓↓
	public Map<String,Object> session;
	private MyPageDAO myPageDAO = new MyPageDAO();
	private ArrayList<MyPageDTO>myPageList = new ArrayList<MyPageDTO>();
	private String deleteFlg;
	private String message;

//	executeメソッドで実行しもし、sessionにlogin_user_idのkeyがなければERRORが返り、もし、deleteFlgがnullなら
//	sessionからkeyのid.login_user_idを取り出しString型に変換してitem_transaction_id.user_master_idに代入し、
//	myPageDAOをgetMyPageUserInfoメソッドで呼び出し、myPageDAOにitem_transaction_id.user_master_idの値を送り、
//	実行結果がmyPageListに代入し、resultがSUCCESSになり、resultがstrutsに返る

//	deleteFlgがnullじゃなければelse if文の処理にいき、deleteFlgの値はmyPage.jspから送られてきた１になるので
//	等しくなりelse if文が実行され、deleteメソッドが実行され、sessionからid.login_user_idが取り出されString型に
//	変換して、item_transaction_id.user_master_idに代入され、myPageDAOのbuyItemHistoryDeleteメソッドが呼ばれ
//	item_transaction_id.user_master_idが送られ、DAOから返ってきた値をint型のresに代入し、if文のres>0になるので
//	myPageList=nullになり、定義しているMessageに商品情報を正しく削除しました。がsetで入れられてresultがSUCCESSになり、
//	returnでstrutsに返る

//	テーブルになにも情報がなければ、DAOのDELETE文が実行されてもDELETEした件数が0なので0が返ってきてresに代入され、
//	res>0にならないのでelse if文に行きres==0になるので定義しているMessageに商品情報の削除に失敗しました。がsetで
//	入れて、result=SUCCESSがstrutsに返る
//							↓↓、
public String execute() throws SQLException{
	if(!session.containsKey("login_user_id")){
		return ERROR;
	}
  if(deleteFlg == null){
	  String item_transaction_id = session.get("id").toString();
	  String user_master_id = session.get("login_user_id").toString();
	  myPageList = myPageDAO.getMyPageUserInfo(item_transaction_id,user_master_id);
  }else if(deleteFlg.equals("1")){
	  				delete();
  }

  	String result = SUCCESS;
  	return result;
}

	public void delete() throws SQLException{

		String item_transaction_id = session.get("id").toString();
		String user_master_id = session.get("login_user_id").toString();

		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id,user_master_id);

		if(res>0){
			myPageList = null;
			setMessage("商品情報を正しく削除しました。");
		}else if(res == 0){
			setMessage("商品情報の削除に失敗しました。");
		}
	}

	public void setDeleteFlg(String deleteFlg)
			{	this.deleteFlg = deleteFlg;
	}
	@Override
	public void setSession(Map<String,Object>session)
			{	this.session = session;
	}
	public ArrayList<MyPageDTO>getMyPageList()
			{	return this.myPageList;
	}
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message)
			{	this.message = message;
	}

}
