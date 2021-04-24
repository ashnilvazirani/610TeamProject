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
                        <h2 class="form-title">Take Quiz: ${quizID} for ${courseName}</h2>
                            <p>            	
                            <font color ="red">
                                <#if status??>${message}</#if>
                            </font>
                        <form action="/submitQuiz" method="POST">
                            <#list questions as question>
                            <div class="form-group" style="width=100%;">
                                <input placeholder="Question: ${question?counter}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input placeholder="${question.problem}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input placeholder="${question.option1}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input placeholder="${question.option2}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input placeholder="${question.option3}" readonly/>
                            </div>
                            <div class="form-group" style="width=100%;">
                                <input placeholder="${question.option4}" readonly/>
                            </div>
                            
                            <div class="form-group" style="width=100%;">
                            <input name="question:${question?counter}" type="hidden" value="${question.getQuestionID()}" />
                                <select name="answer:${question?counter}" id="professorId">
                                <option disabled selected>--Select Choice--</option>
                                <option value="1">A</option>
                                <option value="2">B</option>
                                <option value="3">C</option>
                                <option value="4">D</option>
                                </select>
							</div>
                            <br><br>
                        </#list>
                        <input name="userId" type="hidden" value="${userId}" />
                        <input name="quizId" type="hidden" value="${quizID}" />
                        <input name="lectureId" type="hidden" value="${lectureId}" />
                        <br><br><br>
                        <input name="totalPoints" type="text" value="Total Marks:${questions?size * 10}" readonly/>
                        <input type="submit" name="submitQuiz" id="submitQuiz" style="margin:5px;" class="form-submit" value="Submit Quiz"/>
                    </form>
                    <br><br><br>
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