<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Template by html.am -->
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Đăng Ký Môn Học</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>

</head>

<body>

	<div style="background-color: powderblue;">
		<header style="display: -webkit-inline-box;">
			<img src="https://www.utc.edu.vn/themes/UTC_V2/images/main-logo.png">

		</header>
	</div>
	<div id="container">

		<h2 align="center">Danh Sách Môn Học Đã Đăng Kí</h2>
		<p align="center">Mã Sinh Viên : ${sessionScope.user }</p>
		
		
		<p align="center">Tên Sinh Viên:<b><label id="getName"  ></label></b></p>
		
		
		
		


		<table class="table clearfix"
			style="width: 1200px; height: auto; margin: auto;" id="table">


			<thead class="thead-dark">
				<tr>
					<th scope="col">Mã Môn Học</th>
					<th scope="col">Tên Môn Học</th>
					<th scope="col" style="width: 300px;">Action</th>


				</tr>
			</thead>
			<tbody id="content">

			</tbody>
		</table>
	</div>
</body>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>


<script>
	$(document).ready(function() {
		$.ajax({
			url : "getName",
			type : "Get",
			
			success : function(data) {
				 $('#getName').html(data); 

			},
			error : function(data) {
				alert("error1");
			}
		});
	});
	$(document)
			.ready(
					function() {
						$
								.ajax({
									url : "Kqdk",
									type : "Get",
									contentType : "application/json",
									// DataType : 'json',
									success : function(res) {
										var data = "";
										for (var i = 0; i < res.length; i++) {
											data += "<tr><td>"
													+ res[i].subjectId
													+ "</td><td>"
													+ res[i].subjectName
													+ "</td><td><button type='submit' class='btn btn-info btn-lg' id='btnDelete' value ='"
													+ res[i].subjectId
													+ "' onclick=HuyDangKy('"
													+ res[i].subjectId
													+ "')>Hủy Đăng Kí</button></td></tr>";
											$('#content').html(data);
										}

									},
									error : function() {
										alert("Lỗi!");
									}
								});
					});
	function HuyDangKy(id) {

		$.ajax({
			url : "HuyDangKy/" + id,
			type : "post",
			contentType : "application/json",
			DataType : 'json',

			success : function(data) {
				alert("Hủy Môn Học Thành Công")
				location.reload();
			},
			error : function() {
				alert("Hủy Môn Học Thất Bại")
			}
		});
	}
</script>
</html>