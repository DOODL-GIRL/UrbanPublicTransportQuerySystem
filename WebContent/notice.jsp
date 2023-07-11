<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FzGG5MjHdPrs9I2ZexcKOhEAMXQ4Ve98"></script>
    <title>首页</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .container {
            height: 100%;
            width:82%;
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
            box-shadow: 20px 20px 10px 10px rgba(0, 0, 0, 0.2);
            margin: 50px;
            overflow: auto;
            width:90%;
        }
        
        .query-button {
            background-color: #87CEFA;
            color: white;
            border: none;
            padding: 6px 8px;
            border-radius: 5px;
            cursor: pointer;
        	margin-right:20px;
        }

        .notice-wrapper {
            width: 80%;
            height: 80%;
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }

        .notice {
            background-color: #f2f2f2;
            width: 220px;
            height: 300px;
            border-radius: 15px;
            padding: 20px;
            margin: 5px;
        }

        .header-wrapper {
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            margin-top:10px;
            margin-bottom: 16px;
            height:58px;
            overflow-y: auto;
        }

        .notice-content {
            height: 140px;
            overflow-y: auto;
            padding-right: 10px;
            margin:2px;
        }

        .notice-time {
            font-size: 14px;
            font-weight: bold;
            text-align: center;
            margin-top: 16px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="top-wrapper">
            <div class="header">
                <span style="font-family: 'Arial', sans-serif; letter-spacing: 3px;">往期公告</span>
            </div>
            <form action="/UrbanPublicTransportQuerySystem/notice" method="post">
                <input type="submit" value="查看所有公告" class="query-button">
            </form>
            <a class="logout-link" href="login.jsp">退出登录</a>
        </div>
        <div class="content-wrapper">
            <div class="notice-wrapper">
                <div class="notice">
                    <div class="header-wrapper">上海地铁11号线末班车16日起延后</div>
                    <div class="notice-content">
                        <ul>
                            <li>为了配合苏州地铁11号线开通初期运营（6月16日起为试乘阶段），上海地铁11号线自6月16日起，花桥→迪士尼方向沿途各站的末班车时间均有所延后。</li>
                        </ul>
                    </div>
                    <div class="notice-time">2023年06月12日</div>
                </div>
                <!-- 使用 JSP 标签循环生成表格行 -->
    		    <c:forEach var="row" items="${requestScope.notices}">
        		    <div class="notice">
                    	<div class="header-wrapper">${row.title}</div>
                    		<div class="notice-content">
                        	<ul>
                            	<li>${row.content}</li>
                        	</ul>
                    	</div>
                    <div class="notice-time">${row.time}</div>
                	</div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
