package com.example.userreviewservice.query;

import com.example.userreviewservice.core.UserEntity;
import com.example.userreviewservice.core.data.UserRepository;
import com.example.userreviewservice.core.event.UserCreatedEvent;
import com.example.userreviewservice.core.event.UserUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {
    private final UserRepository userRepository;

    public UserEventsHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserCreatedEvent event){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(event, userEntity);
        userRepository.save(userEntity);
    }

    @EventHandler
    public void on(UserUpdatedEvent event){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(event, userEntity);
        userRepository.save(userEntity);
    }
}
