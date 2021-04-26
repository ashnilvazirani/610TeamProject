<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Students Home</title>

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
		<a class="navbar-brand" href="http://localhost:4567/studentDashboard">MyPLS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav mr-auto"></div>
			<div class="navbar-nav ml-auto">

				<form class="form-inline" method="get" action="/enrollCourse">

				<form class="form-inline" method="get" action="/enrollForCourses">

					<button type="submit" class="btn btn-info" style="margin:5px;">Enroll for courses</button>
				</form>
				<form class="form-inline" method="get" action="/createGroup">
					<button type="submit" class="btn btn-info" style="margin:5px;">Create A group</button>
				</form>
				<form class="form-inline" method="get" action="/viewGroups">
					<button type="submit" class="btn btn-info" style="margin:5px;">View Groups</button>
				</form>
				
				<a class="navbar-brand" href="http://localhost:4567/">
					<button type="submit" class="btn btn-info" style="margin:5px;">Logout</button>
				</a>
				
			</div>
		</div>
	</nav>
	
	
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">
				Welcome!
			</h1>
			<p class="lead">You have enrolled for the following courses:</p>
		</div>
	</div>

	<div class="container">
	
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">Course Name</th>
					<th scope="col">Course Description</th>
					<th scope="col">Course Duration</th>
                    <th scope="col">Stream</th>

                    <th scope="col">Active Groups</th>
                    <th scope="col">View Lectures</th>
                    <th scope="col">Professor Feedback</th>
				</tr>

			</thead>
			<tbody>
			
				<#list courses as course>
				<tr>	
					<td scope="col">${course.courseName}</td>
					<td scope="col">${course.courseDescription}</td>
                    <td scope="col">${course.courseDuration}</td>
                    <td scope="col">${course.streamName}</td>

					<td scope="col">
                    <#list myCourseGroups as myGroup>
						<#if myGroup.courseID == course.courseId>
							<form class="form-inline" method="POST" action="/courseGroupChat">
								<button type="submit" class="btn btn-info">ViewGroupChats</button>
								<input id="courseGroupID" name="courseGroupID" type="hidden" value="${myGroup.courseGroupID}"/>
							</form>
						</#if>
					</#list>
					</td>				
							
				<form class="form-inline" method="get" action="/learnerLectures"> 
						<td scope="col" hidden>
							<input name="courseId" id=${course.courseId} value=${course.courseId} hidden>${course.courseId} </input>
						</td>
						<td scope="col">
							<input type="submit" value="ViewLectures" class="btn btn-info"/>
						</td>
					</form>
					<td scope="col">
						<form class="form-inline" method="post" action="/professorFeedback">
							<input name="professorId" id=${course.professorId} value=${course.professorId} hidden/>
							<div class="form-group" hidden>
								<input type="text" name="courseId" id="courseId" value= ${course.courseId} />
							</div>
							<button type="submit" class="btn btn-info">Give Feedback</button>
						</form>
					</td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
	
	  <!-- JS -->
  
    
    <script type="text/javascript">
	    <#include "/static/js/jquery.min.js">
	</script>
	<script type="text/javascript">
	    <#include "/static/js/main.js">
	</script>
	
</body>
</html>