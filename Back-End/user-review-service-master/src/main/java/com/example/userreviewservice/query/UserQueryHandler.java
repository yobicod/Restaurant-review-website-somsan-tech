package com.example.userreviewservice.query;

import com.example.userreviewservice.core.UserEntity;
import com.example.userreviewservice.core.data.UserRepository;
import com.example.userreviewservice.query.rest.UserRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserQueryHandler {
    private final UserRepository userRepository;

    public UserQueryHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @QueryHandler
    List<UserRestModel> findUsers(FindUsersQuery query){
        List<UserRestModel> usersRest = new ArrayList<>();
        List<UserEntity> storedUsers = userRepository.findAll();
        for (UserEntity userEntity : storedUsers){
            UserRestModel userRestModel = new UserRestModel();
            BeanUtils.copyProperties(userEntity, userRestModel);
            usersRest.add(userRestModel);
        }
        return usersRest;
    }

    @QueryHandler
    UserEntity handle (FindUserByIdQuery query){
        UserEntity user = userRepository.findByUserId(query.get_id());
        return user;
    }

    @QueryHandler
    List<UserRestModel> handle(FindUserByEmailQuery query){
        List<UserRestModel> userRest = new ArrayList<>();
        List<UserEntity> storedUsers = userRepository
                .findByUserEmail(query.getEmail());
        for (UserEntity userEntity : storedUsers){
            UserRestModel userRestModel = new UserRestModel();
            BeanUtils.copyProperties(userEntity, userRestModel);
            userRest.add(userRestModel);
        }
        return userRest;
    }
}
