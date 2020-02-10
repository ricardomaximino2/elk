package com.brasajava.common.mail.config;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@EnableConfigurationProperties(MailProperties.class)
@Configuration
public class MailConfig {

    private final MailProperties mailProperties;

    public MailConfig(MailProperties mailProperties){
        this.mailProperties = mailProperties;
    }

    public JavaMailSender getMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(mailProperties.getHost());
        javaMailSender.setPort(mailProperties.getPort());
        javaMailSender.setUsername(mailProperties.getUsername());
        javaMailSender.setPassword(mailProperties.getPassword());

        Properties properties = new Properties();
        properties.putAll(mailProperties.getProperties());
        javaMailSender.setJavaMailProperties(properties);
        return null;
    }
}
