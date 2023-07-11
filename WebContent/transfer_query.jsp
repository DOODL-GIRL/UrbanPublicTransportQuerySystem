<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FzGG5MjHdPrs9I2ZexcKOhEAMXQ4Ve98"></script>
        <title>换乘查询</title>
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
                <span style="font-family: 'Arial', sans-serif; letter-spacing: 3px;">换乘查询</span>
            </div>
            <a class="logout-link" href="login.jsp">退出登录</a>
    </div>
    <div class="content-wrapper">
        <div class="wrapper">
            <div class="form-header">换乘查询</div>
            <div class="form-wrapper">

                <input type="text" name="starting_station_name" id="starting_station_name" placeholder="请输入起始站点" class="input-item">
                <input type="text" name="ending_station_name" id="ending_station_name" placeholder="请输入结束站点" class="input-item">
                <input type="submit" value="查询" class="btn" onclick="tran()">

            </div>
        </div>
        <div id="map"></div>
    </div>
    </div>
    
    <script type="text/javascript">
    
    
    function tran(){
        
        
        var start=document.getElementById('starting_station_name').value;//起点
        var end= document.getElementById('ending_station_name').value;//终点
        
        var map = new BMap.Map("map");
        var transit = new BMap.TransitRoute(map, {
            renderOptions: {map: map, panel: "result"}
        });
        transit.search(start, end);//调用API换乘查询

        transit.setSearchCompleteCallback(function(results) {
            if (transit.getStatus() == BMAP_STATUS_SUCCESS) {
                var start = transit.getResults().getStart().point;
                map.setCenter(start);
            }
        });
    };
    

    
   </script>
</body>
</html>