	<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>AddAppointment</title>
    
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
        <h3>Add Appointment</h3>
        <p><font color="red">${error}</font></p>
        <form action="/edu/addAppointment.htm" method="post">
          <div class="form-group">
             Appointment Time:     <input type="text" name="appointment_time" class="form-control" placeholder="(00:00:00 format)" >
             Appointment Location: <input type="text" name="appointment_location" class="form-control"  >
             Appointment Date:     <input type="text" name="appointment_date" class="form-control" placeholder="dd/mm/yyyy">
            <br>
            <input type="submit" name="submit" class="form-control" value="Submit">
          </div>
        <p class="text-left"><a href="/edu/doctorsTask.htm">Go Back</a></p>
        </form>
      </div>  
  </div>
</body>
</html>


 
 
	