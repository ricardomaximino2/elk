package com.brasajava.common.mail.service;

import com.brasajava.common.mail.domain.entity.Mail;

public interface MailService {
    boolean sendActivationMail(Mail mail);
}
