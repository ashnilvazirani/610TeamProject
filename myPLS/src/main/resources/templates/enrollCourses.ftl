<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Enroll Courses</title>

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
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<button onclick="goBack()" class="btn btn-info"	style="margin:15px;" >&laquo; Back</button>
		<a class="navbar-brand" href="http://localhost:4567/professorDashboard">MyPLS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav mr-auto"></div>
			<div class="navbar-nav ml-auto">
				
				
				<a class="navbar-brand" href="http://localhost:4567/">
					<button type="submit" class="btn btn-info" style="margin:5px;">Logout</button>
				</a>
			</div>
		</div>
	</nav>
	
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="lead">
			Courses List
			</h1>
		</div>
	</div>
	<div class="container">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">Course Name</th>
					<th scope="col">Course Description</th>
                    <th scope="col">Course Duration</th>
					<th scope="col">Stream Name</th>
					<th scope="col"></th>
                    
				</tr>
			</thead>
			<tbody>
				<#list 0..courses?size-1 as i>
					<tr>
					    <form method="POST" class="register-form" id="register-form" name="enrollCourseForm"  action="/enrollCourse">
					    
							<td scope="col">
							<input name="courseId" id=${courses[i].courseId} value=${courses[i].courseId} hidden>${courses[i].courseName} </input>
							</td>
							<td scope="col">
							<input name="courseDescription" id=${courses[i].courseDescription} value=${courses[i].courseDescription} hidden> ${courses[i].courseDescription}</input>
							</td>
		                    <td scope="col">
		                    <input name="courseDuration" id=${courses[i].courseDuration} value=${courses[i].courseDuration} hidden> ${courses[i].courseDuration}</input>
		                    </td>
		                    <td scope="col">
		                 	<input name="streamId" id= ${courses[i].streamId} value= ${courses[i].streamId} hidden>${courses[i].streamName} </input>
		                   </td>
							
		                    <td scope="col">
							<button type="submit" class="btn btn-info">Enroll Course</button></td>
						</form
					</tr>
				</#list>
			</tbody>
			
		</table>
		
	</div>
	
	  
    <script type="text/javascript">
	    <#include "/static/js/jquery.min.js">
	    function goBack() {
  		window.history.back();}
	</script>	
</body>
</html>