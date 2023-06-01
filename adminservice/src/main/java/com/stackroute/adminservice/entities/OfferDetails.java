package com.stackroute.adminservice.entities;

import javax.validation.constraints.NotBlank;

public class OfferDetails {

		
	 	@NotBlank(message = "Please add message body")
	    private String msgBody;
	    @NotBlank(message = "Please add a subject")
	    private String subject;
	    private String attachment;
		public OfferDetails() {
			super();
			
		}
		public OfferDetails(String msgBody, String subject, String attachment) {
			super();
			this.msgBody = msgBody;
			this.subject = subject;
			this.attachment = attachment;
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
