package com.project.dao;

import java.util.List;

import com.project.entity.Feedback;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;

public interface FeedbackDAO {
	
	    void addFeedback(Feedback feedback) throws SomethingWentWrongException;
	    void updateFeedback(Feedback feedback) throws SomethingWentWrongException;
	    Feedback getFeedbackById(int feedbackId) throws SomethingWentWrongException;
	    List<Feedback> getAllFeedbacks() throws SomethingWentWrongException;
	    List<Feedback> getFeedbacksByStudentId(int studentId) throws SomethingWentWrongException;
	    List<Feedback> getFeedbacksByBook(int book) throws SomethingWentWrongException, NoRecordFoundException ;
		    
	


}
