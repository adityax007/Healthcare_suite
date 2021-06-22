package com.healthcare.mgmt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.type.LongType;

import com.healthcare.mgmt.pojo.Appointment;
import com.healthcare.mgmt.pojo.User;

public class AppointmentDao extends DAO{
	
	public AppointmentDao()
	{}
	
	public Appointment addAppointment(Appointment a) throws Exception {//UserException {
		try {
			 begin();
			 getSession().save(a);
			 commit();
			 return a;
		} 
		catch (HibernateException e) 
		{
			rollback();
//			throw new UserException("Exception while creating user: " + e.getMessage());
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	
	public   List<Appointment> getAppointmentList () throws Exception { //UserException {
        List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			begin();
			Query q = getSession().createQuery("from Appointment where patient_id is not null" );
			appointmentList=q.list();
			commit();
			return appointmentList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get appointment list ", e); //UserException("Could not get user " + userId, e);
		}
	}
	
	public   List<Appointment> getUserAppointmentList (String sessionName) throws Exception { //UserException {
        List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			begin();
			Long convertedLong = (Long) getSession().createQuery("select userId   from User where email_id = :email_id").setParameter("email_id", sessionName).uniqueResult();
			Query q = getSession().createQuery("from Appointment where patient_id=:convertedLong" );
			q.setLong("convertedLong", convertedLong);
			appointmentList=q.list();
			commit();
			return appointmentList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get appointment list ", e); //UserException("Could not get user " + userId, e);
		}
	}
	
	public   List<Appointment> getDoctorAppointmentList (String userName) throws Exception { //UserException {
        List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			begin();
			Long convertedLong = (Long) getSession().createQuery("select userId   from User where email_id = :email_id").setParameter("email_id", userName).uniqueResult();
			Query q = getSession().createQuery("from Appointment where doctor_id=:convertedLong" );
			q.setLong("convertedLong",convertedLong);
			appointmentList=q.list();
			commit();
			return appointmentList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get appointment list ", e); //UserException("Could not get user " + userId, e);
		}
	}
	
	public  List<Appointment> getBookedAppointment (String sessionName,String appointmentDate, String doctorName,String appointmentLocation) throws Exception { //UserException {
        List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			begin();
			Long convertedLong = (Long) getSession().createQuery("select userId   from User where email_id = :email_id").setParameter("email_id", sessionName).uniqueResult();
			Query q = getSession().createQuery("from Appointment where appointment_date = :appointmentDate  and doctor_name= :doctorName and appointment_location=:appointmentLocation and patient_id =:convertedLong" );
			q.setParameter("appointmentDate", appointmentDate);
			q.setParameter("doctorName", doctorName);
			q.setParameter("appointmentLocation", appointmentLocation);
			q.setLong("convertedLong",convertedLong);
			appointmentList=q.list();
			commit();
			return appointmentList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get appointment list ", e); //UserException("Could not get user " + userId, e);
		}
	}
	
	
	
	public  List<Appointment> getMyBookedSlots (String sessionName) throws Exception { //UserException {
        List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			begin();
			Long convertedLong = (Long) getSession().createQuery("select userId   from User where email_id = :email_id").setParameter("email_id", sessionName).uniqueResult();
			Query q = getSession().createQuery("from Appointment where patient_id =:convertedLong" );
			q.setLong("convertedLong",convertedLong);
			appointmentList=q.list();
			commit();
			return appointmentList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get appointment list ", e); //UserException("Could not get user " + userId, e);
		}
	}
	
	public void updateAppointment(String sessionName,String appointmentDate, String doctorName,String appointmentLocation) throws Exception {//UserException {
		try {
			 begin();	
			 Long convertedLong = (Long) getSession().createQuery("select userId   from User where email_id = :email_id").setParameter("email_id", sessionName).uniqueResult();
			 String patientName =  (String) getSession().createQuery("select firstName  from User where email_id = :email_id").setParameter("email_id", sessionName).uniqueResult();
			 Query query = getSession().createQuery("update Appointment set patient_id  = :userId, patient_name=:patientName where appointment_date = :appointmentDate  and doctor_name= :doctorName and appointment_location=:appointmentLocation");
			 query.setParameter("userId", convertedLong,LongType.INSTANCE);
			 query.setParameter("appointmentDate",appointmentDate);
			 query.setParameter("doctorName",doctorName);
			 query.setParameter("appointmentLocation",appointmentLocation);
			 query.setString("patientName",patientName);
			 query.executeUpdate();			 			 
			 commit();
		} 
		catch (HibernateException e) 
		{
			rollback();
//			throw new UserException("Exception while creating user: " + e.getMessage());
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void deleteAppointment(long appointmentId) throws Exception {//UserException {
		try {
			begin();
			Query query = getSession().createQuery("delete from Appointment where appointment_number=:appointmentId");
			query.setLong("appointmentId",appointmentId);
			query.executeUpdate();	
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not delete appointment ", e);
		}
	}

}
