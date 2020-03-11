package com.brasajava.mail;

import com.brasajava.mail.config.EmailConfig;
import com.brasajava.mail.service.MailService;
import com.brasajava.mail.service.impl.MailServiceImpl;
import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SedingEmailApplicationTests {

	@Rule
	public SmtpServerRule greenMail = new SmtpServerRule(2525);
	//public GreenMailRule greenMail = new GreenMailRule(ServerSetup.SMTPS);

	@Autowired
	MailServiceImpl emailService;

	@Autowired
	private MailService mailService;
	@Autowired
	private JavaMailSenderImpl javaMailSender;

	private final String to = "to@hosttest.com";
	private String[] arrayTo = {"One@hosttest.com","Two@hosttest.com"};
	private final String subject = "Subject";
	private Map<String, Object> params = new HashMap<>();
	private final String template = "testTemplate";
	private final String attachmentName = "attachment.txt";
	private final String text = "Some Text!!!";
	private File attachment;

	@Test
	public void contextLoads() {
		assertNotNull(mailService);
		assertNotNull(javaMailSender);
		assertNotNull(greenMail);
	}

	// simple message
	@Test
	public void sendSimpleMessageTest() {
		//GreenMailUser user = greenMail.getGreenMail().setUser(to, "username", "secret");
		assertEquals(0,emailService.sendSimpleMessage(to, subject, text).intValue());
	}

	// e-mail with pre-configured attachment name
	@Test
	public void sendMailOK() throws MessagingException {
		//GreenMailUser user = greenMail.getGreenMail().setUser(to, "username", "secret");
		ClassLoader classLoader = getClass().getClassLoader();
		attachment = new File(Objects.requireNonNull(classLoader.getResource(attachmentName)).getFile());
		assertEquals(0, emailService.sendEmailWithAttachment(to, subject, text, attachment).intValue());
		assertTrue(GreenMailUtil.getBody(greenMail.getReceivedMessages()[0]).contains(text));
		assertEquals(subject, greenMail.getReceivedMessages()[0].getSubject());
	}

	// e-mail with attachment
	@Test
	public void sendMailOK_Attachment() {
		//GreenMailUser user = greenMail.getGreenMail().setUser(to, "username", "secret");
		ClassLoader classLoader = getClass().getClassLoader();
		attachment = new File(Objects.requireNonNull(classLoader.getResource(attachmentName)).getFile());
		params.put("person", getObejct());
		assertEquals(0, emailService.sendEmailWithAttachment(arrayTo, subject, params, template, attachment, attachmentName).intValue());
		assertTrue(GreenMailUtil.getBody(greenMail.getReceivedMessages()[0]).contains(getObejct().getName()));
	}

	private Person getObejct(){
		return new Person();
	}
	class Person {
		final String name = "Name";
		final String lastName = "Last Name";
		final Date birthday = new Date();
		final String [] hobbies = {"One", "Two"};

		public String getName(){return name;}
		public String getLastName(){return lastName;}
		public Date getBirthday(){return birthday;}
		public String[] getHobbies(){return hobbies;}
	}

	@SpringBootApplication(scanBasePackageClasses = {EmailConfig.class})
	static class CommonApplicationTestConfiguration {}
}
