package com.example.userreviewservice.query;

import lombok.Data;

@Data
public class FindUserByEmailQuery {

    private String email;

    public FindUserByEmailQuery(String email) {
        this.email = email;
    }
}
