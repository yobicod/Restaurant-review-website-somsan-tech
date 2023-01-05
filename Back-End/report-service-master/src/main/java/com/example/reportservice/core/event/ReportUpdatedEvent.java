package com.example.reportservice.core.event;

import lombok.Data;

@Data
public class ReportUpdatedEvent {
    private String _id;
    private String userReportId;
    private String userReportName;
    private String typeReport;
    private String itemIdReport;
    private String reportHeader;
    private String reportDescription;
    private String banDescription;
    private boolean judge;
}
