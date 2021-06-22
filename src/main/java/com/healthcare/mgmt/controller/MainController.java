package com.healtcare.mgmt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.healtcare.mgmt.dao.UserDao;
import com.healtcare.mgmt.pojo.User;

@Controller
public class MainController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginUser() throws Exception
	{
		ModelAndView mv = new ModelAndView("Login");
		return mv;
	}
		
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logoutUser(HttpServletRequest req,Model model) throws Exception
	{
		ModelAndView mv = new ModelAndView("Login");
		HttpSession session= req.getSession();
		String userLoggedIn = (String)session.getAttribute("userName");
		session.removeAttribute(userLoggedIn);
		req.getSession().invalidate();
		return mv;
	}
	
	@ModelAttribute
	public void addingCommonObjects(Model model)
	{
	  model.addAttribute("headerMsg","Welcome to HeathCareApp");
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView registerUser (@ModelAttribute("registerUser") User user, HttpServletRequest req) throws Exception {
		ModelAndView mv = new ModelAndView("Register");
		return mv;
	}	
}


