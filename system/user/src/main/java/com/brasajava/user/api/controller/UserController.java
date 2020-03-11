package com.brasajava.user.api.controller;

import com.brasajava.mail.service.MailService;
import com.brasajava.user.api.converter.UserConverter;
import com.brasajava.user.api.dto.UserRequestDTO;
import com.brasajava.user.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

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
        String to = "ricardomaximino@gmail.com";
        String[] arrayTo = {"ricardomaximino@gmail.com","ricardomaximino@gmail.com"};
        String subject = "Subject";
        Map<String, Object> params = new HashMap<>();
        String template = "mail";
        String attachmentName = "attachment.txt";
        String text = "Some Text!!!";
        File attachment;
        ClassLoader classLoader = getClass().getClassLoader();
        attachment = new File(Objects.requireNonNull(classLoader.getResource("classpath:" + attachmentName)).getFile());
        mailService.sendSimpleMessage(to,subject, text);
        System.out.println(attachment);
        mailService.sendEmailWithAttachment(arrayTo, subject, params, template, attachment, attachmentName);
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
        String dfdfd = "dfdfd";
        return userService.resetPassword(id)?
            messageSource.getMessage("user.password.reset.successfully.msg",null, new Locale("ES")) :
                messageSource.getMessage("user.not.found.msg", null,new Locale("ES"));
    }




}
