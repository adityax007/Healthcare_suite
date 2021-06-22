<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
	<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>CheckAppointment</title>
    
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
        <h3>Check Appointment</h3>
        
         <table id="booking">
          <tr>
            <th>Appointment Number</th>
            <th>Appointment Date</th>
            <th>Appointment Time</th>
            <th>Appointment Location</th>
            <th>Patients's Name</th>
            <th>Cancel</th>
          </tr>
           <c:forEach items="${a}" var="a" varStatus="count">   
          <tr>
            <form  action="/edu/sendEmail.htm" method="post">
            <td><input type="text" id ="appointment_number" name="appointment_number" value ="${a.appointment_number}" readonly></td>                     
		    <td><input type="text" id = "appointment_date" name="appointment_date" value ="${a.appointment_date}" readonly></td>                     
            <td><input type="text" id="appointment_time" name="appointment_time" value="${a.appointment_time}" readonly></td> 
            <td><input type="text" id="appointment_location" name="appointment_location" value="${a.appointment_location}" readonly></td>
            <td><input type="text" id ="patient_name" name="patient_name" value="${a.patient_name}" readonly></td> 
		    <td><input type="submit" name="submit" class="form-control" value="Cancel"></td>
		     </form>
		    </tr>
		  </c:forEach>
        </table>  
       
        <p class="text-left"><a href="/edu/doctorsTask.htm">Go Back to HomePage</a></p>
      </div>  
  </div>
</body>
</html>


 
 
	