package com.stackroute.userregistration.entity;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class NotificationDetails {

    @NotBlank(message = "Please add valid Recipient")
    @Email(message = "Please provide a valid email address")
    private String recipient;
    @NotBlank(message = "Please add message body")
    private String msgBody;
    @NotBlank(message = "Please add a subject")
    private String subject;
    private String attachment;

    public NotificationDetails(String recipient, String msgBody, String subject, String attachment) {
        super();
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
        this.attachment = attachment;
    }


    public NotificationDetails() {
        super();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

}
