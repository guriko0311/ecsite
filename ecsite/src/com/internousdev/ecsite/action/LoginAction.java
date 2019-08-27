package com.internousdev.ecsite.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.LoginDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

//LoginActionがActionSupportを継承し、SessionAwareで実装
//						↓↓
public class LoginAction extends ActionSupport implements SessionAware{

//				定義
//				↓↓
	private String loginUserId;
	private String loginPassword;
	public Map<String,Object>session;
//			インスタンス化
//				↓↓
	private LoginDAO loginDAO = new LoginDAO();
	private LoginDTO loginDTO = new LoginDTO();
	private BuyItemDAO buyItemDAO = new BuyItemDAO();

//	executeで実行し、resultにERRORが初期値で入れられ、loginDAOのgetLoginUserInfoメソッドが呼ばれloginUserId.loginPasswordの
//	値が送られ、LoginDAOに飛んで実行結果がloginDTOに返ってきて、返ってきたloginDTOをkeyのloginUserに入れて
//	sessionに格納し、sessionからkeyのloginUserを取り出して、取り出したloginUserはObject型なのでLoginDTO型に変換して、
//	もし、取得したLoginFlgがtrueならresultにSUCCESSが代入されて、buyItemDAOのBuyIteminfoメソッドが実行され、
//	buyItemDAOに飛んで、実行結果がbuyItemDTOに代入され、loginDTOからLoginId、buyItemDTOからItem_name.Id.ItemPriceを
//	keyに入れてsessionに格納し、resultがSUCCESSなのでSUCCESSがstrutsに返る
//	if文の最初がSUCCESSにならなければresultがERRORなのでERRORがreturnでstrutsに返る
//						↓↓
	public String execute(){
		String result = ERROR;
		loginDTO = loginDAO.getLoginUserInfo(loginUserId,loginPassword);
		session.put("loginUser",loginDTO);

		if(((LoginDTO)session.get("loginUser")).getLoginFlg()){
			result = SUCCESS;

			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();

			session.put("login_user_id",loginDTO.getLoginId());
			session.put("id",buyItemDTO.getId());
			session.put("buyItem_name",buyItemDTO.getItemName());
			session.put("buyItem_price",buyItemDTO.getItemPrice());

			return result;
		}

		return result;
	}
	public String getLoginUserId(){
		return loginUserId;
	}
//	login.jspから送られてきたloginUserIdをvaluestockが赤のloginUserIdに値を入れて、青のloginUserIdに代入している
//					↓↓
	public void setLoginUserId(String loginUserId){
		this.loginUserId = loginUserId;
	}
	public String getLoginPassword(){
		return loginPassword;
	}
//	login.jsp送られてきたLoginPasswordをvaluestockが赤のloginPasswordに値を入れて、青のloginPasswordに代入している
//					↓↓
	public void setLoginPassword(String loginPassword){
		this.loginPassword = loginPassword;
	}
	@Override
	public void setSession(Map<String,Object>session){
		this.session = session;
	}

}
