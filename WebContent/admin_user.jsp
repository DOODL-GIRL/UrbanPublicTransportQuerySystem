<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="admin_header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FzGG5MjHdPrs9I2ZexcKOhEAMXQ4Ve98"></script>
    <title>用户管理</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        p {
            text-indent: 2em;
            line-height: 2;
        }
        ul {
            padding: 15px;
        }
        li {
            line-height: 2;
        }
        .container {
            height: 100%;
            width: 100%;
            display: flex;
            flex-direction: column;
        }

        .top-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 5px;
            background-color: black;
            margin-bottom: 10px;
            height: 80px;
        }

        .top-wrapper .header {
            font-size: 20px;
            font-weight: bold;
            color: white;
            text-align: center;
            flex: 1;
        }

        .top-wrapper .logout-link {
            font-size: 14px;
            text-decoration: none;
            color: white;
            background-color: rgba(255, 255, 255, 0.3);
            padding: 6px 8px;
            border-radius: 7px;
            letter-spacing:1px;
            margin-right:20px;
        }
        .content-wrapper {
            flex: 1;
            background-color: white;
            display: flex; 
            justify-content: center;
            align-items: center; 
            border: 3px solid gray;
            border-radius: 15px;
            box-shadow: 20px 20px 10px 10px rgba(0,0,0,0.2);
            margin: 50px;
        }
        .button_wrapper
        {
        	display: flex;
        }
        
        .table-wrapper {
            width: 900px;
            max-height: 400px;
            overflow-y: auto;
            background-color: #f7f7f7;
            border: 1px solid #e0e0e0;
            border-radius: 5px;
            padding: 20px;
            margin:20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%; /* 修改此处为100% */
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }
        th {
            background-color: #f2f2f2;
        }
        td {
            background-color: white;
        }
        .delete-button {
            background-color: #ff6961;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            cursor: pointer;
            margin-bottom: 10px;
        }
        .delete-button:hover {
            background-color: #ff5046;
        }

        .add-button {
            background-color: #87CEFA;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            cursor: pointer;
            margin-bottom: 10px;
            margin-right:20px;
        }
        
        .query-button {
            background-color: #87CEFA;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            cursor: pointer;
            margin-bottom: 10px;
            margin-right:20px;
        }

        .add-dialog {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            width: 300px;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        .add-dialog input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .add-dialog button {
            background-color: #87CEFA;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            cursor: pointer;
        }

        .add-dialog button:hover {
            background-color: #6495ED;
        }
    </style>
</head>
<body>
    <div class="container">
    	<div class="top-wrapper">
            <div class="header">
                <span style="font-family: 'Arial', sans-serif; letter-spacing: 3px;">用户管理</span>
            </div>
            <a class="logout-link" href="admin_login.jsp">退出登录</a>
        </div>
        
        <div class="content-wrapper">
            <div class="table-wrapper">
            	<div class="button_wrapper">
            		<form action="/UrbanPublicTransportQuerySystem/admin_user" method="post">
                		<input type="submit" value="查询" class="query-button">
                	</form>
					<button class="add-button">添加用户</button>
            		<button class="delete-button">删除用户</button>
            		<% if (request.getAttribute("message") != null) { %>
    				<span style="color: red;padding: 8px 16px;"><%= request.getAttribute("message") %></span>
  					<% } %>
            	</div>
                <table>
                    <tr>
                        <th>姓名</th>
                        <th>手机号</th>
                    </tr>
                    <!-- 使用 JSP 标签循环生成表格行 -->
    		    <c:forEach var="row" items="${requestScope.users}">
        		<tr>
            		<td>${row.username}</td>
            		<td>${row.phonenumber}</td>
        		</tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

    <div id="addUserDialog" class="add-dialog" style="display: none;text-align:center">
        <h2 style="margin-bottom:20px;">添加用户</h2>
        <input type="text" id="username" placeholder="用户名">
        <input type="text" id="password" placeholder="密码">
        <input type="text" id="phone_number" placeholder="手机号">
        <button id="addUserButton">确定</button>
        <button class="cancel-button">取消</button>
    </div>
    
	<div id="deleteUserDialog" class="add-dialog" style="display: none;text-align:center">
	    <h2 style="margin-bottom:20px;">删除用户</h2>
	    <input type="text" id="userId" placeholder="请输入要删除的用户名">
	    <button id="deleteUserButton">确定</button>
	    <button class="cancel-button">取消</button>
	</div>
	
	
    <script>
        document.querySelector('.add-button').addEventListener('click', function() {
            document.getElementById('addUserDialog').style.display = 'block';
        });//按“添加用户按钮”出现“添加用户”对话框

        document.querySelector('.delete-button').addEventListener('click', function() {
            document.getElementById('deleteUserDialog').style.display = 'block';
        });//按“删除用户”按钮出现“删除用户”对话框

        //添加对话框的确定按钮
        document.getElementById('addUserButton').addEventListener('click', function() {
            var username = document.getElementById('username').value;
            var password = document.getElementById('password').value;
            var phone_number = document.getElementById('phone_number').value;

            //将对话框中填写的值作为请求参数添加到URL中
            window.location.href = 'user_add?username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password) + '&phone_number=' + encodeURIComponent(phone_number);
            // Clear input fields
            document.getElementById('username').value = '';
            document.getElementById('password').value = '';
            document.getElementById('phone_number').value = '';
            

            // 清空对话框
            document.getElementById('addUserDialog').style.display = 'none';
        });
        
	     // 删除对话框确定按钮的代码
        document.getElementById('deleteUserButton').addEventListener('click', function() {
            var userId = document.getElementById('userId').value;
			
            //将对话框中填写的值作为请求参数添加到URL中
            window.location.href = 'user_delete?userId=' + encodeURIComponent(userId);
            
            // Clear input field
            document.getElementById('userId').value = '';

            // Close the dialog
            document.getElementById('deleteUserDialog').style.display = 'none';
        });
	    
        var addUserDialog = document.getElementById('addUserDialog');
        var deleteUserDialog = document.getElementById('deleteUserDialog');

        document.querySelector('.add-button').addEventListener('click', function() {
            addUserDialog.style.display = 'block';
            deleteUserDialog.style.display = 'none'; // 关闭删除用户弹窗
        });

        document.querySelector('.delete-button').addEventListener('click', function() {
            addUserDialog.style.display = 'none'; // 关闭添加用户弹窗
            deleteUserDialog.style.display = 'block';
        });
        
        //所有对话框的取消键
        var cancelButtons = document.querySelectorAll('.cancel-button');
        for (var i = 0; i < cancelButtons.length; i++) {
            cancelButtons[i].addEventListener('click', function() {
                addUserDialog.style.display = 'none';
                deleteUserDialog.style.display = 'none';
            });
        }

    </script>
</body>
</html>
