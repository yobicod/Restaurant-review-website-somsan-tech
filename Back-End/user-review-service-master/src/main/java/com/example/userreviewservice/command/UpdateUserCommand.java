package com.example.userreviewservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String _id;
    private String username;
    private String name;
    private String email;
    private String birthday;
    private String phone;
    private String imageId;
    private String role;
}
