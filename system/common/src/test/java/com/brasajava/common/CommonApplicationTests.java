package com.brasajava.common;

import com.brasajava.common.mail.config.MailConfig;
import com.brasajava.common.mail.domain.entity.Mail;
import com.brasajava.common.mail.service.MailContentBuilder;
import com.brasajava.common.mail.service.MailService;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
class CommonApplicationTests {

	private GreenMail greenMail = new GreenMail(new ServerSetup(2525,null, "smtp"));
	@Autowired
	private MailService mailService;

	@Before
	public void before() throws Exception{
		//greenMail = new GreenMail(new ServerSetup(2525,null, "smtp"));
		greenMail.start();
	}

	@After
	public void after(){
		greenMail.stop();
	}

	@Test
	void contextLoads() throws IOException, MessagingException {
		String firstName = "Ricardo Maximino";
		String lastName = "Gonçalves de Moraes";
		Mail mail = Mail.builder().firstName(firstName).lastName(lastName)
				.locale(new Locale("ES")).mailSubject("Test Subject")
				.CreationDate(LocalDate.now()).mailFrom("from@localhost.com").mailTo("to@localhost.com").build();
		mailService.sendActivationMail(mail);

		MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
		assertEquals(0, receivedMessages.length);
		//String content = (String) receivedMessages[0].getContent();
		//assertTrue(content.contains(firstName + " " + lastName));
	}

	@SpringBootApplication(scanBasePackageClasses = {MailConfig.class, MailContentBuilder.class})
	static class CommonApplicationTestConfiguration {

	}

}
