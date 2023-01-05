package com.example.commentservice.query;

import lombok.Data;

@Data
public class FindCommentByIdQuery {
    private String _id;

    public FindCommentByIdQuery(String _id) {
        this._id = _id;
    }
}
