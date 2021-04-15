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
					<form method="POST" class="register-form" id="add-course-form" name="addQuestionForm" action="/addQuestion">
						<font color ="red">
						</font>
						<div class="form-group">
							<input type="text" name="problem" id="problem" placeholder="Enter Problem Description" required/>
						</div>
						
						<div class="form-group">
							<input type="text" name="option1" id="option1" placeholder="Choice 1" required/>
						</div>
						
						<div class="form-group">
							<input type="text" name="option2" id="option1" placeholder="Choice 2" required/>
						</div>
						
						<div class="form-group">
							<input type="text" name="option3" id="option1" placeholder="Choice 3" required/>
						</div>
						
						<div class="form-group">
							<input type="text" name="option4" id="option1" placeholder="Choice 4" required/>
						</div>
						
						<div class="form-group">
							<label for="correctAnswer">Corrrect Choice:</label>
						</div> 
						<div class="form-group">
							<select name="correctAnswer" id="correctAnswer">
								<option disabled selected>--SELECT CORRECT ANSWER--</option>
								<option value="1">CHOICE 1</option>
								<option value="2">CHOICE 2</option>
								<option value="3">CHOICE 3</option>
								<option value="4">CHOICE 4</option>
							</select>
						</div>
						<div>
							<input type="hidden" name="professorID" id="professorID" value="${userId}" />
						</div>
						<div class="form-group form-button">
							<input type="submit" name="addQuestion" id="add" class="form-submit" value="Add Question" />
							
							 <input onclick="goBack()" type="submit" name="Back" id="Back" style="margin:5px;" class="form-submit" value="<< Back"/>
                              
                                              
						</div>
						</form>
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