package com.brasajava.user.service;

import com.brasajava.user.domain.entity.User;

import java.util.List;

public interface UserService {

    User create(User user);
    User update(User user);
    List<User> findAll();
    Boolean activate(String id);
    void deleteById(String id);
}
