package com.diceprojects.apiexport.service;

import org.springframework.core.io.Resource;


public interface EmailService {
    void sendEmail(String recipientEmail, String subject, String body, Resource attachment) throws Exception;

}