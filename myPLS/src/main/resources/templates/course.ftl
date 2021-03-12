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
                        <h2 class="form-title">Add Course</h2>
                        <form method="POST" class="register-form" id="add-course-form" name="addCourseForm" action="/addCourse">
                       	 <font color ="red">
                          </font>
                            <div class="form-group">
                                <input type="text" name="courseName" id="name" placeholder="Course Name" required/>
                            </div>
                             <div class="form-group">
                                <input type="text" name="courseDescription" id="name" placeholder="Course Description" required/>
                            </div>
                            <div class="form-group">
                                <input type="text" name="courseDuration" id="name" placeholder="Course Duration" required/>
                            </div>
                            <div class="form-group">
                            

							<select name="streamId" id="streamId">
							 <#list streams as stream>
							  <option value=${stream.streamID}>${stream.streamName}</option>	
							 </#list>
							  
							</select>
							</div>
                            <div class="form-group form-button">
                                <input type="submit" name="add" id="add" class="form-submit" value="Add"
                                />
                            </div>              
                        </form>
                            <div class="signup-image">
                               <a href="http://localhost:4567/loginPage" class="signup-image-link" rel="link"
                               >View Courses</a> 
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
    
</body>
</html>