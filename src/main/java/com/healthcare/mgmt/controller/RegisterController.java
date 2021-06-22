package com.healthcare.mgmt.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.mindrot.jbcrypt.BCrypt;

import com.healthcare.mgmt.dao.UserDao;
import com.healthcare.mgmt.pojo.User;

@Controller
public class RegisterController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("user") User user,BindingResult bindingresult, ModelMap map, HttpServletRequest req) throws Exception
	{
		
		if (bindingresult.hasErrors()) {
			map.addAttribute("error", "Incorrect Input Please check your value");
			return new ModelAndView("Register");
		}
		
		ModelAndView mv    = new ModelAndView("Login");
		
		String password_confirmationString= req.getParameter("password_confirmation");
		
	    if (!user.getPassword().equals(password_confirmationString))
	    {
	    	map.addAttribute("passwordMismatch", "Please check entered password");
			return new ModelAndView("Register");
	    }

		String hashpwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        System.out.println(hashpwd);			

		user.setPassword(hashpwd);
		UserDao uDao = new UserDao();
		long result = uDao.validateUser(user.getEmail());
		if (result == 0)
		{
		  user = uDao.register(user);
		}
		else
		{
		  map.addAttribute("errorMsg","User already exists");
		  mv    = new ModelAndView("Register");
		}
		return mv;
	}
}
