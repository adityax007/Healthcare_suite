<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
	<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>MyPrescription</title>
    
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
        <h3>My Prescription</h3>
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
            <th>Action</th>
            
          </tr>
           <c:forEach items="${a}" var="a" varStatus="count"> 
          <form  action="/edu/task/checkPrescription/DownloadPDF.htm" method="post">
          <tr>
		    <td><input type="text" name="patientSymptom" value ="${a.symptom}" ></td>                     
            <td><input type="text" name="patientMedHistory" value="${a.medHistory}"></td> 
            <td><input type="text" name="patientSymptomDuration" value="${a.symptomDuration}"></td>
            <td><input type="text" name="patientPastSurgery" value="${a.pastSurgery}"></td> 
            <td><input type="text" name="patientOtherMedicine" value ="${a.otherMedicine}" ></td>                     
            <td><input type="text" name="patientAllergy" value="${a.allergy}"></td>    
            <td><input type="text" name="patientDosage" value="${a.dosage}"></td>
            <td><input type="text" name="patientPrescription" value="${a.prescription}"></td> 
            
            <c:choose>
			  <c:when test="${empty a.prescription}">
			   <td><input type="submit" name="patient" class="form-control" value="Download PDF" readonly></td> 
			  </c:when>
			  <c:otherwise>
			      <td><input type="submit" name="patient" class="form-control" value="Download PDF"></td> 
			  </c:otherwise>
			</c:choose>    
            </tr>
		     </form>
		  </c:forEach>
        </table>  
       <p class="text-left"> <font size="2"> <a href="/edu/task/PatientChecksPrescriptionList.htm">Go Back to HomePage</a> </font></p>
      </div>  
  </div>
</body>
</html>


 
 
	