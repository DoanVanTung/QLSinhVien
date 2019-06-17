<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
<style type="text/css">
#logreg-forms {
	width: 412px;
	margin: 10vh auto;
	background-color: #f3f3f3;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
	transition: all 0.3s cubic-bezier(.25, .8, .25, 1);
}

#logreg-forms form {
	width: 100%;
	max-width: 410px;
	padding: 15px;
	margin: auto;
}

#logreg-forms .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

#logreg-forms .form-control:focus {
	z-index: 2;
}

#logreg-forms .form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

#logreg-forms .form-signin input[type="password"] {
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

#logreg-forms .social-login {
	width: 390px;
	margin: 0 auto;
	margin-bottom: 14px;
}

#logreg-forms .social-btn {
	font-weight: 100;
	color: white;
	width: 190px;
	font-size: 0.9rem;
}

#logreg-forms a {
	display: block;
	padding-top: 10px;
	color: lightseagreen;
}

#logreg-form .lines {
	width: 200px;
	border: 1px solid red;
}

.color1 {
	background-color: #DDDDDD;
}

#logreg-forms button[type="submit"] {
	margin-top: 10px;
}

#logreg-forms .facebook-btn {
	background-color: #3C589C;
}

#logreg-forms .google-btn {
	background-color: #DF4B3B;
}

#logreg-forms .form-reset, #logreg-forms .form-signup {
	display: none;
}

#logreg-forms .form-signup .social-btn {
	width: 210px;
}

#logreg-forms .form-signup input {
	margin-bottom: 2px;
}

.form-signup .social-login {
	width: 210px !important;
	margin: 0 auto;
}

/* Mobile */
@media screen and (max-width:500px) {
	#logreg-forms {
		width: 300px;
	}
	#logreg-forms  .social-login {
		width: 200px;
		margin: 0 auto;
		margin-bottom: 10px;
	}
	#logreg-forms  .social-btn {
		font-size: 1.3rem;
		font-weight: 100;
		color: white;
		width: 200px;
		height: 56px;
	}
	#logreg-forms .social-btn:nth-child(1) {
		margin-bottom: 5px;
	}
	#logreg-forms .social-btn span {
		display: none;
	}
	#logreg-forms  .facebook-btn:after {
		content: 'Facebook';
	}
}
</style>
</head>
<body class="color1">

	<div style="background-color: powderblue;">
		<header style="display: -webkit-inline-box;">
			<img src="https://www.utc.edu.vn/themes/UTC_V2/images/main-logo.png">
		</header>
	</div>
	<div id="logreg-forms">
		<div class="form-signin">
		
			<h1 class="h3 mb-3 font-weight-normal" style="text-align: center">
			<hr>
				Đổi Mật Khẩu</h1>
		    <hr>
		    ${mess1 }
			<input type="text" id="user" class="form-control"placeholder="ID SV" >
			<input type="password" id="pass1" class="form-control" placeholder="pass cũ" >
			<input type="password" id="pass2" class="form-control" placeholder="pass mới" >  
			<input type="password" id="pass3" class="form-control" placeholder="Repeat pass mới" >

			<button class="btn btn-primary btn-block" onclick=functionInser()
				type="submit">Đổi Mật Khẩu</button>
			
			<center><a href="TrangChu1"><i class="fas fa-angle-left"></i>
				Back</a></center> 
		</div>
	</div>
	<script>

	function functionInser() { 
   	    var user=$("#user").val();
        var pass1=$("#pass1").val();
        var pass2=$("#pass2").val();
        var pass3=$("#pass3").val();
       
        var insert = user+"_"+pass1+"_"+pass2+"_"+pass3;
			$.ajax({
				url: "EditMK/"+insert,
				type: "post",
				contentType : "application/json",
	            DataType : 'json',
	            success : function(data){
				    if(data==1){
				    	alert("Đổi MK Thành Công")
					}
				    else{
				    	alert("Đổi MK Thất Bại")
					}
					location.reload(); 
			    	
			    },
			    error : function(){
					alert("error")
				}
	       });
         } 
	</script>
</body>
</html>