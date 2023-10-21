package com.ms.user.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.user.models.User;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;



@Service
public class UserService {
    
   
    final UserRepository userRepository;
    final UserProducer userProducer;
    
    public UserService(UserRepository userRepository, UserProducer userProducer){
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public User save(User user){
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }



}
