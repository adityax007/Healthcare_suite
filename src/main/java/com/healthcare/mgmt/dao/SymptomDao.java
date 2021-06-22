package com.healthcare.mgmt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.LongType;

import com.healthcare.mgmt.pojo.Appointment;
import com.healthcare.mgmt.pojo.Symptoms;

public class SymptomDao extends DAO{
	
	public SymptomDao() {}
	
	public void addSymptoms(Symptoms s) throws Exception {//UserException {
		try 
		{
		  begin();
		  getSession().save(s);
	   	  commit();
	   	  SymptomDao.close();
		} 
		catch (HibernateException e) 
		{
		  rollback();
		  throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void updatePrescription(String dosage,String prescription, String SymptomId) throws Exception {//UserException {
		try 
		{
		   	 begin();	
			 Query query = getSession().createQuery("update Symptoms set prescription = :prescription, dosage= :dosage where SymptomId=:SymptomId");
			 query.setParameter("prescription", prescription);
			 query.setParameter("dosage", dosage);
			 query.setParameter("SymptomId",SymptomId);
			 query.executeUpdate();			 			 
			 commit();
	   	  
		} 
		catch (HibernateException e) 
		{
		  rollback();
		  throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	
	public List<Symptoms> getParticularSymptom (long symptom_Id) throws Exception { //UserException {
        List<Symptoms> symptomList = new ArrayList<Symptoms>();
		try {
			begin();
			Query q = getSession().createQuery("from Symptoms where symptomId = :symptom_Id" );
			q.setLong("symptom_Id",symptom_Id);
			symptomList=q.list();
			commit();
			return symptomList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get appointment list ", e); //UserException("Could not get user " + userId, e);
		}
	}
	
	public List<Symptoms> getSymptomList (String userName) throws Exception { //UserException {
        List<Symptoms> symptomList = new ArrayList<Symptoms>();
		try {
			begin();
			Long userId = (Long) getSession().createQuery("select userId   from User where email_id = :email_id").setParameter("email_id", userName).uniqueResult();
			Query q = getSession().createQuery("from Symptoms where userId=:userId and prescription is not null" );
			q.setLong("userId", userId);
			symptomList=q.list();
			commit();
			return symptomList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get appointment list ", e); //UserException("Could not get user " + userId, e);
		}
	}
	
	public List<Symptoms> DoctorChecksSymptomList () throws Exception { //UserException {
        List<Symptoms> symptomList = new ArrayList<Symptoms>();
		try {
			begin();
			Query q = getSession().createQuery("from Symptoms where prescription is null" );
			symptomList= q.list();
			commit();
			return symptomList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get appointment list ", e); //UserException("Could not get user " + userId, e);
		}
	}
}
