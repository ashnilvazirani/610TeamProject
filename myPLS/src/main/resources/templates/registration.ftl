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
                        <h2 class="form-title">Registration</h2>
                        <form method="POST" class="register-form" id="register-form" name="registrationForm" action="/registerUser">
                           <#if status??>${message}</#if>
                            <div class="form-group">
                                <input type="text" name="fname" id="name" placeholder="First Name" required/>
                            </div>
                             <div class="form-group">
                                <input type="text" name="lname" id="name" placeholder="Last Name" required/>
                            </div>
                            <div class="form-group">
                                <input type="email" name="email" id="email" placeholder="Email: username@rit.edu" required/>
                            </div>
                          
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                            </div>              
                        </form>
                            <div class="signup-image">
                               <a href="http://localhost:4567/loginPage" class="signup-image-link" rel="link"
                               onclick="ValidateEmail(document.registrationForm.email)">I am already registered user</a> 
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
		alert("Valid email address!");
		document.form1.text1.focus();
		return true;
		}
		else
		{
		alert("You have entered an invalid email address!");
		document.form1.text1.focus();
		return false;
		}
		}
	</script>
	  <script type="text/javascript">
	<#include "/static/js/main.js">
	</script>
    
</body>
</html>