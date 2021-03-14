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
                        <h2 class="form-title">Chats: ${group.getGroupName()}</h2>
                       	<p>            	
                       	<font color ="red">
                       		<#if status??>${message}</#if>
                       	</font>
                        
                        <#list chats as chat>
                        <#--  represnt the chats on UI  -->
                           <div class="form-group" style="width=100%;">
                                <input type="text" name="message" id="message" placeholder="${users[chat_index].getName()} : ${chat.getMessageContent()}" readonly/>
                            </div>
				        </#list>
                        <br><br><br>
                        <form method="POST" class="post-chat" id="post-chat-form" action="/postChat">
                            <div class="form-group">
                                <input type="text" name="messageContent" id="messageContent" placeholder="Message Content"/>
                                <input type="hidden" name="userID" id="userID" value="${userID}"/>
                                <input type="hidden" name="groupDiscussionID" id="groupDiscussionID" value="${group.getGroupDiscussionID()}"/>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="postMessage" id="postMessage" class="form-submit" value="postMessage"/>
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
</body>
</html>