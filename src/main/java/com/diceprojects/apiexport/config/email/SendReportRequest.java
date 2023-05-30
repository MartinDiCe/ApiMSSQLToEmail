package com.diceprojects.apiexport.config.email;

import com.diceprojects.apiexport.persistences.models.ParamFecha;

public class SendReportRequest {
    private String recipientEmail;
    private String subject;
    private String body;
    private ParamFecha date;

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ParamFecha getDate() {
        return date;
    }

    public void setDate(ParamFecha date) {
        this.date = date;
    }
}

