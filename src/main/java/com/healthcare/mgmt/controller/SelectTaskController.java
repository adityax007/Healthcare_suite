package com.healtcare.mgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.healtcare.mgmt.dao.AppointmentDao;
import com.healtcare.mgmt.dao.SymptomDao;
import com.healtcare.mgmt.dao.UserDao;
import com.healtcare.mgmt.pojo.Appointment;
import com.healtcare.mgmt.pojo.Symptoms;
import com.healtcare.mgmt.pojo.User;

@Controller
public class SelectTaskController {
	
	@RequestMapping(value="/task", method=RequestMethod.POST)	
	public ModelAndView selectTask(HttpServletRequest req, Model model) throws Exception
	{ 
		ModelAndView mv = null;
		String bookAppointment   = req.getParameter("bookAppointment");
		String getPrescription   = req.getParameter("getPrescription");
		String checkPrescription = req.getParameter("checkPrescription");
				
		if (bookAppointment !=null && getPrescription!=null)
		{
		  mv = new ModelAndView("Symptoms");
		}
		else if (bookAppointment != null)
		{
	 	  mv = new ModelAndView("BookAppointment");
	      AppointmentDao appointmentDao = new AppointmentDao();
	      List<Appointment> a           = appointmentDao.getAppointmentList();
//	      List<Appointment> checkBookedAppointments = appointmentDao.getMyBookedSlots();
		  model.addAttribute("a",a);
		  return mv;
		}
		else if (getPrescription != null)
		{
		  mv = new ModelAndView("Symptoms");
		}
		else if (checkPrescription !=null)
		{
		  mv                  = new ModelAndView("PatientChecksPrescriptionList");
		  HttpSession session = req.getSession();
		  String userName     = (String)session.getAttribute("userName");
		  SymptomDao sdao     = new SymptomDao();
		  List<Symptoms> s    = sdao.getSymptomList(userName);
		  model.addAttribute("a",s);			
		}
		return mv;
	}
	
//	@RequestMapping(value="/task/PatientChecksPrescriptionList", method=RequestMethod.POST)
//	public ModelAndView PrescriptionList(HttpServletRequest req, Model model) throws Exception
//	{	       
//      ModelAndView mv = null;
//      HttpSession session = req.getSession();
//	  String sessionName = (String)session.getAttribute("userName");
//
//   	  UserDao u = new UserDao();
//      User user = u.checkCredentials(sessionName);
//
//  	  mv = new ModelAndView("PatientChecksPrescriptionList");	
//  	  SymptomDao sdao = new SymptomDao();
//      List<Symptoms> s = sdao.getSymptomList(sessionName);
//      model.addAttribute("a",s);	
//	  return mv;
//	}	
		
	@RequestMapping(value="/task/checkPrescription", method=RequestMethod.POST)
	public ModelAndView checkPatientsPrescription(HttpServletRequest req, Model model) throws Exception
	{
	  ModelAndView mv = new ModelAndView("PatientChecksPrescription");
	  
	  String patientSymptom = req.getParameter("patientSymptom");
	  String patientMedHistory = req.getParameter("patientMedHistory");
	  String patientSymptomDurationString= req.getParameter("patientSymptomDuration");
	  String patientPastSurgery = req.getParameter("patientPastSurgery");
	  
	  String symptomId = req.getParameter("count");		
	  SymptomDao sdao = new SymptomDao();
	  long symptom_id = Long.parseLong(symptomId);
	  List<Symptoms> s = sdao.getParticularSymptom(symptom_id);
	  model.addAttribute("a",s);
	  return mv;		
	}
	
	@RequestMapping(value="/task")
	public ModelAndView showTask(HttpServletRequest req, Model model) throws Exception
	{
      ModelAndView mv =null;
      HttpSession session= req.getSession(false);
	  String sessionName = (String)session.getAttribute("userName");
	  System.out.println("sessionName:"+sessionName);
      if (sessionName !=null)
      {
      	UserDao u = new UserDao();
          User user = u.checkCredentials(sessionName);
      	
      	if (user.getRole().equals("doctor"))
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
      	  mv = new ModelAndView("SelectTask");	
          }      	
      } 
      else
      {
    	  model.addAttribute("authorizationFailed","You are not authorized to access this page");
          mv = new ModelAndView("Login");
      }
		return mv;
	}			
	
	@RequestMapping(value="/task/PatientChecksPrescriptionList")
	public ModelAndView showPrescriptionList(HttpServletRequest req, Model model) throws Exception
	{	       
      ModelAndView mv = null;
      HttpSession session= req.getSession(false);
	  String sessionName = (String)session.getAttribute("userName");
      if (sessionName !=null)
      {
      	UserDao u = new UserDao();
        User user = u.checkCredentials(sessionName);
      	
      	if (user.getRole().equals("doctor"))
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
	      	mv = new ModelAndView("PatientChecksPrescriptionList");	
	      	SymptomDao sdao = new SymptomDao();
		    List<Symptoms> s = sdao.getSymptomList(sessionName);
		    model.addAttribute("a",s);	
          }      	
      }
      else
      {
        model.addAttribute("authorizationFailed","You are not authorized to access this page");
        mv = new ModelAndView("Login");
      }
	   return mv;
	}	
}
