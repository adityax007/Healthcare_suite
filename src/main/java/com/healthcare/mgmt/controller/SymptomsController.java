package com.healtcare.mgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healtcare.mgmt.dao.SymptomDao;
import com.healtcare.mgmt.dao.UserDao;
import com.healtcare.mgmt.pojo.Symptoms;
import com.healtcare.mgmt.pojo.User;

@Controller
public class SymptomsController {
	
	@RequestMapping(value="/SymptomCheck", method=RequestMethod.POST)
    public ModelAndView checkSymptoms(@Valid @ModelAttribute("symptoms") Symptoms symptoms,BindingResult bindingresult, HttpServletRequest req,Model model, RedirectAttributes redirect) throws Exception
	{ 
		
		if (bindingresult.hasErrors()) {
			model.addAttribute("error", "Symptom and Symptom Duration fields are mandatory");
			return new ModelAndView("Symptoms");
		}
				
		ModelAndView mv        = null;
		HttpSession session    = req.getSession();
		String sessionName     = (String)session.getAttribute("userName");		
		UserDao udao           = new UserDao();
		long userId            = udao.getUserId(sessionName);
		String patient_name    = udao.getFirstName(sessionName);
		
		symptoms.setUserId(userId);
		symptoms.setPatient_name(patient_name);
	    SymptomDao sdao = new SymptomDao();
	    sdao.addSymptoms(symptoms);
	    mv = new ModelAndView("SymptomsAdded");
	    return mv;	
	}	
	
	@RequestMapping(value="/SymptomCheck")
    public ModelAndView authorizecheckSymptoms(@ModelAttribute("s") Symptoms s, HttpServletRequest req,Model model, RedirectAttributes redirect) throws Exception
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
	           mv                     = new ModelAndView("SymptomsAdded");
	 	  	   UserDao udao           = new UserDao();
	 		   long userId            = udao.getUserId(sessionName);
	 		   String patient_name    = udao.getFirstName(sessionName);
	 	       s.setUserId(userId);
	 		   s.setPatient_name(patient_name);
	 	  	   SymptomDao sdao        = new SymptomDao();
	 		   List<Symptoms> symptom = sdao.getSymptomList(sessionName);
	 		   model.addAttribute("a",symptom);		
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

