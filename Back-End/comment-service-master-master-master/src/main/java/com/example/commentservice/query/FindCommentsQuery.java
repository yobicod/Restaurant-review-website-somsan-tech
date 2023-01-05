package com.example.commentservice.query;

import lombok.Data;

@Data
public class FindCommentsQuery {
    private boolean ban;

    public FindCommentsQuery(boolean ban) {
        this.ban = ban;
    }
}
