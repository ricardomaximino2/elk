package com.brasajava.common.mail.service.impl;

import com.brasajava.common.mail.domain.entity.Mail;
import com.brasajava.common.mail.service.MailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.ZoneId;
import java.util.Date;


public class MailServiceImpl implements MailService {

    private static final String ACCOUNT_ACTIVATION = "BrasaJava Account Activation";
    //private static final String BRASAJAVA_ACCOUNT = "brasajava@gmail.com";
    private static final String BASE_URL_FOR_ACTIVATION = "http://localhost:8080/user/";
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public MailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public boolean sendActivationMail(Mail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            //mimeMessageHelper.setFrom(new InternetAddress(BRASAJAVA_ACCOUNT));
            mimeMessageHelper.setSubject(ACCOUNT_ACTIVATION);
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
            final Context ctx = new Context(mail.getLocale());
            ctx.setVariable("name", mail.getFirstName() + " " + mail.getLastName());
            ctx.setVariable("creationDate", Date.from(mail.getCreationDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            ctx.setVariable("url", BASE_URL_FOR_ACTIVATION + mail.getId());
            ctx.setVariable("image", "images/it.jpg"); // so that we can reference it from HTML

            final String htmlContent = this.templateEngine.process("mail", ctx);
            mimeMessageHelper.setText(htmlContent,true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
