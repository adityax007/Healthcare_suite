package com.healthcare.mgmt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.mgmt.dao.UserDao;
import com.healthcare.mgmt.pojo.User;

@Controller
public class HomePageController {
	
	@RequestMapping(value="/HomePage", method=RequestMethod.POST)	
	public ModelAndView selectTask(HttpServletRequest req,Model model) throws Exception
	{ 
		ModelAndView mv = null;	
		String username = req.getParameter("userName");
		String password = req.getParameter("password");
		
		HttpSession session= req.getSession();
		session.setAttribute("userName",req.getParameter("userName"));
				
		UserDao u = new UserDao();
		User userCredentials = u.checkCredentials(username);
		
		Boolean pwdCheck = false;	
		
		if (userCredentials !=null) 
		{
			if (username == "" || password=="")
			{
				model.addAttribute("invalidUser","Please enter correct username and password");
		    	return new ModelAndView("Login");
			}
			else if (BCrypt.checkpw(password, userCredentials.getPassword()))
				{
					System.out.println("user matched");
					pwdCheck=true;
					
					if( (userCredentials.getEmail()).equals(username))
					{
						if ( userCredentials.getRole().equals("doctor"))
						{
							mv = new ModelAndView("DoctorTaskList");
						}
						else if  ( userCredentials.getRole().equals("patient"))
						{
						  mv = new ModelAndView("SelectTask");	
						}
					}
					else
					{
						model.addAttribute("invalidUser","Please enter correct username and password");
						mv = new ModelAndView("Login");
					}
				}	
			else
			{
					model.addAttribute("invalidUser","Please enter correct username and password");
			    	return new ModelAndView("Login");
			}
		}
		else
		{
			model.addAttribute("invalidUser","Please enter correct username and password");
			mv = new ModelAndView("Login");
		}
		return mv;
	}
	
	@RequestMapping(value="/HomePage", method=RequestMethod.GET)	
	public ModelAndView authorizeSelectTask(HttpServletRequest req,Model model) throws Exception
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
	      		mv = new ModelAndView("DoctorTaskList");
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
}
