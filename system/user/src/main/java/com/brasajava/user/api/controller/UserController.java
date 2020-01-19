package com.brasajava.user.api.controller;

import com.brasajava.user.api.converter.UserConverter;
import com.brasajava.user.api.dto.UserRequestDTO;
import com.brasajava.user.service.UserService;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserConverter userConverter;
    private UserService userService;
    private MessageSource messageSource;

    public UserController(UserConverter userConverter, UserService userService, MessageSource messageSource){
        this.userConverter = userConverter;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @PostMapping
    public String createUser(UserRequestDTO dto){
        return userService.create(userConverter.userRequestDtoToUser(dto)).getId();
    }

    @GetMapping("/{id}")
    public String activeUser(@PathVariable String id){
        return userService.activate(id)?
            messageSource.getMessage("user.successfully.activated.msg", null,new Locale("ES")) :
            messageSource.getMessage("user.not.found.msg", null,new Locale("ES"));
    }


}
