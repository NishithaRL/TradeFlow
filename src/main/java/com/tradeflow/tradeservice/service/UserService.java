package com.tradeflow.tradeservice.service;

import com.tradeflow.tradeservice.entity.User;
import com.tradeflow.tradeservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}