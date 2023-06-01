package com.stackroute.notificationservice.controller;


import com.stackroute.notificationservice.entity.NotificationDetails;
import com.stackroute.notificationservice.entity.OfferDetails;
import com.stackroute.notificationservice.exception.NotificationException;
import com.stackroute.notificationservice.mq.MQConfig;
//import com.stackroute.notificationservice.mq.MQConfig;
import com.stackroute.notificationservice.service.NotificationService;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/notificationService")
public class NotificationController {

    @Autowired private NotificationService notificationService;
   
    @Autowired private RabbitTemplate template;
    // Sending a simple Email
    @PostMapping("simplemail")
    public String sendMail(@Valid @RequestBody NotificationDetails details)
    { 
     template.convertAndSend(MQConfig.notificationexchangeName, MQConfig.ROUTING_KEY, details);
//        String status
//        = notificationService.sendSimpleMail(details);
//        
//        return status;
     System.out.println("Mail Sent Successfully... to : " + details.getRecipient());
    return "Mail Sent Successfully... to : " + details.getRecipient();
     
    }

    // Sending email with attachment
    @PostMapping("mailwithattatchment")
    public String sendMailWithAttachment(
            @Valid @RequestBody NotificationDetails details) throws NotificationException
    {
    	template.convertAndSend(MQConfig.notificationexchangeName, MQConfig.ATTACH_ROUTING_KEY, details);
//        String status
//                = notificationService.sendMailWithAttachment(details);
//     
//        return status;
    	System.out.println("Mail with attachment Sent Successfully... to : " + details.getRecipient());
    	return ("Mail with attachment Sent Successfully... to : " + details.getRecipient());
    
    }
    
    @PostMapping("offermails")
    public String offerMails(
            @Valid @RequestBody OfferDetails details) throws NotificationException
    {
    	template.convertAndSend(MQConfig.notificationexchangeName, MQConfig.OFFER_ROUTING_KEY, details);
//        String status
//                = notificationService.offerMail(details);
//     
//        return status;
    	System.out.println("All Mails sent successfully");
    	return ("All Mails sent successfully");
    }


}
