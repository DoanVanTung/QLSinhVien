<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Template by html.am -->
<html>
<head>
	<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
		href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet"
		href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
		<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<title>3 Column Layout</title>
	<style type="text/css">

		/* Layout */
		body {
			min-width: 630px;
		}

		#container {
			padding-left: 200px;
			
		}
		
		#container .column {
			position: relative;
			float: left;
		}
		
		#center {
			padding: 10px 20px;
			width: 100%;
		}
		
		#left {
			width: 300px;
			padding: 0 10px;
			right: 240px;
			margin-left: -100%;
			padding-left: 6%
		}
		
		#right {
			width: 130px;
			padding: 0 10px;
			margin-right: -100%;
		}
		
		#footer {
			/*clear: both;*/
			padding-top: 50%;
		}
		
		/* IE hack */
		* html #left {
			left: 150px;
		}

		/* Make the columns the same height as each other */
		#container {
			overflow: hidden;
		}

		#container .column {
			padding-bottom: 1001em;
			margin-bottom: -1000em;
		}

		/* Fix for the footer */
		* html body {
			overflow: hidden;
		}
		
		* html #footer-wrapper {
			float: left;
			position: relative;
			width: 100%;
			padding-bottom: 10010px;
			margin-bottom: -10000px;
			background: #fff;
		}

		/* Aesthetics */
		body {
			margin: 0;
			padding: 0;
			font-family:Sans-serif;
			line-height: 1.5em;
		}
		
		p {
			color: #555;
		}

		nav ul {
			list-style-type: none;
			margin: 0;
			padding: 0;
		}
		
		nav ul a {
			color: darkgreen;
			text-decoration: none;
		}

		#header, #footer {
			font-size: large;
			padding: 0.3em;
			background: #BCCE98;
		}

		#left {
			background: #DAE9BC;
		}
		#divcolumn {
			margin-top: 50%;

		}
		.xy{
			          
			     float: right;
			    
			     padding-top: 2%;
			     padding-right: 2%;
			    
			}
		
		#right {
			background: #F7FDEB;
		}

		#center {
			background: #fff;
		}

		#container .column {
			padding-top: 1em;
			height: 50%;
		}
		
	</style>
	
	<script type="text/javascript">
		/* =============================
		This script generates sample text for the body content. 
		You can remove this script and any reference to it. 
		 ============================= */
		var bodyText=["The smaller your reality, the more convinced you are that you know everything.", "If the facts don't fit the theory, change the facts.", "The past has no power over the present moment.", "This, too, will pass.", "</p><p>You will not be punished for your anger, you will be punished by your anger.", "Peace comes from within. Do not seek it without.", "<h3>Heading</h3><p>The most important moment of your life is now. The most important person in your life is the one you are with now, and the most important activity in your life is the one you are involved with now."]
		function generateText(sentenceCount){
			for (var i=0; i<sentenceCount; i++)
			document.write(bodyText[Math.floor(Math.random()*7)]+" ")
		}
	</script>	
</head>

<body>

	<div style="background-color: powderblue; ">
        <header style="display: -webkit-inline-box;"> <img
            src="https://www.utc.edu.vn/themes/UTC_V2/images/main-logo.png">
            	
     </header>
       <div class="xy">
	            <p><input class="form-inline mr-auto" type="text"  id="MaSV3" style="border-radius: 10px;width: 300px" maxlength="50" ">
                <button type="submit" class="btn btn-info" onclick=functionTimkiem()  >Tìm Kiếm</button>
                <button type="submit" class="btn btn-info" onclick=functionTrangChu()  >Trang Chủ</button>
                </p>
                </div>
    </div>
	<div id="container">

		<main id="center" class="column">
			<article>
			
				
				
			
			</article>								
		</main	>	

	
	<nav id="left" class="column">
			<h3 style=" color: red;">Danh Mục Chính</h3>
			<ul>
				<li> <a href="Sv"><button style="padding-top: 1%; margin-top:3%; width: 200px;" type="submit" class="btn btn-success" >Sinh Viên</button></a></li>
				<li> <a href="bangdiem"><button style="padding-top: 1%; margin-top:3%; width: 200px;" type="submit" class="btn btn-success">Tra Cứu Điểm</button></a></li>
				<li> <a href="KetQua_DkyHoc"><button style="padding-top: 1%; margin-top:3%; width: 200px;" type="submit" class="btn btn-success" >Tra Cứu Môn Học</button></a></li>
				<li> <a href="SubjectQ"><button style="padding-top: 1%; margin-top:3%; width: 200px;" type="submit" class="btn btn-success" >Đăng Ký Môn Học</button></a></li>
				<li> <a href="DoiMK"><button style="padding-top: 1%; margin-top:3%; width: 200px;" type="submit" class="btn btn-success" >Đổi Mật Khẩu</button></a></li>
			</ul>
	</nav>

    <div id="divcolumn">
    </div>
    </div>
</body>
</html>  