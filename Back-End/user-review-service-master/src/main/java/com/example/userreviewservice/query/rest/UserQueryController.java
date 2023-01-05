package com.example.userreviewservice.query.rest;

import com.example.userreviewservice.core.UserEntity;
import com.example.userreviewservice.query.FindUserByEmailQuery;
import com.example.userreviewservice.query.FindUserByIdQuery;
import com.example.userreviewservice.query.FindUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping("/getAll")
    public List<UserRestModel> getUsers(){
        FindUsersQuery findUsersQuery = new FindUsersQuery();
        List<UserRestModel> users = queryGateway
                .query(findUsersQuery, ResponseTypes.multipleInstancesOf(UserRestModel.class)).join();
        return users;
    }

    @GetMapping("findById")
    public ResponseEntity<UserEntity> getUserById(@RequestParam String id){
        UserEntity user = queryGateway.query(new FindUserByIdQuery(id), UserEntity.class).join();
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findByEmail")
    public List<UserRestModel> getUserByEmail(@RequestParam String email){
        List<UserRestModel> users = queryGateway
                .query(new FindUserByEmailQuery(email), ResponseTypes.multipleInstancesOf(UserRestModel.class)).join();
        return users;
    }
}
