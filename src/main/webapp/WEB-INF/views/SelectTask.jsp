<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>Select Task</title>
    
<style>
    .col-centered{
    float: none;
    margin: 0 auto;
}
</style>
  </head>
<body style="background-color: #4b5257" background = "https://educationupdatesonline.files.wordpress.com/2014/02/health_online_medical_powerpoint_templates_and_powerpoint_backgrounds_0511_title.jpg">
<P color="white">${headerMsg}</P>
	<%@ include file="/resources/static/navbar.jsp"%>
  <div class="container">
  <div class="jumbotron">
        <h3>Welcome to Online Health Care System</h3>
        
        <form action="/edu/task.htm" method="post">
          <div class="form-group">
            <input type="submit" name="bookAppointment" class="form-control" placeholder="bookAppointment" value="Schedule an Appointment">
          </div>
          <div class="form-group">
            <input type="submit" name="getPrescription" class="form-control" placeholder="getPrescription" value="Ask for E-Prescription">
          </div>
          <div class="form-group">
            <input type="submit" name="checkPrescription" class="form-control" placeholder="getPrescription" value="Check my E-Prescription">
          </div>
        </form>
      </div>  
  </div>
</body>
</html>


 
 
