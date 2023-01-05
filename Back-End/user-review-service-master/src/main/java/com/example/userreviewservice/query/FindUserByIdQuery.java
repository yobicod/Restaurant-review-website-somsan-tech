package com.example.userreviewservice.query;

import lombok.Data;

@Data
public class FindUserByIdQuery {
    private String _id;
    public FindUserByIdQuery(String _id){
        this._id = _id;
    }
}
