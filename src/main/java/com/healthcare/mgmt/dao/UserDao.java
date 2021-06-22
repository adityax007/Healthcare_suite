package com.healthcare.mgmt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.healthcare.mgmt.pojo.User;

public class UserDao extends DAO {

	public UserDao() {}

	public User get(long userId) throws Exception { //UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userId = :userId");
			q.setLong("userId", userId);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userId, e); //UserException("Could not get user " + userId, e);
		}
	}
	
	public String getFirstName(String userName) throws Exception { //UserException {
		try {
			begin();
			String result = (String) getSession().createQuery("select firstName   from User where email_id = :email_id").setParameter("email_id", userName).uniqueResult();
			UserDao.close();
			return result;
		} 
		catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userName, e); //UserException("Could not get user " + userId, e);
		}
	}
	
	public long getUserId(String userName) throws Exception { //UserException {
		try {
			begin();
			Long result = (Long) getSession().createQuery("select userId from User where email_id = :email_id").setParameter("email_id", userName).uniqueResult();		
			System.out.println("result:"+result);
			UserDao.close();
			return result;
		} 
		catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userName, e); //UserException("Could not get user " + userId, e);
		}
	}
	
	public User checkCredentials(String userName) throws Exception { //UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where email_Id =:userName" );
			q.setString("userName", userName);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userName, e); //UserException("Could not get user " + userId, e);
		}
	}	

	public User register(User u) throws Exception {//UserException {
		try {
			begin();
			getSession().save(u);
			commit();
			return u;
		} catch (HibernateException e) {
			rollback();
//			throw new UserException("Exception while creating user: " + e.getMessage());
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	public long validateUser (String userName) throws Exception { //UserException {
		try {
			begin();
			Query q = getSession().createQuery("Select count(1) from User where email_Id =:userName" );
			q.setString("userName", userName);
			long result = (Long) q.uniqueResult();
			commit();
			return result;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userName, e); //UserException("Could not get user " + userId, e);
		}
	}
	
	

	public void delete(User user) throws Exception {//UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not delete user " + user.getFirstName() + "  " + user.getLastName(), e);
//			throw new UserException("Could not delete user " + user.getFirstname() + "  " + user.getLastname(), e);
		}
	}
}
