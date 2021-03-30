<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Professor Home</title>

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
		<a class="navbar-brand" href="http://localhost:4567/professorDashboard">MyPLS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav mr-auto"></div>
			<div class="navbar-nav ml-auto">
				
				<form class="form-inline" method="get" action="/createGroup">
					<button type="submit" class="btn btn-info" style="margin:5px;">Create Group</button>
				</form>
				<form class="form-inline" method="get" action="/viewGroups">
					<button type="submit" class="btn btn-info" style="margin:5px;">View Group</button>
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
				Welcome Professor!
			</h1>
			<p class="lead">Courses List</p>
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
					<th scope="col">PreReq Course Names</th>
					
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col"></th> 
					<th scope="col"></th>                    
				</tr>
			</thead>
			<tbody>
				<#list courses as course>
				<tr>
					<td scope="col">
					${course.courseName}</td>
					<td scope="col">${course.courseDescription}</td>
                    <td scope="col">${course.courseDuration}</td>
                    <td scope="col">${course.streamName}</td>
                    <td>
                    <#list preReqs as k,v>
                    <#if k == course.courseId>
                     <#list 0..v?size-1 as i>
                     <#if v[i]??>
 					 <span>${v[i].courseName}
 					 <#assign ls = v?size-1>
 					  <#if ls != i>, </#if>
 					 </span>
 					 </#if>
 					 </#list>
 					 </#if>
					</#list>
					</td>
                    </td>
                    <td scope="col">
                    <form class="form-inline" method="POST" action="/preReqCourse">
                    
					<input name="courseId" id=${course.courseId} value=${course.courseId} hidden> </input>
					
					<button type="submit" class="btn btn-info">Add Pre-Requisite Course</button>
				</form></td>

				<td>
					<form class="form-inline" method="GET" action="/enrolledLearners">
						<input name="courseId" id=${course.courseId} value=${course.courseId} hidden/>
						<button type="submit" class="btn btn-info">Manage Students</button>
					</form>
				</td>
				<td scope="col">
				<#assign courseGroupID = -1>
					<#list courseGroups as gc>
						<#if gc.courseID == course.courseId>
							<#assign courseGroupID = gc.courseGroupID>
						</#if>
					</#list>
						<#if courseGroupID==-1>
						<form class="form-inline" method="POST" action="/createACourseGroup">
							<button type="submit" class="btn btn-info">Create Course Group</button>
							<input id="courseId" name="courseId" type="hidden" value="${course.courseId}"/>
							<input id="professorId" name="professorId" type="hidden" value="${userId}"/>
						</form>
					<#else>
						<form class="form-inline" method="POST" action="/courseGroupChat">
							<button type="submit" class="btn btn-info">ViewGroupChats</button>
							<input id="courseGroupID" name="courseGroupID" type="hidden" value="${courseGroupID}"/>
						</form>
					</#if>
				</td>
				</tr>
				</#list>
			</tbody>
			
		</table>
	</div>
</body>
</html>