package com.project.dao;

import java.util.List;
import com.project.entity.Rental;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;
import com.project.utility.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class RentalDAOImpl implements RentalDAO {
	

	    @Override
	    public void addRental(Rental rental) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.persist(rental);
	            entityManager.getTransaction().commit();
	        } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new SomethingWentWrongException("Failed to add rental."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public void updateRental(Rental rental) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	        	Rental k =  entityManager.find(Rental.class, rental.getId());
		           if(k==null) {
		            	throw new NoRecordFoundException("NO Data found :"+rental.getId());
		            }
	            entityManager.getTransaction().begin();
	            entityManager.merge(rental);
	            entityManager.getTransaction().commit();
	        } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new SomethingWentWrongException("Failed to update rental."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public Rental getRentalById(int rentalId) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	           Rental k =  entityManager.find(Rental.class, rentalId);
	           if(k==null) {
	            	throw new NoRecordFoundException("NO Data found :"+rentalId);
	            }
	            return k;
	        } catch (Exception ex) {
	            throw new SomethingWentWrongException("Failed to get rental by ID."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public List<Rental> getAllRentals() throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            TypedQuery<Rental> query = entityManager.createQuery("SELECT r FROM Rental r", Rental.class);
	            List<Rental> k = query.getResultList();
	            if(k==null) {
	            	throw new NoRecordFoundException("NO Data found ");
	            }
	            return k;
	        } catch (Exception ex) {
	            throw new SomethingWentWrongException("Failed to get all rentals."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public List<Rental> getRentalsByStudentId(int studentId) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            TypedQuery<Rental> query = entityManager.createQuery(
	                    "SELECT r FROM Rental r WHERE r.student.id = :studentId", Rental.class);
	            query.setParameter("studentId", studentId);
	            List<Rental> k = query.getResultList();
	            if(k==null) {
	            	throw new NoRecordFoundException("NO Data found :"+studentId);
	            }
	            return k;
	        } catch (Exception ex) {
	            throw new SomethingWentWrongException("Failed to get rentals by student ID."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }
	    
	    @Override
	    public Rental getRentalByStudentAndBook(int studentId, int bookId) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            TypedQuery<Rental> query = entityManager.createQuery(
	                    "SELECT r FROM Rental r WHERE r.student.id = :studentId AND r.book.id = :bookId", Rental.class);
	            query.setParameter("studentId", studentId);
	            query.setParameter("bookId", bookId);
	            List<Rental> rentals = query.getResultList();
	             if(rentals.isEmpty()) { 
	            	 throw new NoRecordFoundException("NO Data found :"); 
	             }
	             return rentals.get(0);
	             
	             
	        } catch (Exception ex) {
	            throw new SomethingWentWrongException("Failed to get rental by student and book. "+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	


}
