package com.example.muhammadimran.parkingbookingsystem.Models;

/**
 * Created by muhammad imran on 1/28/2017.
 */

public class userfeedbackModel {
    private String feedback;
    private String user_feedback_name;

    public userfeedbackModel() {
    }

    public userfeedbackModel(String feedback, String user_feedback_name) {
        this.feedback = feedback;
        this.user_feedback_name = user_feedback_name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getUser_feedback_name() {
        return user_feedback_name;
    }

    public void setUser_feedback_name(String user_feedback_name) {
        this.user_feedback_name = user_feedback_name;
    }
}
