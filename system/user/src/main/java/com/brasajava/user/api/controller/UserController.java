package com.brasajava.user.api.controller;

import com.brasajava.common.mail.domain.entity.Mail;
import com.brasajava.common.mail.service.MailService;
import com.brasajava.user.api.converter.UserConverter;
import com.brasajava.user.api.dto.UserRequestDTO;
import com.brasajava.user.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/user")
public class UserController {

    private MailService mailService;
    private UserConverter userConverter;
    private UserService userService;
    private MessageSource messageSource;

    public UserController(UserConverter userConverter, UserService userService, MessageSource messageSource, MailService mailService){
        this.userConverter = userConverter;
        this.userService = userService;
        this.messageSource = messageSource;
        this.mailService = mailService;

    }

    @PostMapping
    public String createUser(@RequestBody UserRequestDTO dto, Locale locale){
        Mail mail = Mail.builder().mailTo(dto.getEmail()).mailSubject("BrasaJava Account Activation")
                .contentType("text/html").locale(locale).firstName(dto.getFirstName()).lastName(dto.getLastName()).build();
        mailService.sendActivationMail(mail);
        return userService.create(userConverter.userRequestDtoToUser(dto)).getId();
    }

    @GetMapping("/{id}")
    public String activeUser(@PathVariable String id){
        return userService.activate(id)?
            messageSource.getMessage("user.successfully.activated.msg", null,new Locale("ES")) :
            messageSource.getMessage("user.not.found.msg", null,new Locale("ES"));
    }

    @PostMapping("/{id}/resetpassword")
    public String resetPassword(@PathVariable String id){
        return userService.resetPassword(id)?
            messageSource.getMessage("user.password.reset.successfully.msg",null, new Locale("ES")) :
                messageSource.getMessage("user.not.found.msg", null,new Locale("ES"));
    }




}
