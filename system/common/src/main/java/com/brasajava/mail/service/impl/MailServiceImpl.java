package com.brasajava.mail.service.impl;

import com.brasajava.mail.service.MailService;
import com.brasajava.mail.service.MailTemplateService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

public class MailServiceImpl implements MailService {

	private final JavaMailSender emailSender;
	private final MailTemplateService mailTemplateService;


	public MailServiceImpl(JavaMailSender emailSender, MailTemplateService mailTemplateService) {
		this.emailSender = emailSender;
		this.mailTemplateService = mailTemplateService;
	}

	@Override
	public Integer sendSimpleMessage(String to, String subject, String text) {
		int response = -1;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		try {
		emailSender.send(message);
		response = 0;
		}catch(MailException ex){
			ex.printStackTrace();
		}
		return response;
	}

	@Override
	public Integer sendEmailWithAttachment(String to, String subject, String text, String filePath) {
		int response = -1;
		MimeMessage message = emailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			
			FileSystemResource file = new FileSystemResource(new File(filePath));
			helper.addAttachment("attachmentName.txt", file);
			
			emailSender.send(message);
			response = 0;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Integer sendEmailWithAttachment(String to, String subject, String text, File file) {
		int response = -1;
		MimeMessage message = emailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setTo(to.split(","));
			helper.setSubject(subject);
			helper.setText(text);
			
			helper.addAttachment ("attachmentName", file);
			
			emailSender.send(message);
			response = 0;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public Integer sendEmailWithAttachment(String[] to, String subject, String text, File file) {
		int response = -1;
		MimeMessage message = emailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			
			helper.addAttachment ("attachmentName", file);
			
			emailSender.send(message);
			response = 0;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public Integer sendEmailWithAttachment(String[] to, String subject, Map<String,Object> params, String template, File attachment, String attachmentName) {
		int response = -1;
		MimeMessage message = emailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setTo(to);
			helper.setSubject(subject);
			String content = mailTemplateService.build(params, template);
			helper.setText(content, true);
			
			helper.addAttachment ("attachmentName", attachment);
			
			emailSender.send(message);
			response = 0;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
