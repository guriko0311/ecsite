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
		<title>商品追加完了画面</title>

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
	    </style>
</head>
<body>

		<div id = "header">
				<div id = "pr">
				</div>
		</div>
		<div id = "main">
				<div id = "top">
						<p>ItemCreateComplete</p>
				</div>
				<div>
						<h3>商品の追加が完了しました。</h3>
						<div>
						<a href = '<s:url action = "GoHomeAction"/>'>ホームに戻る</a>
						<br>
						<br>
						<a href = '<s:url action = "AdminAction"/>'>管理者TOPへ</a>
						</div>
				</div>
		</div>
		<div id = "footer">
				<div id = "pr">
				</div>
		</div>
</body>
</html>