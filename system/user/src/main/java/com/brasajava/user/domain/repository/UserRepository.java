package com.brasajava.user.domain.repository;

import com.brasajava.user.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
