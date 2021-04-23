<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MyPLS - Login</title>

    <!-- Main css -->
    
    <style type="text/css">
	<#include "/static/css/style.css">
	</style>
    
</head>
<body>

    <div class="main">

        <!-- Sing in  Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-form">
                        <h2 class="form-title">View Answer: ${quizID}</h2>
                       	<p>            	
                       	<font color ="red">
                       		<#if status??>${message}</#if>
                       	</font>
                        <#list questions as question>
                            <div class="form-group" style="width=100%;">
                                <input type="text" name="questionNumber" id="questionNumber" placeholder="Question: ${question?counter}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input type="text" name="problem" id="message" placeholder="${question.problem}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input type="text" name="option1" id="option1" placeholder="${question.option1}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input type="text" name="option2" id="option2" placeholder="${question.option2}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input type="text" name="option3" id="option3" placeholder="${question.option3}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input type="text" name="option4" id="option4" placeholder="${question.option4}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input type="text" name="correctAnswer" id="correctAnswer" placeholder="Answer: ${question.correctOption}" readonly/>
                            </div>
                            <br><br>
                        </#list>
                        <input onclick="goBack()" type="submit" name="Back" id="Back" style="margin:5px;" class="form-submit" value="<< Back"/>
                    </div>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    
<script type="text/javascript">
<#include "/static/js/jquery.min.js">
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>