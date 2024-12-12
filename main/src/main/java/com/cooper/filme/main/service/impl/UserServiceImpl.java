package com.cooper.filme.main.service.impl;

import com.cooper.filme.main.models.User;
import com.cooper.filme.main.repository.UserRepository;
import com.cooper.filme.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
