
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
	<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>Add your Symptoms</title>
    
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
   <p><font color="red">${error}</font></p>
   <h3>Add your Symptom</h3>
   
   <form action="/edu/SymptomCheck.htm" method="post">
     What symptoms have you been having recently?                 <input type="text" name="symptom" class="form-control"/>
	 Do you have any medical conditions?                          <input type="text" name="medHistory" class="form-control"/>
	 How long have your symptoms overall been happening?          <input type="text" name="symptomDuration" class="form-control"/>
	 Have you ever had any surgeries or hospitalizations?         <input type="text" name="pastSurgery" class="form-control"/>
	 Do you currently take any medicines, herbals, or supplements?<input type="text" name="otherMedicine" class="form-control"/>	
	 Do you have any allergies or medication reactions?           <input type="text" name="allergy" class="form-control"/> 	
     <br>
     <input type="submit" value="Submit" class="btn-primary form-control"/>
   </form>  
       <p class="text-left"> <font size="2"> <a href="/edu/task.htm">Go Back to HomePage</a> </font></p>
      </div>  
  </div>
</body>
</html>


 
 
	

 
 
