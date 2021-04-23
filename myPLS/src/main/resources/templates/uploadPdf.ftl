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
                        <h2 class="form-title">Upload Lecture Content</h2>
                        
                          <form method="POST" class="add-lecture-form" id="pdfForm" name="pdfForm" enctype="multipart/form-data" action="/uploadPdf">
                            <div class="form-group">
                                File:
            					<input type="file" name="file" id="file" /> <br/>
                            </div>
                            <div hidden>
                            <input type="text" name="type" id="type" value="PDF"/>
                            </div>
                            
                            <div class="form-group form-button">
                                <input type="submit" name="upload" id="upload" class="form-submit" value="upload Lecture"/>
                                <div hidden>
                                <td scope="col">
							<input name="courseId" id=courseId value=${courseId} hidden>${courseId} </input>
							</div>
							</td>
                            </div> 
                            </form>
                    </div>                 
                </div>
            </div>
        </section>

    </div>

</body>
</html>