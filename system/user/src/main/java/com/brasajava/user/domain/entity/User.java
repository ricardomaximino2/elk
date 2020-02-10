package com.brasajava.user.domain.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Locale;

@Data
public class User implements Entity {
    private String id;
    private String email;
    private String username;
    private String password;
    private String status;
    private LocalDate creationDate;
    private Locale locale;

}


