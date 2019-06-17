<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!DOCTYPE html>
<html lang="en">
<head>

<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>



<%-- hiển thị bảng điểm sinh viên --%>
<script>
	$(document)
			.ready(
					function() {
						$
								.ajax({
									url : "table_sv",
									type : "Get",
									contentType : "application/json",
									// DataType : 'json',
									success : function(res) {
										var data = "";
										for (var i = 0; i < res.length; i++) {
											data += "<tr><td>"
													+ res[i].maSV
													+ "</td><td>"
													+ res[i].tenSV
													+ "</td><td>"
													+ res[i].age
													+ "</td><td><button type='submit' class='btn btn-warning' id='btnDelete' value ='"+res[i].masv+"' onclick=functionDelete('"+res[i].maSV+"')>Delete</button></td><td><button data-target='#myModalAdd' onclick='functionEdit()'  class='btn btn-info btn-lg' data-toggle='modal'>edit</button></td></tr>";
											$('#content').html(data);
										}

									},
									error : function() {
										alert("error");
									}
								});
					});
</script>

<%-- xóa thông tin sinh viên --%>
<script>
	function functionDelete(id) {

		$.ajax({

			url : "Delete_sv/" + id,
			type : "POST",
			contentType : "application/json",
			dataType : 'json',
			success : function() {
				alert("success");
				location.reload();

			},
			error : function() {
				alert("error occurred 1");
				location.reload();
			}
		});

	}
</script>
<%--chọn hàng để hiển thị thông tin --%>
<script type="text/javascript">
function functionEdit() {
	var table = document.getElementById('table');
	for (var i = 0; i < table.rows.length; i++) {
		table.rows[i].onclick = function name() {
			document.getElementById('maSV').value = this.cells[0].innerHTML;
			document.getElementById('tenSV').value = this.cells[1].innerHTML;
			document.getElementById('age').value = this.cells[2].innerHTML;
		};
	}

};
function functionEdit1() {
	var table = document.getElementById('table1');
	for (var i = 0; i < table.rows.length; i++) {
		table.rows[i].onclick = function name() {
			document.getElementById('maSV').value = this.cells[0].innerHTML;
			document.getElementById('tenSV').value = this.cells[1].innerHTML;
			document.getElementById('age').value = this.cells[2].innerHTML;
		};
	}

};
	//hiện table bị ẩn
	$(document).ready(function() {

	});
	//hiện table bị ẩn
	$(document).ready(function() {
		$('#seach').click(function() {
			$('#form1').toggle(500);
		});
		$('#add').click(function() {
			$('#form').toggle(500);
		});
	});
</script>

<script>
	//thêm sinh viên
	function addNew() {
		var a = document.getElementById("maSV1").value;
		var b = document.getElementById("tenSV1").value;
		var c = document.getElementById("age1").value;
		$.ajax({
			url : "addsv",
			type : "post",
			data : {
				maSV1 : a,
				tenSV1 : b,
				age1 : c,
			},
			success : function(data) {
				alert("success add");
				location.reload();
			},
			error : function() {
				alert("error add");

			}

		});
	};
</script>
<script>
	//sửa thông tin sinh viên
	function edit() {
		var a = document.getElementById("maSV").value;
		var b = document.getElementById("tenSV").value;
		var c = document.getElementById("age").value;
		$.ajax({
			url : "edit_sv",
			type : "post",
			data : {
				maSV : a,
				tenSV : b,
				age : c,
			},
			success : function(data) {
				alert("success edit");
				location.reload();
			},
			error : function() {
				alert("error edit");

			}

		});
	};
</script>
<%-- tìm kiếm sinh viên --%>
<script>
	function Seach() {
		var a = document.getElementById("tenSV2").value;
		$
				.ajax({

					url : "seach_sv/" + a,
					type : "GET",
					contentType : "application/json",
					dataType : 'json',
					success : function(res) {
						var data = "";
						for (var i = 0; i < res.length; i++) {
							data += "<tr><td>"
									+ res[i].maSV
									+ "</td><td>"
									+ res[i].tenSV
									+ "</td><td>"
									+ res[i].age
									+ "</td> <td><button id='"
									+ res[i].maSV
									+ "' onclick='functionDelete("
									+ res[i].maSV
									+ ")'  class='btn btn-info btn-lg' data-toggle='modal'>delete</button></td> <td><button data-target='#myModalAdd' onclick='functionEdit1()'  class='btn btn-info btn-lg' data-toggle='modal'>edit</button></td></tr>";
							$('#content1').html(data);
						}

					},
					error : function() {
						alert("error occurred@");
					}
				});
	}
</script>


<%-- popup --%>
<script type="text/javascript">
	$(document).ready(function() {
		$('a.login-window').click(function() {
			//lấy giá trị thuộc tính href - chính là phần tử "#login-box"
			var loginBox = $(this).attr('href');

			//cho hiện hộp đăng nhập trong 300ms
			$(loginBox).fadeIn(300);

			// thêm phần tử id="over" vào sau body
			$('body').append('<div id="over">');
			$('#over').fadeIn(300);

			return false;
		});

		// khi click đóng hộp thoại
		$(document).on('click', "a.close, #over", function() {
			$('#over, .login').fadeOut(300, function() {
				$('#over').remove();
			});
			return false;
		});
	});
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-light"
		style="background-color: powderblue;">
	<div class="container-fluid" style="background-color: powderblue;">
		<div class="navbar-header" style="background-color: powderblue;">
			<img src="https://www.utc.edu.vn/themes/UTC_V2/images/main-logo.png">
		</div>

		<form class="navbar-form navbar-right" action="seach_student"
			method="post">
			<a class="nav-link" href="navigateTrangchu">Trang Chủ</a> <a
				class="nav-link" href="#">Trợ Giúp</a> <a class="nav-link"
				href="navigateSign_out">Đăng xuất</a>
		</form>
	</div>
	</nav>



	<div class="container">

		<div style="text-align: center;">
			<h2 style="color: blue">Thông tin học sinh</h2>
			<hr>
			<h3>${abc}</h3>
			<table class="table table-hover" id="table" border="1">
				<thead>
					<tr>
						<th>mã sinh viên</th>
						<th>tên sinh viên</th>
						<th>số nồi bánh chưng</th>
						<th>action</th>
						<th>action</th>
					</tr>
				</thead>
				<tbody id="content">
				</tbody>

			</table>
			<button id="add">thêm thông tin sinh viên</button>
			<button id="seach">tìm kiếm sinh viên</button>
			<br> <br>
			<div class="form-horizontal" id="form" hidden >
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">mã sinh
						viên:</label>
					<div class="col-sm-10">
						<input type="number" class="form-control" id="maSV1" name="maSV1"
							placeholder="mã sinh viên">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">họ tên:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="tenSV1" name="tenSV1"
							placeholder="Nhập vào tên Sinh viên">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">tuổi:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="age1" name="age1"
							placeholder="Nhập vào tuổi">
					</div>
				</div>
				<div>
					<button onclick="addNew()">submit</button>
				</div>

			</div>
			<div class="form-horizontal" id="form1" hidden >

				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">nhập tên
						sinh viên:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="tenSV2" name="tenSV2"
							placeholder="tên sinh viên">
					</div>
				</div>

				<div>
					<button onclick="Seach()" data-target='#myModalAdd1' type='button'
						class='btn btn-info btn-lg' data-toggle='modal'>submit</button>
				</div>

			</div>
		</div>

		<%-- popup  tìm kiếm sinh viên --%>
		<div id="myModalAdd1" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h2 align="center" class="modal-title">thông tin sinh viên</h2>
					</div>

					<h2 style="color: blue">Thông tin học sinh</h2>
					<hr>
					<table class="table table-hover" id="table1" border="1">
						<thead>
							<tr>
								<th>mã sinh viên</th>
								<th>tên sinh viên</th>
								<th>số nồi bánh chưng</th>
								<th>action</th>
								<th>action</th>
							</tr>
						</thead>
						<tbody id="content1">
						</tbody>

					</table>
				</div>
			</div>
		</div>

		<%-- popup sửa thông tin sinh viên --%>
		<div id="myModalAdd" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h2 align="center" class="modal-title">Sửa thông tin sinh
							viên</h2>
					</div>
					<div class="modal-body">
						<form method="post" id="insert_form">
							<label>mã sinh viên: </label> <input type="number" name="maSV"
								id="maSV" class="form-control" readonly="readonly" /> <label>họ
								tên :</label> <input type="text" name="tenSV" id="tenSV"
								class="form-control" /> <label>tuổi :</label> <input
								type="number" name="age" id="age" class="form-control" min="0"
								max="100" step="1" /> <br>
						</form>
					</div>
					<div class="modal-footer">
						<button onclick="edit()">edit</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>




