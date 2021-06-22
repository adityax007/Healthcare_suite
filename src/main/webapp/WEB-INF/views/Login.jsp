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
<P color="white">${headerMsg}</P>
  <div class="container">
  <div class="col-xs-12 col-sm-8 col-md-4 col-lg-4 col-centered">
  <div class="jumbotron">

      <p><font color="red">${invalidUser}</font></p>
      <p><font color="red">${authorizationFailed}</font></p>
       
      <h5>Please login</h5>
       
        <form action="/edu/HomePage.htm" method="post">
          <div class="form-group">
            <input type="email" name="userName" class="form-control" placeholder="Enter Username">
          </div>
          <div class="form-group">
            <input type="password" name="password" class="form-control" placeholder="Enter password">
          </div>
          <input type="submit" value="Login" class="btn-primary form-control"/>
          <h4 class="text-center"><a href="/edu/register.htm">Create an account</a></h4>
        </form>
      </div>
  </div>
  
  </div>
</body>
</html>


 
 
