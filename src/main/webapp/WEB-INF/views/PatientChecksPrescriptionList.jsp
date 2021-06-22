<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
	<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>PatientPrescriptionList</title>
    
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

 tr.disabled {
    background-color: grey;
}
</style>
  </head>
<body style="background-color: #4b5257" background = "https://educationupdatesonline.files.wordpress.com/2014/02/health_online_medical_powerpoint_templates_and_powerpoint_backgrounds_0511_title.jpg">
<P color="white">${headerMsg}</P>
<%@ include file="/resources/static/navbar.jsp"%>
  <div class="container">
  <div class="jumbotron">
        <h3>My Prescriptions</h3>
       
         <table id="booking">
          <tr>
            <th>Symptom</th>
            <th>Medical History</th>
            <th>Symptom Duration</th>
            <th>Past Surgery</th>
            <th>Other Medicine</th>
            <th>Allergy</th>
            <th>Dosage</th>
            <th>Prescription</th>
          </tr>
           <c:forEach items="${a}" var="a" varStatus="count">   
          <tr>
           <form  action="/edu/task/checkPrescription.htm" method="post">
		    <td><input type="text" name="patientSymptom" value ="${a.symptom}" readonly></td>  
            <td><input type="text" name="patientMedHistory" value="${a.medHistory}" readonly></td>
            <td><input type="text" name="patientSymptomDuration" value="${a.symptomDuration}" readonly></td>
            <td><input type="text" name="patientPastSurgery" value="${a.pastSurgery}" readonly></td>
            <td><input type="text" name="patientOtherMedicine" value ="${a.otherMedicine}" readonly ></td>
            <td><input type="text" name="patientAllergy" value="${a.allergy}" readonly></td>
            <td><input type="text" name="patientDosage" value="${a.dosage}" readonly></td>
            <td><input type="text" name="patientPrescription" value="${a.prescription}" readonly></td>
		    <td> <input type="hidden" id="${count.count}" name="count" value="${a.symptomId}" readonly/></td>
		    <td><input type="submit" name="patientPrescription" value="Get prescription" readonly></td> 
		    </form>     
		    </tr>
		  </c:forEach>
        </table>  
       <p class="text-left"> <font size="2"> <a href="/edu/task.htm">Go Back to HomePage</a> </font></p>
      </div>  
  </div>
</body>
</html>


 
 
	