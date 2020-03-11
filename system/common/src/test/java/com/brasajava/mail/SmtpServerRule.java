package com.brasajava.mail;

import com.icegreen.greenmail.user.GreenMailUser;
import org.junit.rules.ExternalResource;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.internet.MimeMessage;

public class SmtpServerRule extends ExternalResource {

    private GreenMail greenMail;
    private int port;

    public SmtpServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        greenMail = new GreenMail(new ServerSetup(port, null, "smtp"));
        GreenMailUser user = greenMail.setUser("to@hosttest.com", "username", "secret");
        greenMail.start();
    }

    @Override
    protected void after() {
        super.after();
        greenMail.stop();
    }

    public MimeMessage[] getReceivedMessages(){
        return greenMail.getReceivedMessages();
    }

    public GreenMail getGreenMail() {
        return greenMail;
    }
}