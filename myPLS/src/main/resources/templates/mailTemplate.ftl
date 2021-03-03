<html>
<body style="font-family:calibri">
<p>Please verify your email address<br></p>
    <form method="POST" action="http://localhost:4567/mailVerified">
   <input type="hidden" id="email" name="email" value=${email}><br><br>
   <input type="hidden" id="role" name="role" value=${role} ><br><br>
  <button type="submit" class="btn btn-default">Verify Email</button>
</form>
</body>
</html>