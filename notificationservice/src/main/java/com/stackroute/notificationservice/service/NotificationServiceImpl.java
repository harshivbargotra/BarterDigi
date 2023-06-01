package com.stackroute.notificationservice.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.stackroute.notificationservice.dao.EmailRepo;
import com.stackroute.notificationservice.entity.EmailDetails;
import com.stackroute.notificationservice.entity.NotificationDetails;
import com.stackroute.notificationservice.entity.OfferDetails;
import com.stackroute.notificationservice.exception.NotificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    

	@Autowired
	private EmailRepo emailRepo;

    @Override
    public String sendSimpleMail(NotificationDetails details) {
    	
    	//System.out.println(details);
    	
        try {
        	
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully... to : " + details.getRecipient();
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailWithAttachment(NotificationDetails details) throws NotificationException {
    	if(details.getAttachment()==null || details.getAttachment()=="")
    		throw new NotificationException("Please add an attatchment!!");
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
        	
            // Setting multipart as true for attachments to
            // be send
        
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            if(file.exists())
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            else throw new NotificationException("Please add a valid path for the attatchment !!");

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully to : " + details.getRecipient();
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

	@Override
	public String offerMail(OfferDetails details) throws NotificationException {
		
		List<EmailDetails> emails = this.emailRepo.findAll();
		if(emails.isEmpty())
			throw new NotificationException("No Emails available to send offers!!");
		NotificationDetails notiDetails = new NotificationDetails();
		notiDetails.setMsgBody(details.getMsgBody());
		notiDetails.setSubject(details.getSubject());
		try {
			
		if(details.getAttachment()==null || details.getAttachment()=="") {
		for(EmailDetails s : emails)
		{
			notiDetails.setRecipient(s.getEmail());
			String message =this.sendSimpleMail(notiDetails);
			System.out.println(message);
		}
		}
		else {
			notiDetails.setAttachment(details.getAttachment());
			for(EmailDetails s : emails)
			{
				notiDetails.setRecipient(s.getEmail());
				String message =this.sendMailWithAttachment(notiDetails);
				System.out.println(message);
			}
		}
		return "All mails send successfully";
		
		}
		catch (Exception e) {
			return "Error while sending the mails!!";
		}
		
	}
}
