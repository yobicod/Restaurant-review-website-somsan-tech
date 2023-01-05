package com.example.userreviewservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateUserCommand {
    @TargetAggregateIdentifier
    private final String _id;
    private final String username;
    private final String name;
    private final String email;
    private final String birthday;
    private final String phone;
    private final String imageId;
    private final String role;
}
