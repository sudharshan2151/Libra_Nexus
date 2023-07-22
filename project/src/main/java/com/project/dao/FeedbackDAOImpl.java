package com.project.dao;

import java.util.List;

import com.project.entity.Feedback;
import com.project.exception.SomethingWentWrongException;
import com.project.utility.DbUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class FeedbackDAOImpl implements FeedbackDAO{
	

	    @Override
	    public void addFeedback(Feedback feedback) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.persist(feedback);
	            entityManager.getTransaction().commit();
	        } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new SomethingWentWrongException("Failed to add feedback."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public void updateFeedback(Feedback feedback) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.merge(feedback);
	            entityManager.getTransaction().commit();
	        } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new SomethingWentWrongException("Failed to update feedback."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public Feedback getFeedbackById(int feedbackId) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            return entityManager.find(Feedback.class, feedbackId);
	        } catch (Exception ex) {
	            throw new SomethingWentWrongException("Failed to get feedback by ID."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public List<Feedback> getAllFeedbacks() throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            TypedQuery<Feedback> query = entityManager.createQuery("SELECT f FROM Feedback f", Feedback.class);
	            return query.getResultList();
	        } catch (Exception ex) {
	            throw new SomethingWentWrongException("Failed to get all feedbacks."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public List<Feedback> getFeedbacksByStudentId(int studentId) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            TypedQuery<Feedback> query = entityManager.createQuery(
	                    "SELECT f FROM Feedback f WHERE f.student.id = :studentId", Feedback.class);
	            query.setParameter("studentId", studentId);
	            return query.getResultList();
	        } catch (Exception ex) {
	            throw new SomethingWentWrongException("Failed to get feedbacks by student ID."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }
	


}
