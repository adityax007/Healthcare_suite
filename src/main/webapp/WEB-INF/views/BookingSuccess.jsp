<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
	<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>BookAppointment</title>
    
<style>
    .col-centered{
    float: none;
    margin: 0 auto;
}

#booking {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#booking td, #booking th {
  border: 1px solid #ddd;
  padding: 8px;
}

#booking tr:nth-child(even){background-color: #f2f2f2;}

#booking tr:hover {background-color: #ddd;}

#booking th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
</style>
  </head>
<body style="background-color: #4b5257" background = "https://educationupdatesonline.files.wordpress.com/2014/02/health_online_medical_powerpoint_templates_and_powerpoint_backgrounds_0511_title.jpg">
<P color="white">${headerMsg}</P>
<%@ include file="/resources/static/navbar.jsp"%>
  <div class="container">
  <div class="jumbotron">
        <h3>Booking Appointment Confirmation</h3>
        <form modelAttribute="bookingSuccess" commandName="bookingSuccess">
        <P>Please Confirm your booking once</P>
         <table id="booking">
          <tr>
            <th>Appointment Date</th>
            <th>Appointment Time</th>
            <th>Doctor's Name</th>
            <th>Appointment Location</th>
            <th>Status</th>
          </tr>
            <c:forEach items="${a}" var="a">  
          <tr>
		    
            <td><input type="text" name="selectedAppointmentDate" value ="${a.appointment_date}" readonly></td>                     
            <td><input type="text" name="selectedAppointmentTime" value="${a.appointment_time}" readonly></td> 
            <td><input type="text" name="selectedDoctorName" value="${a.doctor_name}" readonly></td> 
            <td><input type="text" name="selectedAppointmentLocation" value="${a.appointment_location}" readonly></td>
		    <td><input type="submit" name="SelctedAppointment" class="form-control" value="Book"></td>
		  </tr>
		   </c:forEach>
        </table>
        </form>
       <p class="text-left"> <font size="2"> <a href="/edu/task.htm">Go Back to HomePage</a> </font></p>
      </div>  
  </div>
</body>
</html>


 
 
	