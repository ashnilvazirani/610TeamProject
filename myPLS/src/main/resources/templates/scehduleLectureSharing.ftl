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
  </head>

  <!-- Body contains the page content -->
  <body>

    
<div class="main">

<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">Lecture Name</th>
					<th scope="col">select date</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
			    <form method="POST" class="register-form" id="get-form" name="editLecture"  action="/scheduleLecture">
			
				<tr>
				<td scope="col">${lectureName}</td>
				<td><input id="datetime" name="datetime"/></td>
				<td></td>
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

<!-- Make sure to close all your tags! -->
</html>



