package com.example.userreviewservice.command.rest;

import com.example.userreviewservice.command.CreateUserCommand;
import com.example.userreviewservice.command.UpdateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public UserCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createUser(@RequestBody CreateUserModel model){
        CreateUserCommand command = CreateUserCommand.builder()
                ._id(UUID.randomUUID().toString().toString())
                .username(model.getUsername())
                .name(model.getName())
                .email(model.getEmail())
                .birthday(model.getBirthday())
                .phone(model.getPhone())
                .imageId(model.getImageId())
                .role(model.getRole()).build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @PutMapping(value = "/update")
    public String updateUser(@RequestBody UpdateUserModel model){
        UpdateUserCommand command = UpdateUserCommand.builder()
                ._id(model.get_id())
                .username(model.getUsername())
                .name(model.getName())
                .email(model.getEmail())
                .birthday(model.getBirthday())
                .phone(model.getPhone())
                .imageId(model.getImageId())
                .role(model.getRole())
                .build();

        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            result = "Update Complete ID: " + idUpdate;
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }

        return result;
    }
}
