 <html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>Login</title>
    
<style>
    .col-centered{
    float: none;
    margin: 0 auto;
}
</style>
  </head>
<body style="background-color: #4b5257">
<P><font color="white">${headerMsg}</font></P>
  <div class="container">
  <div class="col-xs-12 col-sm-8 col-md-4 col-lg-4 col-centered">
  <div class="jumbotron">
        <p><font color="red">${error}</font></p>
        <p><font color="red">${passwordMismatch}</font></p>
        <h3><b>Please Register</b></h3>
       		<P><font>${headerMsg}</font></P>
			    <form action="/edu/login.htm" method="post">
			    	<div class="form-group">    			
			    	   <input type="radio" name="role" value="patient"> Are you a Patient? <br>
			        </div>
			    			  
			        <div class="form-group">
                       <input type="radio" name="role" value="doctor">  Are you a Doctor? <br>
  			    	</div>
   					<div class="form-group">
                         <input type="text" name="firstName" id="firstName" class="form-control input-sm" placeholder="First Name">
   					</div>
   					<div class="form-group">
   						<input type="text" name="lastName" id="last_name" class="form-control input-sm" placeholder="Last Name">
   					</div>

   					<div class="form-group">
   						<input type="text" name="age" id="age" class="form-control input-sm" placeholder="Age">
   					</div>
   					
   					<div class="form-group">    			
				    	  <input type="radio" name="gender" value="Female">  Female<br>
				        </div>
				    			  
				        <div class="form-group">
	                        <input type="radio" name="gender" value="Male"> Male <br>
	  			    	</div>
  
   					<div class="form-group">
   						<input type="text" name="dateOfBirth" id="dateOfBirth" class="form-control input-sm" placeholder="Date Of Birth (dd/mm/yyyy)">
   					</div>
   			
   					<div class="form-group">
   					     <P><font color="red">${errorMsg}</font></P>
   						<input type="email" name="email" id="email" class="form-control input-sm" placeholder="Email Address">
   					</div>

   					<div class="row">
   					<div class="col-xs-6 col-sm-6 col-md-6">
   						<div class="form-group">
   							<input type="password" name="password" id="password" class="form-control input-sm" placeholder="Password">
   						</div>
   					</div>
   					
   					<div class="col-xs-6 col-sm-6 col-md-6">
   					<div class="form-group">
   						<input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password">
   					</div>
   				  </div>
   			   </div>
   				<input type="submit" value="Register" class="btn btn-info btn-block">
   					<br>
   				<h4 class="text-left"><a href="/edu/login.htm">Back</a></h4>
   		</form>
      </div>
  </div>
  </div>
</body>
</html>


 
 
    
    
    