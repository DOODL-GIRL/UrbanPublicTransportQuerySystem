<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理员界面</title>
    <style>

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

        
    </style>
</head>
<body>
		<form action="/UrbanPublicTransportQuerySystem/Admin" method="post">
			   <input type="text" name="username" placeholder="请输入用户名" class="input-item">
               <input type="password" name="password" placeholder="请输入密码" class="input-item">
               <input type="submit" value="添加" class="btn">
        </form>
</body>
</html>