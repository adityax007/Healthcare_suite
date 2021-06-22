package com.healthcare.mgmt.controller;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.mgmt.dao.AppointmentDao;
import com.healthcare.mgmt.dao.SymptomDao;
import com.healthcare.mgmt.dao.UserDao;
import com.healthcare.mgmt.pojo.Appointment;
import com.healthcare.mgmt.pojo.Symptoms;
import com.healthcare.mgmt.pojo.User;

@Controller
public class DoctorsController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }	
	
	@RequestMapping(value="/doctorsTask", method=RequestMethod.POST)
	public ModelAndView doctorsSelectedTask(@ModelAttribute() Appointment appointment,HttpServletRequest req,Model model) throws Exception
	{
		ModelAndView mv = null;
		String addAppointment   = req.getParameter("addAppointment");
		String checkPatient     = req.getParameter("checkPatient");
		String checkAppointment = req.getParameter("checkAppointment");
		
		if (addAppointment != null)
		{
			mv = new ModelAndView("DoctorAddsAppointment");
		}
		else if (checkAppointment != null)
		{
			mv = new ModelAndView("DoctorChecksAppointment");
			HttpSession session= req.getSession();
	        String userName = (String)session.getAttribute("userName");
	        			
    	    AppointmentDao appointmentDao = new AppointmentDao();
		    List<Appointment> a = appointmentDao.getDoctorAppointmentList(userName);
			model.addAttribute("a",a);	
		}
		else if(checkPatient != null)
		{
			mv = new ModelAndView("DoctorChecksPatient");
    	    SymptomDao sdao = new SymptomDao();
    	    List<Symptoms> s1 = sdao.DoctorChecksSymptomList();
    	    model.addAttribute("a1",s1);
		}
			return mv;
	}
	
	@RequestMapping(value="/addAppointment", method=RequestMethod.POST)
	public ModelAndView doctorAddsAppointment(HttpServletRequest req,@Valid @ModelAttribute("appointment") Appointment appointment,BindingResult bindingresult, Model model) throws Exception
	{
	
		ModelAndView mv = new ModelAndView("AppointmentAdded");
		
		HttpSession session= req.getSession();
        String userName = (String)session.getAttribute("userName");
                
        UserDao udao = new UserDao();
        long doctorUserId = udao.getUserId(userName);
        		
		String appointmentTime      = req.getParameter("appointment_time");
		String appointment_location = req.getParameter("appointment_location");
		String appointment_date     = req.getParameter("appointment_date");	
		
		String TimeRegex="^(?:(?:([01]?\\d|2[0-3]):)?([0-5]?\\d):)?([0-5]?\\d)$";
		if (!appointmentTime.matches(TimeRegex))
		{
			model.addAttribute("error", "Please enter Appointment Time in correct format (00:00:00 format)");
			return new ModelAndView("DoctorAddsAppointment");
		}
		
//		String dateRegex="^(0[1-9]|[12][0-9]|3[01])[- \.](0[1-9]|1[012])[- \.](19|20)\\d\\d$";
//		if (!appointmentTime.matches(dateRegex))
//		{
//			model.addAttribute("error", "Please enter Appointment Date in correct format (dd/mm/yyyy format)");
//			return new ModelAndView("DoctorAddsAppointment");
//		}
		
		
		if (appointmentTime == "" || appointment_location=="" || appointment_date == "") {
			model.addAttribute("error", "All the fileds are mandatory");
			return new ModelAndView("DoctorAddsAppointment");
		}
		
		appointment.setDoctor_name(userName);
		appointment.setAppointment_location(appointment_location);
		appointment.setDoctor_id(doctorUserId);
		AppointmentDao aDao =new AppointmentDao();
		aDao.addAppointment(appointment);
		return mv;
	}
	
	@RequestMapping(value="/readSymptoms", method=RequestMethod.POST)
	public ModelAndView readSymptoms(HttpServletRequest req, Model model) throws Exception
	{
		ModelAndView mv= new ModelAndView("DoctorAddsPrescription");
		String symptomId = req.getParameter("count");		
		SymptomDao sdao = new SymptomDao();
		long symptom_id = Long.parseLong(symptomId);
		List<Symptoms> s = sdao.getParticularSymptom(symptom_id);
		model.addAttribute("a",s);
		return mv;
	}
	
	@RequestMapping(value="/addPrescription.htm", method=RequestMethod.POST)
	public ModelAndView addPrescription(HttpServletRequest req, Model model) throws Exception
	{
			
		ModelAndView mv = new ModelAndView("DoctorAddedPrescriptionSuccessfully");
		String patientPrescription = req.getParameter("patientPrescription");
		String patientDosage = req.getParameter("patientDosage");
		String SymptomId = req.getParameter("symptomId");
		
		if (patientDosage == "" || patientPrescription =="") {
			model.addAttribute("error", "Dosage and Prescription are mandatory");
			return new ModelAndView("DoctorChecksPatient");
		}
		
		SymptomDao sdao = new SymptomDao();
		sdao.updatePrescription(patientDosage, patientPrescription, SymptomId);		
		return mv;
	}
	
	@RequestMapping(value="/doctorsTask", method=RequestMethod.GET)
	public ModelAndView checkDailyRequest(HttpServletRequest req, Model model) throws Exception
	{	
		model.addAttribute("authorizationFailed","You are not authorized to access this page");
        ModelAndView mv = new ModelAndView("Login");
        HttpSession session= req.getSession(false);
		String sessionName = (String)session.getAttribute("userName");
        if (sessionName !=null)
        {
        	UserDao u = new UserDao();
            User user = u.checkCredentials(sessionName);
        	
        	if (user.getRole().equals("patient"))
            {
              model.addAttribute("authorizationFailed","You are not authorized to access this page");
              mv = new ModelAndView("Login");
            }
            else if (user.getRole() == null)
            {
            	model.addAttribute("authorizationFailed","You are not authorized to access this page");
                mv = new ModelAndView("Login");
            }
            else
            {
        	  mv = new ModelAndView("DoctorTaskList");	
            }
        }
	    return mv;
	}
	
	@RequestMapping(value="/addAppointment", method=RequestMethod.GET)
	public ModelAndView authorizeDoctorAddsAppointment(HttpServletRequest req, Model model) throws Exception
	{
		ModelAndView mv = null;
		HttpSession session= req.getSession(false);
        String sessionName = (String)session.getAttribute("userName");
        
        if (sessionName !=null)
        {
        	UserDao u = new UserDao();
            User user = u.checkCredentials(sessionName);
        	
        	if (user.getRole().equals("patient"))
            {
              model.addAttribute("authorizationFailed","You are not authorized to access this page");
              mv = new ModelAndView("Login");
            }
            else if (user.getRole() == null)
            {
            	model.addAttribute("authorizationFailed","You are not authorized to access this page");
                mv = new ModelAndView("Login");
            }
            else
            {
        	  mv = new ModelAndView("DoctorAddsAppointment");	
            }
        } 
        else
        {
        	model.addAttribute("authorizationFailed","You are not authorized to access this page");
            mv = new ModelAndView("Login");
        }
	    return mv;
	}	
	
	@RequestMapping(value="/readSymptoms", method=RequestMethod.GET)
	public ModelAndView authorizeReadSymptoms(HttpServletRequest req, Model model) throws Exception
	{
		model.addAttribute("authorizationFailed","You are not authorized to access this page");
        ModelAndView mv = new ModelAndView("Login");
		HttpSession session= req.getSession(false);
        String sessionName = (String)session.getAttribute("userName");        
        
        if (sessionName !=null)
        {
        	UserDao u = new UserDao();
            User user = u.checkCredentials(sessionName);
        	
        	if (user.getRole().equals("patient"))
            {
              model.addAttribute("authorizationFailed","You are not authorized to access this page");
              mv = new ModelAndView("Login");
            }
            else if (user.getRole() == null)
            {
            	model.addAttribute("authorizationFailed","You are not authorized to access this page");
                mv = new ModelAndView("Login");
            }
            else
            {
        	  mv = new ModelAndView("DoctorChecksPatient");	
            }
        }  
        
	    return mv;		
	}
	
	@RequestMapping(value="/addPrescription.htm", method=RequestMethod.GET)
	public ModelAndView authorizeAddPrescription(HttpServletRequest req, Model model) throws Exception
	{
		model.addAttribute("authorizationFailed","You are not authorized to access this page");
        ModelAndView mv = new ModelAndView("Login");
		
		HttpSession session= req.getSession(false);
        String sessionName = (String)session.getAttribute("userName");	
        
        if (sessionName !=null)
        {
        	UserDao u = new UserDao();
            User user = u.checkCredentials(sessionName);
        	
        	if (user.getRole().equals("patient"))
            {
              model.addAttribute("authorizationFailed","You are not authorized to access this page");
              mv = new ModelAndView("Login");
            }
            else if (user.getRole() == null)
            {
            	model.addAttribute("authorizationFailed","You are not authorized to access this page");
                mv = new ModelAndView("Login");
            }
            else
            {
        	  mv = new ModelAndView("DoctorAddedPrescriptionSuccessfully");	
            }
        }  
        
		return mv;
	}	
		
	@RequestMapping(value="/sendEmail", method=RequestMethod.POST)
	public ModelAndView sendAppointmentCancellationMail(@ModelAttribute("appointment") Appointment appointment, HttpServletRequest req) throws Exception
	{		
		ModelAndView mv = new ModelAndView("EmailSentSuccessfully");
		HttpSession session= req.getSession();
        String sessionName = (String)session.getAttribute("userName"); 
        UserDao udao = new UserDao();
        String firstName = udao.getFirstName(sessionName);
		String emailBody = "Hello,"+"\n"+"Due to an emergency medical attention I will have to cancel the appointment number "+appointment.getAppointment_number()+". Request you to book any book any other slot. Apology for the rescheduling.\n"+"Regards,\n"+firstName;
		EmailController.sendEmail("emailprojectspring2019@gmail.com", "emailprojectspring2019@gmail.com", emailBody,"Appointment Cancellation");
		AppointmentDao adao = new AppointmentDao();
		adao.deleteAppointment(appointment.getAppointment_number());
		return mv;
	}
	
	@RequestMapping(value="/sendEmail")
	public ModelAndView authenticateSendAppointmentCancellationMail(@ModelAttribute("appointment") Appointment appointment, HttpServletRequest req,Model model) throws Exception
	{		
		model.addAttribute("authorizationFailed","You are not authorized to access this page");
        ModelAndView mv = new ModelAndView("Login");
		
		HttpSession session= req.getSession(false);
        String sessionName = (String)session.getAttribute("userName");	
        
        if (sessionName !=null)
        {
        	UserDao u = new UserDao();
            User user = u.checkCredentials(sessionName);
        	
        	if (user.getRole().equals("patient"))
            {
              model.addAttribute("authorizationFailed","You are not authorized to access this page");
              mv = new ModelAndView("Login");
            }
            else if (user.getRole() == null)
            {
            	model.addAttribute("authorizationFailed","You are not authorized to access this page");
                mv = new ModelAndView("Login");
            }
            else
            {
        	  mv = new ModelAndView("EmailSentSuccessfully");	
            }
        }  
        
		return mv;	
	}	
}
