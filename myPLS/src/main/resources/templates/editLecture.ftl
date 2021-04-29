<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                        <h2 class="form-title">Edit Lecture</h2>
                        <form method="POST" class="add-lecture-form" id="add-lecture-form" name="lectureForm" action="/updateLecture">
                            <div class="form-group">
                                <input type="text" name="lectureName" id="lectureName" placeholder="Lecture Name"  value= ${lecture.lectureName} required/>
                            </div>
                             <div class="form-group">
                                <input type="text" name="lectureDescription" id="lectureDesc"  value= ${lecture.lectureDescription} placeholder="Lecture Description"/>
                            </div>
                            <div class="form-group" hidden>
                            <input name="lectureId" id="lectureId" value=${lecture.lectureId} hidden/> 
                                <input type="text" name="type" id="type" value="PDF" hidden/>
                             </div>
                             <div class="form-group" hidden>
                            <input type="text" name="courseId" id="courseId" value=${lecture.courseId} hidden/> 
                             </div>
                            <div class="form-group form-button">
                                <input type="submit" name="add" id="add" class="form-submit" value="Edit Lecture"/>
                                
                                <input onclick="goBack()" type="submit" name="Back" id="Back" style="margin:5px;" class="form-submit" value="<< Back"/>
                              
                            </div>   
                        
                        </form>
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