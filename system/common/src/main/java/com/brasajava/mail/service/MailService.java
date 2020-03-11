package com.brasajava.mail.service;

import java.io.File;
import java.util.Map;

public interface MailService {
	
	Integer sendSimpleMessage(String to, String subject, String text);
	
	Integer sendEmailWithAttachment(String to, String subject, String text, String filePath);
	
	Integer sendEmailWithAttachment(String to, String subject, String text, File file);
	
	Integer sendEmailWithAttachment(String[] to, String subject, String text, File file);
	
	Integer sendEmailWithAttachment(String[] to, String subject, Map<String,Object> params, String template, File attachment, String attachmentName);

}
