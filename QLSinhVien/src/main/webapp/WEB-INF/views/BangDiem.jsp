<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tra cứu điểm</title>

</head>

<style>
.btn-primary {
	color: lawngreen;
	background-color: lightslategray;
	border-color: yellowgreen;
}

.btn-warning {
	color: sienna;
	background-color: wheat;
	border-color: unset;
}

.btn-success {
	color: yellow;
	border-color: teal;
}

body {
	font-family: auto;
	font-size: 15px;
	line-height: 1.42857143;
	color: unset;
	background-color: whitesmoke;
}

button, textarea {
	color: tomato;
}

.modal-footer {
	text-align: center;
}
</style>
<body>

	<!-- navbar -->
	<nav class="navbar navbar-light bg-dark justify-content-between">
	<a href="TrangChu1" class="navbar-brand"><button
			class="btn btn-infor my-2 my-sm-0"
			style="color: white; background: red;">Home</button></a>
	<form class="form-inline">
		<input class="form-control mr-sm-2" type="search" placeholder="Search"
			aria-label="Search" id="search">
		<button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
		<button class="btn btn-danger my-2 my-sm-0" onclick='Back()'>Back</button>
	</form>
	</nav>

	<!-- Main -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1 align="center">Thông tin</h1>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Danh sách điểm</h3>

					</div>
					<div id="div1">
						<table class="table table-hover" id="table">

							<thead>
								<tr>
									<th hidden>Mã môn học</th>
									<th>Mã sinh viên</th>
									<th>Tên sinh viên</th>
									<th>Tên môn</th>
									<th>Điểm_1</th>
									<th>Điểm_2</th>
									<th>Điểm_3</th>
									<th>Xóa</th>
									<th>Sửa</th>
								</tr>
							</thead>
							<tbody id="content">
							</tbody>
						</table>
						<div class="container">
							<div class="row">
								<div class="col-md-12">
									<center>

										<!-- <a href='#myModalAddSV' class='trigger-btn'
											data-toggle='modal'>
											<button style="font-size: larger;"
												class="btn btn-info btn-sm" id="addSV">Thêm sinh
												viên</button> -->

										</a> <a href='#myModalAddScore' class='trigger-btn'
											data-toggle='modal'>
											<button style="font-size: larger;"
												class="btn btn-info btn-sm" id="addScore">Thêm điểm</button>
										</a>
									</center>
								</div>
							</div>
						</div>
					</div>
					<div id="div2">
						<table class="table table-hover" id="table1">

							<thead>
								<tr>
									<th hidden>Mã môn học</th>
									<th>Mã sinh viên</th>
									<th>Tên sinh viên</th>
									<th>Tên môn</th>
									<th>Điểm_1</th>
									<th>Điểm_2</th>
									<th>Điểm_3</th>
									<th>Xóa</th>
									<th>Sửa</th>
								</tr>
							</thead>
							<tbody id="content1">
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- Form Sửa -->
			<div id="myModalEdit" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h2 style="color: red;" align="center" class="modal-title">Sửa
								Điểm</h2>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<form method="post" id="edit_form">
								<input type="hidden" name="idsub" id="idsub"
									class="form-control" /> <label>ID</label><input type="text"
									name="ids" id="ids" class="form-control" readonly="true" /> <label>Name</label><input
									type="text" name="names" id="names" class="form-control"
									readonly="true" /> <label>Điểm_1</label><input type="number"
									min="0" max="10" step="0.1" name="diem1" id="diem1"
									class="form-control" /> <label>Điểm_2</label> <input
									type="number" min="0" max="10" step="0.1" name="diem2"
									id="diem2" class="form-control">
							</form>

						</div>
						<div class="modal-footer">
							<input type="submit" name="edit" id="editTable"
								onclick="editTable()" value="Edit" class="btn btn-success" />
							<button type="button" class="btn btn-default"
								data-dismiss="modal" class="btn btn-dager">Close</button>
						</div>
					</div>
				</div>
			</div>

			<!-- form thêm điểm -->
			<div id="myModalAddScore" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h2 style="color: red;" align="center" class="modal-title">Thêm
								điểm</h2>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<form method="post" id="insert_form">
								<label>ID: </label> <input type="text" name="idsc" id="idsc"
									class="form-control" /> <label>IDsub: </label><input
									type="text" name="idsubsc" id="idsubsc" class="form-control" />
								<label>Điểm 1</label><input type="text" name="diem1sc"
									id="diem1sc" class="form-control" /><label>Điểm 2</label><input
									type="text" name="diem2sc" id="diem2sc" class="form-control" />

							</form>
						</div>
						<div class="modal-footer">
							<input type="submit" name="insertsc" onclick="addScore()"
								id="insertsc" value="InsertSC" class="btn btn-success" />
							<button type="button" class="btn btn-dager" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		// Lay du lieu duoc gui ra tu controller gan vao bang table
		$(document)
				.ready(
						function() {
							$("#div1").show();
							$("#div2").hide();
							$
									.ajax({
										url : "certificate",
										type : "Get",
										contentType : "application/json",
										success : function(res) {
											var data = "";
											for (var i = 0; i < res.length; i++) {
												data += "<tr><td hidden>"
														+ res[i].maMH
														+ "</td><td>"
														+ res[i].maSV
														+ "</td><td>"
														+ res[i].tenSV
														+ "</td><td>"
														+ res[i].tenMH
														+ "</td><td>"
														+ res[i].diem_1
														+ "</td><td>"
														+ res[i].diem_2
														+ "</td><td>"
														+ ((res[i].diem_1 * 0.3) + (res[i].diem_2 * 0.7))
														+ "</td> <td>"
														+ "<button class = 'btn btn-dager' idma = '"
														+ res[i].maSV
														+ "' class='delete' onclick = 'deleteFuntion("
														+ res[i].maSV
														+ ","
														+ res[i].maMH
														+ ")'"
														+ ">Delete</button>"
														+ "</td><td>"
														+ "<a href='#myModalEdit' class='trigger-btn' data-toggle='modal'>"
														+ "<button onclick=Edit_Cer() class = 'btn btn-primary' class='edit_data' idma = '"
														+ res[i].maSV
														+ "'>Edit</button>"
														+ "</td></tr>";
												$('#content').html(data);
											}
										},
										error : function() {
											alert("Error occurred");
										}

									});
						});

		//lay thong tin tu hang cua bang table
		function Edit_Cer() {
			var table = document.getElementById('table');
			for (var i = 0; i < table.rows.length; i++) {
				table.rows[i].onclick = function name() {
					document.getElementById('idsub').value = this.cells[0].innerHTML;
					document.getElementById('ids').value = this.cells[1].innerHTML;
					document.getElementById('names').value = this.cells[2].innerHTML;
					document.getElementById('diem1').value = this.cells[4].innerHTML;
					document.getElementById('diem2').value = this.cells[5].innerHTML;
				};
			}
		}

		//lay thong tin tu hang cua bang table1
		function Edit_Cer1() {
			var table = document.getElementById('table1');
			for (var i = 0; i < table.rows.length; i++) {
				table.rows[i].onclick = function name() {
					document.getElementById('idsub').value = this.cells[0].innerHTML;
					document.getElementById('ids').value = this.cells[1].innerHTML;
					document.getElementById('names').value = this.cells[2].innerHTML;
					document.getElementById('diem1').value = this.cells[4].innerHTML;
					document.getElementById('diem2').value = this.cells[5].innerHTML;
				};
			}
		}

		// Sua diem sau khi da lay duoc thong tin tu hang va xu ly qua fole java
		function editTable() {

			var a = document.getElementById("names").value;
			var b = document.getElementById("diem1").value;
			var c = document.getElementById("diem2").value;
			var d = document.getElementById("idsub").value;
			var e = document.getElementById("ids").value;
			$.ajax({
				url : "edit",
				type : "post",
				data : {
					id : e,
					idsub : d,
					name : a,
					diem1 : b,
					diem2 : c,

				},
				success : function() {
					alert("Success");
					location.reload();
				},
				error : function() {
					alert("Error");

				}

			});
		};

		//tra cuu diem thong qua ten sinh vien hoac id sinh vien
		$('#search')
				.on(
						'change',
						function() {
							$("#div1").hide();
							$("#div2").show();
							var infor = $("#search").val();
							$
									.ajax({
										url : "timkiem/" + infor,
										type : "GET",
										contentType : "application/json",
										DataType : 'json',
										success : function(sv) {
											var data = "";
											for (var i = 0; i < sv.length; i++) {
												data += "<tr><td hidden>"
														+ sv[i].maMH
														+ "</td><td>"
														+ sv[i].maSV
														+ "</td><td>"
														+ sv[i].tenSV
														+ "</td><td>"
														+ sv[i].tenMH
														+ "</td><td>"
														+ sv[i].diem_1
														+ "</td><td>"
														+ sv[i].diem_2
														+ "</td><td>"
														+ ((sv[i].diem_1 * 0.3) + (sv[i].diem_2 * 0.7))
														+ "</td> <td>"
														+ "<button class = 'btn btn-dager' idma = '"
														+ sv[i].maSV
														+ "' class="
														+ "'delete' onclick = 'deleteFuntion("
														+ sv[i].maSV
														+ ","
														+ sv[i].maMH
														+ ")'"
														+ ">Delete</button>"
														+ "</td><td>"
														+ "<a href='#myModalEdit' class='trigger-btn' data-toggle='modal'>"
														+ "<button onclick=Edit_Cer1() class = 'btn btn-primary' class='edit_data' idma = '"
														+ sv[i].maSV
														+ "'>Edit</button>"
														+ "</td></tr>";
												$('#content1').html(data);
											}
										},
										error : function(data) {
											alert("Không tìm thấy sinh viên")
										}
									});

						});

		//xoa 1 hang duoc chon
		function deleteFuntion(id, idsub) {
			$.ajax({
				url : "del/" + id + "/" + idsub,
				type : "post",
				contentType : "application/json",
				DataType : 'json',

				success : function(data) {
					location.reload();
					alert("Delete success");
				},
				error : function() {
					alert("error");

				}
			});
		}

		//thêm sinh viên
		/* 	function addSV() {
				var a = document.getElementById("idsv").value;
				var b = document.getElementById("namesv").value;
				var c = document.getElementById("agesv").value;
				$.ajax({
					url : "insSV",
					type : "post",
					data : {
						id : a,
						name : b,
						age : c,
					},
					success : function(data) {
						alert("Success");
						location.reload();
					},
					error : function() {
						alert("Error");

					}

				});
			};
		 */

		//them diem cho sinh vien
		function addScore() {
			var a = document.getElementById("idsc").value;
			var b = document.getElementById("idsubsc").value;
			var c = document.getElementById("diem1sc").value;
			var d = document.getElementById("diem2sc").value;
			$.ajax({
				url : "insertScore",
				type : "post",
				data : {
					id : a,
					idsub : b,
					diem1 : c,
					diem2 : d,
				},
				success : function(data) {
					alert("Success");
					location.reload();
				},
				error : function() {
					alert("Error");

				}

			});
		};

		//Back lai bang diem
		function Back() {
			$("#div1").show();
			$("#div2").hide();
		}

		/* onchange
		 *	$('#search').on('change', function() {
		 *		var a = document.getElementById('search').value;
		 *		alert(a);
		 *	}); 
		 */
	</script>
</body>
</html>