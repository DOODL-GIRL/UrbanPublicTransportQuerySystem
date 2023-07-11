<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FzGG5MjHdPrs9I2ZexcKOhEAMXQ4Ve98"></script>
        <title>线路查询</title>
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
            width: 82%;
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
		    box-shadow: 20px 20px 10px 10px rgba(0, 0, 0, 0.2);
		    margin: 50px;
		    flex-direction: row; /* 水平排列 */
		}

        .header {
            font-family: "Arial", sans-serif;
            font-size: 34px;
            font-weight: bold;
            text-align: center;
            line-height: 80px;
            letter-spacing: 2px;
        }
        .wrapper {
            background-color: rgba(255, 255, 255, 0.7);
            width: 350px;
            height: 500px;
            border-radius: 15px;
            padding: 0 40px;
            border: 1px solid gray;
		    border-radius: 15px;
            margin-left: 5%;
            margin-top: 2%;
        }
        .form-header {
            font-size: 36px;
            font-weight: bold;
            text-align: center;
            line-height: 150px;
        }
        .input-item {
            display: block;
            width: 100%;
            margin-top: 20px;
            border:0;
            border-bottom: 1px solid rgb(128, 125, 125);
            padding: 10px;
            font-size: 15px;
            background-color: rgba(255, 255, 255, 0.5);
        }
        .btn {
            text-align: center;
            padding: 10px;
            width: 100%;
            margin-top: 40px;
            border:0;
            background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
            color: #fff;
            font-size: 16px;
            border-radius: 15px;
        }
        #map
        {
            background-color: #fff;
            border-radius: 15px;
            width: 650px; 
            height: 500px;
            margin-left: 5%;
            margin-right: 5%;
            margin-top: 2%;
        }
    </style>
</head>
<body>
    <div class="container">
    <div class="top-wrapper">
            <div class="header">
                <span style="font-family: 'Arial', sans-serif; letter-spacing: 3px;">线路查询</span>
            </div>
            <a class="logout-link" href="login.jsp">退出登录</a>
    </div>
    <div class="content-wrapper">
        <div class="wrapper">
            <div class="form-header">线路查询</div>
            <div class="form-wrapper">
                <form action="/UrbanPublicTransportQuerySystem/Line" method="post">
                <input type="text" name="line_name" placeholder="请输入线路名称（必填）" class="input-item">
                <% if (request.getAttribute("message") != null) { %>
    			<p style="color: red;text-indent: 0em;"><%= request.getAttribute("message") %></p>
  				<% } %>
                <select name="line_type" class="input-item">
                	<option value="bus">公交线路</option>
    				<option value="subway">地铁线路</option>
  				</select>
                
                <input type="submit" value="查询" class="btn">
                
                
                <% if (request.getAttribute("starting_time") != null) { %>
    			<p style="color: rgb(128, 125, 125); margin-top: 35px;text-indent: 0em;">运行时间：<%= request.getAttribute("starting_time") %>-<%= request.getAttribute("ending_time") %></p>
  				<% } %>
  				<% if (request.getAttribute("result_message") != null) { %>
    			<p style="color: red; margin-top: 35px;text-indent: 0em;"><%= request.getAttribute("result_message") %></p>
  				<% } %>
                
                </form>
            </div>
        </div>
        <div id="map"></div>
    </div>
    </div>
    
    
    <script type="text/javascript">
    // 获取起点经纬度
    var lng = ${stations.get(0).getLatitude()};
    var lat = ${stations.get(0).getLongitude()};
    console.log(lng);
    
    
    // 创建地图对象
    var map = new BMap.Map("map");

    // 设置中心点和缩放级别
    var point = new BMap.Point(lng,lat);
    map.centerAndZoom(point, 14);

    var marker=null;
    
    var points = [];
    
    var new_lng,new_lat,new_point;
    <c:forEach var="station" items="${stations}">
        new_lng=${station.latitude};
        new_lat=${station.longitude};
    	
    	new_point=new BMap.Point(new_lng,new_lat);
        
        marker = new BMap.Marker(new_point);
        map.addOverlay(marker);
       
    	points.push(new_point);
    </c:forEach>

    
    var polyline= new BMap.Polyline(points, {strokeColor:"blue", strokeWeight:4, strokeOpacity:0.5});
    map.addOverlay(polyline);
    
   </script>
</body>
</html>