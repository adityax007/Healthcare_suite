package com.healthcare.mgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.mgmt.dao.AppointmentDao;
import com.healthcare.mgmt.dao.UserDao;
import com.healthcare.mgmt.pojo.Appointment;
import com.healthcare.mgmt.pojo.User;

@Controller
public class BookingSuccessController {
	
	@RequestMapping(value="/task/bookingSuccess", method=RequestMethod.POST)
	public ModelAndView showBookingSuccess(@ModelAttribute("bookingSuccess") Appointment appointment, HttpServletRequest req,Model model) throws Exception
	{
		HttpSession session= req.getSession();
        String sessionName = (String)session.getAttribute("userName");
        System.out.println("sessionName:"+sessionName);
        String appointmentDate = req.getParameter("selectedAppointmentDate");
        String doctorName = req.getParameter("selectedDoctorName");
        String appointmentLocation = req.getParameter("selectedAppointmentLocation");
        
        
		ModelAndView mv = new ModelAndView("BookingSuccess");
		AppointmentDao appointmentDao = new AppointmentDao();
		appointmentDao.updateAppointment(sessionName,appointmentDate,doctorName,appointmentLocation);	
		List<Appointment> a= appointmentDao.getBookedAppointment(sessionName,appointmentDate,doctorName,appointmentLocation);
		System.out.println("appointment date:");
	    model.addAttribute("a", a);
		return mv;
	}
	
	@RequestMapping(value="/task/bookingSuccess", method=RequestMethod.GET)
	public ModelAndView BookingSuccessAuthorization(HttpServletRequest req, Model model) throws Exception
	{
        
		ModelAndView mv = null;
		HttpSession session= req.getSession(false);
        String sessionName = (String)session.getAttribute("userName");
        UserDao u = new UserDao();
        User user = u.checkCredentials(sessionName);

        if (sessionName !=null)
        {     		        	
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
        	  mv = new ModelAndView("BookingSuccess");	
        	  AppointmentDao appointmentDao = new AppointmentDao();
       		  List<Appointment> a= appointmentDao.getUserAppointmentList(sessionName);
       	      model.addAttribute("a", a);
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
