package com.brasajava.user.domain.entity;

import lombok.Data;

@Data
public class User implements Entity {
    private String id;
    private String email;
    private String username;
    private String password;
    private String status;

}


