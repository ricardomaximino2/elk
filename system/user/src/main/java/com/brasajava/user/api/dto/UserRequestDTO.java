package com.brasajava.user.api.dto;

import lombok.Data;

@Data
public class UserRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
