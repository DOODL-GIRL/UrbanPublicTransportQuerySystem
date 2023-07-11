<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册界面</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        html {
            height: 100%;
        }
        body {
            height: 100%;
        }
        .container {
            height: 100%;
            /*background-image: linear-gradient(to left,#FFFAF0,#CCFFFF);*/
            background-image:url(images/subway.gif);
            background-size: cover;
  			background-repeat: no-repeat;
 			background-position: center center;
            display:flex;
        }
        .regist-wrapper {
            background-color: rgba(255, 255, 255, 0.7);
            width: 350px;
            height: 580px;
            border-radius: 15px;
            padding: 0 50px;
            position: relative;
            left: 50%;
            top: 53%;
            transform: translate(-50%, -50%);
        }
        .header {
            font-size: 38px;
            font-weight: bold;
            text-align: center;
            line-height: 200px;
        }
        .input-item {
        	background-color: rgba(255, 255, 255, 0.5);
            display: block;
            width: 100%;
            margin-bottom: 25px;
            border: 0;
            padding: 10px;
            font-size: 15px;
        }
        .btn_group
        {
        	diaplay:flex;
        	width:100%;
        	text-align: center;
        }
        .btn {
            text-align: center;
            padding: 10px;
            width: 40%;
            margin-left:8%;
            margin:right:8%;
            margin-top: 30px;
            border:0;
            background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
            color: #fff;
            font-size: 16px;
            border-radius: 15px;
        }
        .msg {
            text-align: center;
            line-height: 88px;
        }
        a {
            text-decoration-line: none;
            color: #abc1ee;
        }
        
    </style>
</head>
<body>
    <div class="container">
        <div class="regist-wrapper">
            <div class="header">简行   注册</div>
            <div class="form-wrapper">
                <form action="/UrbanPublicTransportQuerySystem/Regist" method="post" onsubmit="submitForm(event)">
                <input type="text" name="username" placeholder="请输入用户名" class="input-item">
                <input type="password" name="password" placeholder="请输入密码" class="input-item">
                <input type="phone_number" name="phone_number" placeholder="请输入手机号码" class="input-item">
                <% if (request.getAttribute("message") != null) { %>
    			<p style="color: red;"><%= request.getAttribute("message") %></p>
  				<% } %>
                <div class="btn_group">
                	<input type="submit" value="注册" class="btn">
                	<input type="reset" value="重置" class="btn">
                </div>
                </form>
            </div>
            <div class="msg">
                已有账号?
                <a href="login.jsp">去登录</a>
            </div>
        </div>
	</div>

</body>
</html>