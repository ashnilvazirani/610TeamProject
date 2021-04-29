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
	
	      input.right {
      	 display: inline-block;
		background: #6c757d;
		color: #fff;
		border-bottom: none;
		width: auto;
		padding: 15px 39px;
		-webkit-border-radius: 5px;
		cursor: pointer;
        float: left;
      }
      
	</style>
	

     
</head>
<body>

    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
            
                   <input onclick="goBack()" class="right" type="button" name="Back" id="Back" style="margin:5px;" class="form-submit" value="<< Back"/>
               
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Add Discussion Group</h2>
                        <form method="POST" class="register-form" id="add-group-form" name="addGroupForm" action="/addGroupDiscussion">
                       	 <font color ="red">
                          </font>
                            <div class="form-group">
                                <input type="text" name="groupName" id="groupName" placeholder="Group Name" required/>
                            </div>
                             <div class="form-group">
                                <input type="text" name="groupTopic" id="groupTopic" placeholder="Topic Description" required/>
                            </div>
                            <div class="form-group">
                                <input type="hidden" name="userID" id="userID" placeholder="Topic Description" value="${userID}" required/>
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

  <!-- JS -->
  
  
      <script type="text/javascript">
	    <#include "/static/js/jquery.min.js">
	    function goBack() {
  		window.history.back();}
	</script>	
	
</body>
</html>