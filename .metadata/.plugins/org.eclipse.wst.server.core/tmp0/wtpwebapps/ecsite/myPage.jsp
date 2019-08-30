<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv = "Content-Type" content = "text/html; charset = utf-8"/>
		<meta http-equiv = "Content-Style-Type" content = "text/css"/>
		<meta http-equiv = "Content-Script-Type" content = "text/javascript"/>
		<meta http-equiv = "imagetoolbar" content = "no"/>
		<meta name = "description" content = ""/>
		<meta name = "keywords" content = ""/>
		<title>MyPage画面</title>

		<style type="text/css">
		/*========TAG LAYOUT========*/
				body{
				 margin:0;
				 padding:0;
				 line-hight:1.6;
				 letter-spacing:1px;
				 font-family:Verdana,Helvetica,sans-serif;
				 font-seze:12px;
				 color:#333;
				 background:#fff;
				}

				table{
				 text-align:center;
				 margin:0 auto;
				}
				/* ========ID LAYOUT========*/
				#top{
				 width:780px;
				 margin:30px auto;
				 border:1px solid #333;
				}

				#header{
				 width:100%;
				 height:80px;
				 background-color:black;
				}

				#main{
				 width:100%;
				 height:500px;
				 text-align:center;
				}
				#footer{
				 width:100%;
				 height:80px;
				 background-color:black;
				 cleat:both;
				}

				#text-right{
				 display:inline-block;
				 text-align: right;
				}
	    </style>
</head>
<body>
<!-- 商品を購入しているもしくは前に購入して削除いていなければmyPageList==nullにならないのでこのif文は処理されず -->
<!-- messageになにも値を入れる処理をしていないのでmessage==nullになり下の画面が表示される

削除をクリックするとdeleteFlg.値を１とMyPageActionに飛び、ActionとDAOが処理され、myPageList==nullになり
if文のご購入情報はありません。が表示され、message !=nullになるためmessageに入れられている文字列も一緒に表示される

商品購入を完了していない状態でマイページに飛ぶと値を何もDBに入れてないので下の内容が表示されるがテーブルには何も
情報はないので下の一行だけ表示され、削除をクリックするとActionとDAOか処理され、myPageList==nullにならず下の画面は
表示されず、message !=nullになるためmessageに入っている文字列が(商品情報の削除に失敗しました。)が表示される

HomeこちらをクリックするとGoHomeActionに飛び、
ログアウトこちらをクリックするとLogoutActionに飛ぶ -->
<!-- 					↓↓ -->
		<div id = "header">
				<div id = "pr">
				</div>
		</div>
		<div id = "main">
				<div id = "top">
						<p>MyPage</p>
				</div>
				<div>
				<s:if test = "myPageList == null">
						<h3>ご購入情報はありません。</h3>
				</s:if>
				<s:elseif test = "message == null">
						<h3>ご購入情報は以下になります。</h3>
						<table border = "1">
						<tr>
								<th>商品名</th>
								<th>値段</th>
								<th>購入個数</th>
								<th>支払い方法</th>
								<th>購入日</th>
						</tr>
				<s:iterator value = "myPageList">
						<tr>
							<td><s:property value = "itemName"/></td>
							<td><s:property value = "totalPrice"/><span>円</span></td>
							<td><s:property value = "totalCount"/><span>個</span></td>
							<td><s:property value = "payment"/></td>
							<td><s:property value = "insert_date"/></td>
						</tr>
				</s:iterator>
						</table>
						<s:form action = "MyPageAction">
							<input type = "hidden" name = "deleteFlg" value = "1">
							<s:submit value = "削除"/>
						</s:form>
				</s:elseif>
				<s:if test = "message != null">
						<h3><s:property value = "message"/></h3>
				</s:if>
					<div id = "text-right">
						<p>Homeへ戻る場合は<a href = '<s:url action = "GoHomeAction"/>'>
						こちら</a></p>
						<p>ログアウトする場合は<a href = '<s:url action = "LogoutAction"/>'>
						こちら</a></p>
					</div>
				</div>
		</div>
	<div id = "footer">
		<div id = "pr">
		</div>
	</div>
</body>
</html>