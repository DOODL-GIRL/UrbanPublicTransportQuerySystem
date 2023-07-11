<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        html {
            height: 100%;
            width: 100%;
        }
        body {
            height: 100%;
            width: 100%;
            display: flex;
        }
        .navigation {
            width: 15%;
            min-width: 200px;
            background: linear-gradient(to bottom, #002b36, #004c6d);
            padding: 20px;
            margin-top: 0px;
        }
        .navigation-item {
            margin-bottom: 15px;
        }
        .navigation-item a {
            display: block;
            padding: 15px;
            text-decoration: none;
            color: white;
            border-radius: 10px;
            font-family: "STZhongsong";
            letter-spacing: 2px;
            background-color: rgba(255, 255, 255, 0.3);
            transition: background-color 0.3s;
        }
        .navigation-item a:hover {
            background-color: rgba(255, 255, 255, 0.5);
        }
        .navigation-item a:active {
            background-color: rgba(255, 255, 255, 0.7);
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            transform: translateY(1px);
        }
        .navigation-item a.selected {
		    background-color: rgba(255, 255, 255, 0.7);
		    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
		    transform: translateY(1px);
		}
		.logo {
            font-size: 35px;
            color: white;
            text-align: center;
            margin-top: 20px;
            margin-bottom:50px;
            letter-spacing: 5px;
            font-weight: bold;
            font-family: "STKaiti";
        }
    </style>
    <script type="text/javascript">
	    // 在页面加载完成后执行
	    window.onload = function() {
	        // 获取当前页面的URL
	        var url = window.location.href;
	
	        // 获取导航栏的所有链接
	        var navLinks = document.getElementsByClassName('navigation-item');
	
	        // 遍历导航栏链接
	        for (var i = 0; i < navLinks.length; i++) {
	            var link = navLinks[i].getElementsByTagName('a')[0];
	
	            // 如果当前链接的URL与页面URL匹配，则设置为选中状态
	            if (link.href === url) {
	                link.classList.add('selected'); // 添加选中状态的类名
	            } else {
	                link.classList.remove('selected'); // 移除其他链接的选中状态类名
	            }
	        }
	    };
	
	    // 点击导航栏链接时执行
	    function selectNavItem(link) {
	        // 移除所有导航栏链接的选中状态类名
	        var navLinks = document.getElementsByClassName('navigation-item');
	        for (var i = 0; i < navLinks.length; i++) {
	            navLinks[i].getElementsByTagName('a')[0].classList.remove('selected');
	        }
	
	        // 将点击的链接设置为选中状态
	        link.classList.add('selected');
	    }
	</script>
    
</head>
<body>
    <div class="navigation">
    	<div class="logo">
            简行
        </div>
        <div class="navigation-item">
		    <a href="index.jsp" id="nav-home" data-title="首页" onclick="selectNavItem(this)"><img src="images/basicmessage.png" style="height: 1.5rem;width: 1.5rem;margin-bottom: -3.5%;"></img>   首  页</a>
		</div>
		<div class="navigation-item">
		    <a href="notice.jsp" id="nav-notice" data-title="公告" onclick="selectNavItem(this)"><img src="images/skill.png" style="height: 1.5rem;width: 1.5rem;margin-bottom: -3.5%;"></img>   公  告</a>
		</div>
		<div class="navigation-item">
		    <a href="station_query.jsp" id="nav-station" data-title="站点查询" onclick="selectNavItem(this)"><img src="images/remark.png" style="height: 1.5rem;width: 1.5rem;margin-bottom: -3.5%;"></img>  站点查询</a>
		</div>
		<div class="navigation-item">
		    <a href="line_query.jsp" id="nav-line" data-title="线路查询" onclick="selectNavItem(this)"><img src="images/workbag.png" style="height: 1.5rem;width: 1.5rem;margin-bottom: -3.5%;"></img>  线路查询</a>
		</div>
		<div class="navigation-item">
		    <a href="transfer_query.jsp" id="nav-transfer" data-title="换乘查询" onclick="selectNavItem(this)"><img src="images/hobby.png" style="height: 1.5rem;width: 1.5rem;margin-bottom: -3.5%;"></img>  换乘查询</a>
		</div>

    </div>
</body>
</html>
