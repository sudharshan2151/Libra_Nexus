package com.project.service;

import java.util.List;

import com.project.dao.FeedbackDAO;
import com.project.dao.FeedbackDAOImpl;
import com.project.entity.Feedback;
import com.project.exception.SomethingWentWrongException;

public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackDAO feedbackDAO=new FeedbackDAOImpl();

    

    @Override
    public void addFeedback(Feedback feedback)throws SomethingWentWrongException {
        feedbackDAO.addFeedback(feedback);
    }

    @Override
    public void updateFeedback(Feedback feedback) throws SomethingWentWrongException{
        feedbackDAO.updateFeedback(feedback);
    }

    @Override
    public Feedback getFeedbackById(int feedbackId) throws SomethingWentWrongException {
        return feedbackDAO.getFeedbackById(feedbackId);
    }

    @Override
    public List<Feedback> getAllFeedbacks() throws SomethingWentWrongException{
        return feedbackDAO.getAllFeedbacks();
    }

    @Override
    public List<Feedback> getFeedbacksByStudentId(int studentId) throws SomethingWentWrongException{
        return feedbackDAO.getFeedbacksByStudentId(studentId);
    }

    // You can implement additional methods here as needed
}
