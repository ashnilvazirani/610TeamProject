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
                        <h2 class="form-title">Learner Feedback</h2>
                        <form method="POST" class="register-form" id="student-feedback-form" name="studentFeedbackForm" action="/addStudentFeedback">
                          </font>
                          	<div class="form-group" hidden>
                                <input type="text" name="learnerId" id="learnerId" value= ${learnerId} />
                           </div>
                           <div class="form-group" hidden>
                                <input type="text" name="courseId" id="courseId" value= ${courseId} />
                           </div>
                            <div class="form-group">
                                <input type="number" name="rating" id="rating" placeholder="Rating (between 1 and 5)" min="1" max="5" required/>
                            </div>
                            
                            <div class="form-group">
                                <input name="comment" id="comment" placeholder="Comment"/>
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
</body>
</html>