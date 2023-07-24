package com.project.service;

import java.util.List;

import com.project.entity.Feedback;
import com.project.exception.SomethingWentWrongException;

public interface FeedbackService {
	
	    void addFeedback(Feedback feedback) throws SomethingWentWrongException;
	    void updateFeedback(Feedback feedback) throws SomethingWentWrongException;
	    Feedback getFeedbackById(int feedbackId) throws SomethingWentWrongException;
	    List<Feedback> getAllFeedbacks() throws SomethingWentWrongException;
	    List<Feedback> getFeedbacksByStudentId(int studentId) throws SomethingWentWrongException;
	    // You can add additional methods as needed
	

}
