package com.example.reportservice.query;

import lombok.Data;

@Data
public class FindReportByIdQuery {
    private String _id;

    public FindReportByIdQuery(String _id) {
        this._id = _id;
    }
}
