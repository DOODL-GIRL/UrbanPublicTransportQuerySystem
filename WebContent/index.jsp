<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

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
            border: 3px solid gray; /* 添加此行，设置灰色边框 */
		    border-radius: 15px; /* 添加此行，设置圆角效果 */
		    box-shadow:  20px 20px 10px 10px rgba(0,0,0,0.2); /* 添加此行，设置灰色阴影 */
		    margin:50px;
        }
        .header {
            font-family: "Arial", sans-serif;
            font-size: 34px;
            font-weight: bold;
            text-align: center;
            line-height: 80px;
            letter-spacing: 2px;
        }
        .introduction {
            background-color: #f2f2f2;
            width: 300px;
            height: 260px;
            border-radius: 15px;
            padding: 20px 20px;
            margin-left: 2%;
        }
        #map
        {
            border-radius: 15px;
            width: 550px; 
            height: 500px;
            margin-left: 30px; 
        }
    </style>
</head>
<body>
    <div class="container">
    	<div class="top-wrapper">
            <div class="header">
                <span style="font-family: 'Arial', sans-serif; letter-spacing: 3px;">首页</span>
            </div>
            <a class="logout-link" href="login.jsp">退出登录</a>
        </div>
        <div class="content-wrapper">
            <div class="introduction">
                <div class="header">网站简介</div>
                <p>
                    “简行”目前支持公交和地铁站点查询、线路查询和换乘查询功能，导入百度地图API为您提供精准定位服务。欢迎使用我们的网站，如果您有任何意见和建议也欢迎向我们反馈！
                </p>
            </div>
            <div id="map"></div>
        </div>
    </div>
    <script type="text/javascript">
    // 获取经纬度
    var lng = 121.51305;
    var lat = 31.2882;

    // 创建地图对象
    var map = new BMap.Map("map");

    // 设置中心点和缩放级别
    var point = new BMap.Point(lng, lat);
    map.centerAndZoom(point, 15);

   </script>
</body>
</html>
