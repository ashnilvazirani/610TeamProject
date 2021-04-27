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
	 	</style>
	
		<style>
      input.right {
      	 display: inline-block;
		background: #6dabe4;
		color: #fff;
		border-bottom: none;
		width: auto;
		padding: 15px 39px;
		-webkit-border-radius: 5px;
		cursor: pointer;
        float: right;
      }
    </style>
     
</head>
<body>

    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
            
              <input onclick="goBack()" class="right" type="button" name="Back" id="Back" style="margin:5px;" value="<< Back"/>
                
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Learner Feedback</h2>
                        <form method="POST" class="register-form" id="student-feedback-form" name="studentFeedbackForm" action="/addStudentFeedback">
                          </font>
                          	<div class="form-group" hidden>
                                <input type="text" name="learnerId" id="learnerId" value= ${feedback.feedbackEntityId} />
                           </div>
                           <div class="form-group" hidden>
                                <input type="text" name="courseId" id="courseId" value= ${feedback.courseId} />
                           </div>
                            <div class="form-group">
                            	<#if (feedback.rating)!=0>
                                <input type="number" name="rating" value= ${feedback.rating} id="rating" placeholder="Rating (between 1 and 5)" min="1" max="5" required/>
                                <#else>
                                <input type="number" name="rating" id="rating" placeholder="Rating (between 1 and 5)" min="1" max="5" required/>
                                </#if>
                            </div>
                            
                            <div class="form-group">
                            	<#if (feedback.comments)??>
                                <input name="comment" id="comment" value= ${feedback.comments} placeholder="Comment"/>
                                 <#else>
                                <input name="comment" id="comment"  placeholder="Comment"/>
                                </#if>
                            </div>
                          
                            <div class="form-group form-button">
                                <input type="submit" name="add" id="add" class="form-submit" value="Add"/>
                                
                                
                            </div>           
                               
                        </form>
                    </div>                 
                </div>
            </div>
        </section>

    </div>
    
      <script type="text/javascript">
	    <#include "/static/js/jquery.min.js">
	    function goBack() {
  		window.history.back();}
	</script>	
	
</body>
</html>