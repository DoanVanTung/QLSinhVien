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
									url : "tableSinhvien",
									type : "Get",
									contentType : "application/json",
									// DataType : 'json',
									success : function(res) {
										var data = "";
										for (var i = 0; i < res.length; i++) {
											data += "<tr><td>"
													+ res[i].maSinhvien
													+ "</td><td>"
													+ res[i].tenSinhvien
													+ "</td><td>"
													+ res[i].tuoi
													+ "</td><td><button type='submit' class='btn btn-warning' id='btnDelete' value ='"+res[i].maSinhvien+"' onclick=functionDelete('"+res[i].maSinhvien+"')>Delete</button></td><td><button data-target='#myModalAdd' onclick='functionEdit()'  class='btn btn-info btn-lg' data-toggle='modal'>edit</button></td></tr>";
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
	function functionDelete(idSinhvien) {

		$.ajax({

			url : "deleteSinhvien/" + idSinhvien,
			type : "POST",
			contentType : "application/json",
			dataType : 'json',
			success : function() {
				alert("success delete");
				location.reload();

			},
			error : function() {
				alert("error delete");
				location.reload();
			}
		});

	}
</script>
<%--chọn hàng để hiển thị thông tin --%>
<script type="text/javascript">
function functionEdit() {
	var table = document.getElementById('tableSinhvien');
	for (var i = 0; i < table.rows.length; i++) {
		table.rows[i].onclick = function name() {
			document.getElementById('maSinhvien').value = this.cells[0].innerHTML;
			document.getElementById('tenSinhvien').value = this.cells[1].innerHTML;
			document.getElementById('tuoi').value = this.cells[2].innerHTML;
		};
	}

};
function functionEdit1() {
	var table = document.getElementById('tableSinhvien1');
	for (var i = 0; i < table.rows.length; i++) {
		table.rows[i].onclick = function name() {
			document.getElementById('maSinhvien').value = this.cells[0].innerHTML;
			document.getElementById('tenSinhvien').value = this.cells[1].innerHTML;
			document.getElementById('tuoi').value = this.cells[2].innerHTML;
		};
	}

};

	//hiện table bị ẩn
	$(document).ready(function() {
		$('#searchSinhvien').click(function() {
			$('#formSinhvien1').toggle(500);
		});
		$('#addSinhvien').click(function() {
			$('#formSinhvien').toggle(500);
		});
	});
</script>

<script>
	//thêm sinh viên
	function addSinhvien() {
		var b = document.getElementById("tenSinhvien1").value;
		var c = document.getElementById("tuoi1").value;
		$.ajax({
			url : "addSinhvien",
			type : "post",
			data : {
				tenSinhvien1 : b,
				tuoi1 : c,
			},
			success : function(data) {
				alert("success addSinhvien");
				location.reload();
			},
			error : function() {
				alert("error addSinhvien");

			}

		});
	};
</script>
<script>
	//sửa thông tin sinh viên
	function editSinhvien() {
		var a = document.getElementById("maSinhvien").value;
		var b = document.getElementById("tenSinhvien").value;
		var c = document.getElementById("tuoi").value;
		$.ajax({
			url : "editSinhvien",
			type : "post",
			data : {
				maSinhvien : a,
				tenSinhvien : b,
				tuoi : c,
			},
			success : function(data) {
				alert("success editSinhvien");
				location.reload();
			},
			error : function() {
				alert("error editSinhvien");

			}

		});
	};
</script>
<%-- tìm kiếm sinh viên --%>
<script>
	function SearchSinhvien() {
		var a = document.getElementById("searchSV").value;
		$
				.ajax({

					url : "seachSinhvien/" + a,
					type : "GET",
					contentType : "application/json",
					dataType : 'json',
					success : function(res) {
						var data = "";
						for (var i = 0; i < res.length; i++) {
							data += "<tr><td>"
									+ res[i].maSinhvien
									+ "</td><td>"
									+ res[i].tenSinhvien
									+ "</td><td>"
									+ res[i].tuoi
									+ "</td> <td><button id='"
									+ res[i].maSinhvien
									+ "' onclick='functionDelete("
									+ res[i].maSinhvien
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

		<form class="navbar-form navbar-right" >
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
			<h3>${message}</h3>
			<table class="table table-hover" id="tableSinhvien" border="1">
				<thead>
					<tr>
						<th>mã sinh viên</th>
						<th>tên sinh viên</th>
						<th>tuổi</th>
						<th>action</th>
						<th>action</th>
					</tr>
				</thead>
				<tbody id="content">
				</tbody>

			</table>
			<button id="addSinhvien">thêm thông tin sinh viên</button>
			<button id="searchSinhvien">tìm kiếm sinh viên</button>
			<br> <br>
			<div class="form-horizontal" id="formSinhvien" hidden >
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">họ tên:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="tenSinhvien1" name="tenSinhvien1"
							placeholder="Nhập vào tên Sinh viên">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">tuổi:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="tuoi1" name="tuoi1"
							placeholder="Nhập vào tuổi">
					</div>
				</div>
				<div>
					<button onclick="addSinhvien()">submit</button>
				</div>

			</div>
			
			<%-- form tìm kiếm Sinh viên --%>
			<div class="form-horizontal" id="formSinhvien1" hidden >

				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">nhập tên
						sinh viên:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="searchSV" name="searchSV"
							placeholder="tên sinh viên">
					</div>
				</div>

				<div>
					<button onclick="SearchSinhvien()" data-target='#myModalAdd1' type='button'
						class='btn btn-info btn-lg' data-toggle='modal'>submit</button>
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
					<table class="table table-hover" id="tableSinhvien1" border="1">
						<thead>
							<tr>
								<th>mã sinh viên</th>
								<th>tên sinh viên</th>
								<th>tuổi</th>
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
							<label>mã sinh viên: </label> <input type="number" name="maSinhvien"
								id="maSinhvien" class="form-control" readonly="readonly" /> <label>họ
								tên :</label> <input type="text" name="tenSinhvien" id="tenSinhvien"
								class="form-control" /> <label>tuổi :</label> <input
								type="number" name="tuoi" id="tuoi" class="form-control" min="0"
								max="100" step="1" /> <br>
						</form>
					</div>
					<div class="modal-footer">
						<button onclick="editSinhvien()">edit</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	</div>
</body>
</html>




