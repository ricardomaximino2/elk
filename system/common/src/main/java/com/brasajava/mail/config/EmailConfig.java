package com.brasajava.mail.config;

import com.brasajava.mail.service.MailService;
import com.brasajava.mail.service.MailTemplateService;
import com.brasajava.mail.service.impl.MailServiceImpl;
import com.brasajava.mail.service.impl.MailTemplateServiceImpl;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

import java.util.Properties;

@EnableConfigurationProperties(MailProperties.class)
@Configuration
public class EmailConfig {

    private static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";
    private MessageSource messageSource;
	
	@Bean
	public JavaMailSender createJavaMailSender(MailProperties mailProperties) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailProperties.getHost());
        javaMailSender.setPort(mailProperties.getPort());
        javaMailSender.setUsername(mailProperties.getUsername());
        javaMailSender.setPassword(mailProperties.getPassword());

        Properties properties = new Properties();
        properties.putAll(mailProperties.getProperties());
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
	}

    @Bean
    public MailService getEmailService(JavaMailSender emailSender, MailTemplateService mailTemplateService){
	    return new MailServiceImpl(emailSender,mailTemplateService);
    }

    @Bean
    public MailTemplateService getMailTemplateService(TemplateEngine templateEngine){
	    return new MailTemplateServiceImpl(templateEngine);
    }

}
