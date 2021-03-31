<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Admin Home</title>

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
		<a class="navbar-brand" href="http://localhost:4567/">MyPLS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav mr-auto"></div>
			<div class="navbar-nav ml-auto">
				<form class="form-inline" method="get" action="/addCourse">
					<button type="submit" class="btn btn-info" style="margin:5px;">Add course</button>
				</form>
				<form class="form-inline" method="get" action="/createGroup">
					<button type="submit" class="btn btn-info" style="margin:5px;">Create A group</button>
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
				Discussion Groups
			</h1>
			<p class="lead">Group List</p>
		</div>
	</div>

	<div class="container">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Group Name</th>
                    <th scope="col">Group Topic</th>
					<th scope="col">Created By</th>
                    <th scope="col">View Members</th>
                    <th scope="col">Chat</th>
				</tr>
			</thead>
			<tbody>
				<#list groups as group>
				<tr>
					<td scope="col">${group.getGroupDiscussionID()}</td>
					<td scope="col">${group.getGroupName()}</td>
                    <td scope="col">${group.getGroupTopic()}</td>
                    <td scope="col">${group.getUserID()}</td>
					<td scope="col">
                        <form class="form-inline" method="post" action="/viewMembersInGroup">
                            <button type="submit" class="btn btn-info">View Members</button>
                            <input id="groupDiscussionID" name="groupDiscussionID" type="hidden" value="${group.getGroupDiscussionID()}"/>
                        </form>
                    </td>
					<td scope="col">
						<a class="btn btn-info" href="/viewGroupChats/${group.getGroupDiscussionID()}">Chat</a>
					</td>
					<#if userID == group.userID>
						<td scope="col">
							<form class="form-inline" method="post" action="/inviteMembers">
								<button type="submit" class="btn btn-info">Invite Members</button>
								<input id="groupDiscussionID" name="groupDiscussionID" type="hidden" value="${group.getGroupDiscussionID()}"/>
							</form>
						</td>
					</#if>
				</tr>
				</#list>
			</tbody>
			
		</table>
		
			<button onclick="goBack()" class="btn btn-info"	>&laquo; Back</button>
	</div>
	
	  <script type="text/javascript">
	    <#include "/static/js/jquery.min.js">
	    function goBack() {
  		window.history.back();}
	</script>	
	
</body>
</html>