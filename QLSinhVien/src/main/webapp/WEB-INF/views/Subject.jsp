<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</head>
<body>


	<section class="clearfix huy-cpoyright">
		<h3 align="center">Đăng ký Môn Học</h3>
		<form class="form-horizontal" method="get" style="margin-left: 150px;">
			<h2>Chọn kì học</h2>
			<select id="ddlViewBy">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</select>
		</form>
		<button onclick="myFunction()" style="margin-left: 150px;">Chọn</button>
		<form class="form-inline my-2 my-lg-0" action="SearchStudent"
			method="get" >
			<input class="form-control mr-sm-2" type="search"
				name="searchSubject" id="searchSubject" placeholder="Search"
				aria-label="Search" style="margin-left: 1058px;">
			<button  onclick=functionSearch()
				class="btn btn-outline-success my-2 my-sm-0"
				class='btn btn-info btn-lg' type='button' data-toggle='modal'>Search</button>
		</form>
		<h4 align="center">Danh Sách Môn Học</h4>
		<table class="table clearfix"
			style="width: 1200px; height: auto; margin: auto;" id="table">


			<thead class="thead-dark">
				<tr>
					<th scope="col">Mã Môn Học</th>
					<th scope="col">Học Kỳ</th>
					<th scope="col">Tên Môn Học</th>
					<th scope="col" style="width: 370px;">Action</th>


				</tr>
			</thead>
			<tbody id="content">

			</tbody>
		</table>

		<div class="form-group" style="text-align: center;">

			<div class="col-sm-offset col-sm ">
				<button data-target='#add' type='button' class='btn btn-info btn-lg'
					data-toggle='modal' style="height: 75px; width: 125px;">Thêm
					Mới</button>

			</div>
		</div>

	</section>
	<div id="add" class="modal fade" style="margin-top: 85px;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 align="center" class="modal-title" style="margin-left: 130px;">Thêm
						sinh viên</h2>
					<button type="button" class="close" data-dismiss="modal">&times;</button>

				</div>
				<div class="modal-body">
					<form method="post" id="insert_form">
						 <input type="text" name="subjectId1"
							id="subjectId1" class="form-control" hidden="true"/> <label>Học Kỳ:</label> <input
							type="text" name="semesterId1" id="semesterId1"
							class="form-control" /> <label>Tên Môn Học:</label><input
							type="text" name="subjectName1" id="subjectName1"
							class="form-control" /> <br>
					</form>
				</div>
				<div class="modal-footer">
					<a><button class="btn btn-light"
							style="height: 75px; width: 125px;" onclick=addSubject()>ADD</button></a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div id="myModalEdit" class="modal fade" style="margin-top: 85px;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 align="center" class="modal-title" style="margin-left: 130px;">Sửa
						sinh viên</h2>
					<button type="button" class="close" data-dismiss="modal">&times;</button>

				</div>
				<div class="modal-body">
					<form method="post" id="insert_form">
						<label>Mã Môn Học: </label> <input readonly="true" type="text"
							name="subjectId" id="subjectId" class="form-control" /> <label>Học
							Kỳ:</label> <input type="text" name="semesterId" id="semesterId"
							class="form-control" /> <label>Tên Môn Học:</label><input
							type="text" name="subjectName" id="subjectName"
							class="form-control" /> <br>
					</form>
				</div>
				<div class="modal-footer">
					<a><button class="btn btn-light"
							style="height: 75px; width: 125px;" onclick=edit()>Update</button></a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

</body>
<script>
function myFunction(){
	var e = document.getElementById("ddlViewBy");
	var value = e.options[e.selectedIndex].value;
						$.ajax({
									url : "Subject/" + value,
									type : "Get",
									contentType : "application/json",
									dtaType : 'json',
									success : function(res) {
										var data = "";
										for (var i = 0; i < res.length; i++) {
											data += "<tr><td>"
													+ res[i].subjectId
													+ "</td><td>"
													+ res[i].semesterId
													+ "</td><td>"
													+ res[i].subjectName
													+ "</td><td><button class='btn btn-info btn-lg' onclick='functionDelete("
													+ res[i].subjectId
													+ ")' >Xoa</button ><button data-target='#myModalEdit' type='button' onclick=functionEdit() class='btn btn-info btn-lg' data-toggle='modal'>Sửa</button><button onclick=functionEdit() class='btn btn-info btn-lg'>Đăng Ký</button></td></tr>";
											$('#content').html(data);
										}

									},
									error : function() {
										alert("error occurred");
									}
								});
					}
	function functionEdit() {
		var table = document.getElementById('table');
		for (var i = 0; i < table.rows.length; i++) {
			table.rows[i].onclick = function name() {
				document.getElementById('subjectId').value = this.cells[0].innerHTML;
				document.getElementById('semesterId').value = this.cells[1].innerHTML;
				document.getElementById('subjectName').value = this.cells[2].innerHTML;
			};
		}

	};
	function edit() {
		var a = document.getElementById("subjectId").value;
		var b = document.getElementById("semesterId").value;
		var c = document.getElementById("subjectName").value;
		$.ajax({
			url : "EditSubject",
			type : "post",
			data : {
				subjectId : a,
				semesterId : b,
				subjectName : c,
			},
			success : function(data) {
				alert("success Edit");
				location.reload();
			},
			error : function() {
				alert("error Edit");

			}

		});
	};
	function functionDelete(id) {

		$.ajax({
			url : "DeleteSubject/" + id,
			type : "post",
			contentType : "application/json",
			DataType : 'json',

			success : function(data) {
				alert("success Delete")
				location.reload();
			},
			error : function() {
				alert("error Delete")
			}
		});
	}
	function addSubject() {
		var a = document.getElementById("subjectId1").value;
		var b = document.getElementById("semesterId1").value;
		var c = document.getElementById("subjectName1").value;
		$.ajax({
			url : "addSubject",
			type : "post",
			data : {
				subjectId : a,
				semesterId : b,
				subjectName : c,
			},
			success : function(data) {
				alert("success Edit");
				location.reload();
			},
			error : function() {
				alert("error Edit");

			}

		});
	}
	 
function functionSearch() {
		
		var searchSubject=$("#searchSubject").val();
		$.ajax({
			url : "Search/" + searchSubject,
			type : "get",
			contentType : "application/json",
			DataType : 'json',

			success : function(res) {
				var data = "";
				for (var i = 0; i < res.length; i++) {
					data += "<tr><td>"
						+ res[i].subjectId
						+ "</td><td>"
						+ res[i].semesterId
						+ "</td><td>"
						+ res[i].subjectName
						+ "</td><td><button class='btn btn-info btn-lg' onclick='functionDelete("
						+ res[i].subjectId
						+ ")' >Xoa</button ><button data-target='#myModalEdit' type='button' onclick=functionEdit() class='btn btn-info btn-lg' data-toggle='modal'>Sửa</td></tr>";
				$('#content').html(data);
				}

			},
			error : function() {
				alert("error occurred");
			}
		});
	} 

</script>
</html>