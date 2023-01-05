package com.example.reportservice.core;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Document("report")
@Data
public class ReportEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -1198298728216637846L;
    @Id
    @Column(unique = true)
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
