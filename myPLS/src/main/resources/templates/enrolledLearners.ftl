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
	
	
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">
				Welcome!
			</h1>
			<p class="lead">Students List</p>
		</div>
	</div>

	<div class="container">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">Student Name</th>
					<th scope="col">Student Email</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<#list users as user>
				<tr>
					<td scope="col">${user.name}</td>
					<td scope="col">${user.email}</td>
					<td scope="col">
						<form class="form-inline" method="post" action="/learnerFeedback">
							<input name="learnerId" id=${user.userID} value=${user.userID} hidden/>
							<div class="form-group" hidden>
								<input type="text" name="courseId" id="courseId" value= ${courseId} />
							</div>
							<button type="submit" class="btn btn-info">Give Feedback</button>
						</form>
					</td>
					<#assign memberPresent = -1>
					<#list groupMembers as gm>
						<#if gm.userID == user.userID>
							<#assign memberPresent = 1>
							<#break>
						</#if>
					</#list>
					<#if memberPresent == 1>
					<td scope="col">
						<form class="form-inline" method="post" action="/addRemoveMemberCourseGroup">
							<input name="userID" id=${user.userID} value=${user.userID} hidden/>
							<input type="text" name="courseId" id="courseId" value= ${courseId} hidden/>
							<button type="submit" class="btn btn-info" name="removeMember">RemoveMember</button>
						</form>
					</td>
					<#else>
					<td scope="col">
						<form class="form-inline" method="post" action="/addRemoveMemberCourseGroup">
							<input name="userID" id=${user.userID} value=${user.userID} hidden/>
							<input type="text" name="courseId" id="courseId" value= ${courseId} hidden/>
							<button type="submit" class="btn btn-info" name="addMember">AddMember</button>
						</form>
					</td>
					</#if>
					<#assign memberPresent = -1>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</body>
</html>