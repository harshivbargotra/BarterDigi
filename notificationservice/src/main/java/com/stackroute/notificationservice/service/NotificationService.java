package com.stackroute.notificationservice.service;


import com.stackroute.notificationservice.entity.NotificationDetails;
import com.stackroute.notificationservice.entity.OfferDetails;
import com.stackroute.notificationservice.exception.NotificationException;

public interface NotificationService {
    //Responsible to send an email without attachment
    String sendSimpleMail(NotificationDetails details);

    //Responsible to send an email with attachment
    String sendMailWithAttachment(NotificationDetails details) throws NotificationException;


	String offerMail(OfferDetails details) throws NotificationException;

}
