<!-- DOCTYPE is an instruction to the browser about what version of HTML
the page is written in -->
<!DOCTYPE html>
<html>

  <!-- Head contains meta data and imports -->
  <head>

    <!-- define character set in use -->
    <meta charset="utf-8">

    <!-- Title appears in tab -->
    <title>Title</title>
    <style type="text/css">
	<#include "jquery.datetimepicker.min.css">
	</style>

<script src="jquery.js"></script>
<script src="jquery.datetimepicker.full.js"></script>



<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

  </head>

  <!-- Body contains the page content -->
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
			Scheduled Lecture
			</h1>
		</div>
	</div>
    
<div class="main">

<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">Lecture Name</th>
					<th scope="col">Select Date</th>
					
				</tr>
			</thead>
			<tbody>
			    <form method="POST" class="register-form" id="get-form" name="editLecture"  action="/scheduleLecture">
			
				<tr>
				<td scope="col">${lectureName}</td>
				<td><input id="datetime" name="datetime"/></td>
			
				</tr>
				<td scope="col" hidden>
				<input name="lectureId" id=${lectureId} value=${lectureId} hidden>${lectureId} </input>
				</td>
				<td scope="col" hidden>
				<input name="courseId" id=${courseId} value=${courseId} hidden>${courseId} </input>
				</td>
				<td scope="col">
				<button type="submit" class="btn btn-info">Schedule Lecture</button></td>
			</form>
			</tbody>
			
	
       
    </div>

    <!-- JS -->
    <script>
    $('#datetime').datetimepicker();
    </script>
  </body>
  
       <script type="text/javascript">
	    <#include "/static/js/jquery.min.js">
	    function goBack() {
  		window.history.back();}
	</script>	

<!-- Make sure to close all your tags! -->
</html>



