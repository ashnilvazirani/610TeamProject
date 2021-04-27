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
                        <h2 class="form-title">Add Lecture</h2>
                        <form method="POST" class="add-lecture-form" id="add-lecture-form" name="lectureForm" action="/addLecture">
                            <div class="form-group">
                                <input type="text" name="lectureName" id="lectureName" placeholder="Lecture Name" required/>
                            </div>
                             <div class="form-group">
                                <input type="text" name="lectureDesc" id="lectureDesc" placeholder="Lecture Description"/>
                            </div>
                            <div class="form-group" hidden>
                                <input name="courseId" id="courseId" value=${courseId} hidden/> 
                                <input type="text" name="type" id="type" value="PDF" hidden/>
                             </div>
                            <div class="form-group form-button">
                                <input type="submit" name="add" id="add" class="form-submit" value="Add Lecture"/>
                                
   							<input onclick="goBack()" type="button" name="Back" id="Back" style="margin:5px;" class="form-submit" value="<< Back"/>
                            
  
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
	
		 <script type="text/javascript">
	    <#include "/static/js/jquery.min.js">
	    function goBack() {
  		window.history.back();}
	</script>	
    
</body>
</html>