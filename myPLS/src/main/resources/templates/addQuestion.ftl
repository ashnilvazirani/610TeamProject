<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>MyPLS - Sign Up</title>

<!-- Main css -->
<style type="text/css">
<#include "/static/css/style.css">
</style>


</head>
<body>

<div class="main">

	<!-- Sign up form -->
	<section class="signup">
		<div class="container">
			<div class="signup-content">
				<div class="signup-form">
					<h2 class="form-title">Add Question</h2>
					<#if courseToUpdate??>
						<form method="POST" class="register-form" id="add-course-form" name="addCourseForm" action="/modifyCourse">
				<font color ="red">
					</font>
						<div class="form-group">
							<input type="text" name="courseName" id="name" value="${courseToUpdate.courseName}" required/>
						</div>
						<div class="form-group">
							<input type="text" name="courseDescription" id="name" value="${courseToUpdate.courseDescription}" required/>
						</div>
						<div class="form-group">
							<input type="text" name="courseDuration" id="name" value="${courseToUpdate.courseDuration}" required/>
						</div>
						
					<div class="form-group">
						<label for="streamId">Select Stream:</label>
						</div> 
							
						<div class="form-group">
								<select name="streamId" id="streamId">
								<option disabled selected>--Select Stream--</option>
									<#list streams as stream>
										<option value=${stream.streamID}>${stream.streamDescription}</option>	
									</#list>						
								</select>	
							
						</div>
						
						
						<div class="form-group">
						

						<select name="professorId" id="professorId">
						<option disabled selected>--Select Professor--</option>
                        <#list professors as professor>
							<option value=${professor.userID}>${professor.email}</option>	
							</#list>
						</select>
						</div>
						<div class="form-group form-button">
							<input type="submit" name="modifyCourse" id="modifyCourse" class="form-submit" style="margin:5px;" value="Add Course" />
						</div>
						</form>
						
					<#else>
					<form method="POST" class="register-form" id="add-course-form" name="addCourseForm" action="/addCourse">
						<font color ="red">
						</font>
						<div class="form-group">
							<input type="text" name="problem" id="problem" placeholder="Enter Problem Description" required/>
						</div>
							<div class="form-group">
							<input type="text" name="option1" id="option1" placeholder="Choice 1" required/>
						</div>
						</div>
							<div class="form-group">
							<input type="text" name="option2" id="option1" placeholder="Choice 1" required/>
						</div>
						</div>
							<div class="form-group">
							<input type="text" name="option3" id="option1" placeholder="Choice 1" required/>
						</div>
						</div>
							<div class="form-group">
							<input type="text" name="option4" id="option1" placeholder="Choice 1" required/>
						</div>
						<div class="form-group">
							<label for="correctAnswer">Corrrect Choice:</label>
						</div> 
						<div class="form-group">
								<select name="correctAnswer" id="correctAnswer">
									<option disabled selected>--Select Stream--</option>
									<option value="1">CHOICE 1</option>
									<option value="2">CHOICE 2</option>
									<option value="3">CHOICE 3</option>
									<option value="4">CHOICE 4</option>
								</select>
						</div>

						<div class="form-group form-button">
							<input type="submit" name="add" id="add" class="form-submit" style="margin:5px;" value="Add Course" />

						</div>
						</form>
							
					</#if>
							<form method="get" action="/enrollCourses">
							<input type="submit" name="add" id="add" class="form-submit" style="margin:5px;" value="View Courses" />
							</form>
	
							<input onclick="goBack()" type="submit" name="Back" id="Back" style="margin:5px;" class="form-submit" value="<< Back"/>
							
						</div>                       
				</div>   
			</div>
		</div>
		
	</section>
	
</div>

<!-- JS -->


	<script type="text/javascript">
<#include "/static/js/jquery.min.js">
</script>
<script>
function ValidateEmail(inputText)
	{
	var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	if(inputText.value.match(mailformat))
	{
	document.form1.text1.focus();
	return true;
	}
	
	}
</script>
	<script type="text/javascript">
<#include "/static/js/main.js">
</script>

	<script type="text/javascript">
	<#include "/static/js/jquery.min.js">
	function goBack() {
	window.history.back();}
</script>	


</body>
</html>