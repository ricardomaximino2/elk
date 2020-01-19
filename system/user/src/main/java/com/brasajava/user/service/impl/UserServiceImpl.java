package com.brasajava.user.service.impl;

import com.brasajava.user.domain.entity.User;
import com.brasajava.user.domain.repository.UserRepository;
import com.brasajava.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository reposiory;

    public UserServiceImpl(UserRepository reposiory) {
        this.reposiory = reposiory;
    }

    @Override
    public User create(User user) {
        return reposiory.save(user);
    }

    @Override
    public User update(User user) {
        return reposiory.save(user);
    }

    @Override
    public List<User> findAll() {
        return reposiory.findAll();
    }

    @Override
    public Boolean activate(String id) {
        return reposiory.findById(id).map(user -> {
            user.setStatus("active");
            return true;
        }).orElse(false);
    }

    @Override
    public void deleteById(String id) {
        reposiory.deleteById(id);
    }
}
