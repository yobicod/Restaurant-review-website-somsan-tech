package com.example.reportservice.query;

import lombok.Data;

@Data
public class FindReportsQuery {
    private boolean judge;

    public FindReportsQuery(boolean judge) {
        this.judge = judge;
    }
}
