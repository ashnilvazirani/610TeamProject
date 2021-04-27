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

        <!-- Sing in  Form -->
        <section class="sign-in">
            <div class="container">
            
              <input onclick="goBack()" class="right" type="button" name="Back" id="Back" style="margin:5px;" class="form-submit" value="<< Back"/>
                       
                <div class="signin-content">
                    <div class="signin-form">
                        <h2 class="form-title">Chats</h2>
                        <p>            	
                        <font color ="red">
                            <#if status??>${message}</#if>
                        </font>
                        
                        <#list groupChats as chat>
                        <#--  represnt the chats on UI  -->
                            <div class="form-group" style="width=100%;">
                                <input type="text" name="message" id="message" placeholder="${chat.getUserName()} : ${chat.getMessageContent()}" readonly/>
                            </div>
				        </#list>
                        <br><br><br>
                        <form method="POST" class="post-chat" id="post-chat-form" action="/postMessageForCourseGroup">
                            <div class="form-group">
                                <input type="text" name="messageContent" id="messageContent" placeholder="Message Content"/>
                                <input type="hidden" name="userID" id="userID" value="${userID}"/>
                                <input type="hidden" name="courseGroupID" id="courseGroupID" value="${courseGroupID}"/>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="postMessage" id="postMessage" class="form-submit" value="Post Message"/>
                             
                            </div>
                        </form>
                        
                      
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
  		window.history.back();}
	</script>	
</body>
</html>