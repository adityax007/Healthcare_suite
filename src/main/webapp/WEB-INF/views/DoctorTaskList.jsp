	<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>Doctor's Task List</title>
    
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
        <h3>Check your Daily Task</h3>          
        <form action="/edu/doctorsTask.htm" method="post">
          <div class="form-group">
            <input type="submit" name="addAppointment" class="form-control" placeholder="addAppointment" value="Add Appointment Slots">
          </div>
          <div class="form-group">
            <input type="submit" name="checkAppointment" class="form-control" placeholder="checkPatient" value="Check Appointments">
          </div>
          <div class="form-group">
            <input type="submit" name="checkPatient" class="form-control" placeholder="checkPatient" value="Check E-prescription Request">
          </div>
        </form>
      </div>  
  </div>
</body>
</html>


 
 
	