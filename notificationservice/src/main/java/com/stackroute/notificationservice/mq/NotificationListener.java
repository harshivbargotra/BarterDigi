package com.stackroute.notificationservice.mq;

import javax.validation.Valid;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.notificationservice.dao.EmailRepo;
import com.stackroute.notificationservice.entity.EmailDetails;
import com.stackroute.notificationservice.entity.NotificationDetails;
import com.stackroute.notificationservice.entity.OfferDetails;
import com.stackroute.notificationservice.exception.NotificationException;
import com.stackroute.notificationservice.service.NotificationServiceImpl;



@Component
public class NotificationListener {
	
	@Autowired
	private NotificationServiceImpl notificationServiceImpl;
	
	@Autowired
	private EmailRepo emailRepo;
	
	@RabbitListener(queues=MQConfig.NOTIFICATION_QUEUE)
	public void emailListener(NotificationDetails details) throws NotificationException
	{
		//System.out.println("hello");
		// Adding new Emails to DB
		System.out.println("Simple Mail Listener");
		EmailDetails empresent = this.emailRepo.findByEmail(details.getRecipient());
		System.out.println("here2");
		if(empresent==null)
    	{
    		EmailDetails emailAdd = new EmailDetails();
    		emailAdd.setEmail(details.getRecipient());
    		emailRepo.save(emailAdd);
    	}
				
		notificationServiceImpl.sendSimpleMail(details);
	}
	
	
	@RabbitListener(queues=MQConfig.NOTIFICATION_QUEUE_ATTATCH)
	public void emailAttatchListener(NotificationDetails details) throws NotificationException
	{
		System.out.println("Mail with attatchment Listener");
		// Adding new Emails to DB
		EmailDetails empresent = this.emailRepo.findByEmail(details.getRecipient());
		if(empresent==null)
    	{
    		EmailDetails emailAdd = new EmailDetails();
    		emailAdd.setEmail(details.getRecipient());
    		emailRepo.save(emailAdd);
    	}
		if(details.getAttachment()==null || details.getAttachment()=="")
			throw new NotificationException("PLease add a valid attatchment");
	
		notificationServiceImpl.sendMailWithAttachment(details);
	}
	
	@RabbitListener(queues=MQConfig.OFFER_QUEUE)
	public void offerListener(@Valid OfferDetails details) throws NotificationException
	{
		System.out.println("Offer Mail Listener");
		notificationServiceImpl.offerMail(details);
	}

}
