package com.example.userreviewservice.command;

import com.example.userreviewservice.core.event.UserCreatedEvent;
import com.example.userreviewservice.core.event.UserUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String _id;
    private String username;
    private String name;
    private String email;
    private String birthday;
    private String phone;
    private String imageId;
    private String role;

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand){
        if(createUserCommand.getUsername() == null || createUserCommand.getUsername().isBlank()){
            throw new IllegalArgumentException("Username Can't be Empty");
        }
        if(createUserCommand.getName() == null || createUserCommand.getName().isBlank()){
            throw new IllegalArgumentException("Name Can't be Empty");
        }
        if(createUserCommand.getEmail() == null || createUserCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Email Can't be Empty");
        }
        if(createUserCommand.getPhone() == null || createUserCommand.getPhone().isBlank()){
            throw new IllegalArgumentException("Phone Can't be Empty");
        }
        if(createUserCommand.getBirthday() == null || createUserCommand.getBirthday().isBlank()){
            throw new IllegalArgumentException("Birthday Can't be Empty");
        }
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        BeanUtils.copyProperties(createUserCommand, userCreatedEvent);
        AggregateLifecycle.apply(userCreatedEvent);
    }

    @CommandHandler
    public void handle(UpdateUserCommand updateUserCommand){
        if(updateUserCommand.getUsername() == null || updateUserCommand.getUsername().isBlank()){
            throw new IllegalArgumentException("Username Can't be Empty");
        }
        if(updateUserCommand.getName() == null || updateUserCommand.getName().isBlank()){
            throw new IllegalArgumentException("Name Can't be Empty");
        }
        if(updateUserCommand.getEmail() == null || updateUserCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Email Can't be Empty");
        }
        if(updateUserCommand.getPhone() == null || updateUserCommand.getPhone().isBlank()){
            throw new IllegalArgumentException("Phone Can't be Empty");
        }
        if(updateUserCommand.getBirthday() == null || updateUserCommand.getBirthday().isBlank()){
            throw new IllegalArgumentException("Birthday Can't be Empty");
        }
        UserUpdatedEvent userUpdatedEvent = new UserUpdatedEvent();
        BeanUtils.copyProperties(updateUserCommand, userUpdatedEvent);
        AggregateLifecycle.apply(userUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent){
        this._id = userCreatedEvent.get_id();
        this.username = userCreatedEvent.getUsername();
        this.name = userCreatedEvent.getName();
        this.email = userCreatedEvent.getEmail();
        this.birthday = userCreatedEvent.getBirthday();
        this.phone = userCreatedEvent.getPhone();
        this.imageId = userCreatedEvent.getImageId();
        this.role = userCreatedEvent.getRole();

    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent userUpdatedEvent){
        this._id = userUpdatedEvent.get_id();
        this.username = userUpdatedEvent.getUsername();
        this.name = userUpdatedEvent.getName();
        this.email = userUpdatedEvent.getEmail();
        this.birthday = userUpdatedEvent.getBirthday();
        this.phone = userUpdatedEvent.getPhone();
        this.imageId = userUpdatedEvent.getImageId();
        this.role = userUpdatedEvent.getRole();
    }
}
