<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Lectures</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

</head>
<body>
	
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="lead">
			Lectures List
			</h1>
		</div>
	</div>
	<div class="container">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">Lecture Name</th>
					<th scope="col">Lecture Description</th>
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<#list 0..lectures?size-1 as i>
					<tr>
					    	<form method="GET" class="register-form" id="register-form" name="uploadPdf"  action="/getUploadPdfPage">
							<td scope="col" hidden>
							<input name="lectureId" id=${lectures[i].lectureId} value=${lectures[i].lectureId} hidden>${lectures[i].lectureId} </input>
							</td>
							<td scope="col">${lectures[i].lectureName}</td>
							<td scope="col">${lectures[i].lectureDescription}</td>
							<td scope="col" hidden>
							<input type="text" name="type" id="type" value="PDF" />
							</td>
		                    <td scope="col">
							<button type="submit" class="btn btn-info">upload Lecture</button></td>
							</form>
						<form method="POST" class="register-form" id="get-form" name="getPdf"  action="/getPdfs">
						<td scope="col" hidden>
							<input name="lectureId" id=${lectures[i].lectureId} value=${lectures[i].lectureId} hidden>${lectures[i].lectureId} </input>
							</td>
							<td scope="col">
							<button type="submit" class="btn btn-info">Get Lectures Content</button></td>
						</form>
						
						<form method="POST" class="register-form" id="get-form" name="editLecture"  action="/editLecture">
						<td scope="col" hidden>
							<input name="lectureId" id=${lectures[i].lectureId} value=${lectures[i].lectureId} hidden>${lectures[i].lectureId} </input>
							</td>
							<td scope="col">
							<button type="submit" class="btn btn-info">Edit Lecture</button></td>
						</form>
						
						<form method="POST" class="register-form" id="get-form" name="editLecture"  action="/deleteLecture">
						<td scope="col" hidden>
							<input name="lectureId" id=${lectures[i].lectureId} value=${lectures[i].lectureId} hidden>${lectures[i].lectureId} </input>
							</td>
							<td scope="col">
							<button type="submit" class="btn btn-info">Delete Lecture</button></td>
						</form>
					</tr>
				</#list>
			</tbody>
			
		</table>
	</div>
	
</body>
</html>