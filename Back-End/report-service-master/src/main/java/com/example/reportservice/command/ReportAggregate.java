package com.example.reportservice.command;

import com.example.reportservice.core.event.ReportCreatedEvent;
import com.example.reportservice.core.event.ReportUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ReportAggregate {

    @AggregateIdentifier
    private String _id;
    private String userReportId;
    private String userReportName;
    private String typeReport;
    private String itemIdReport;
    private String reportHeader;
    private String reportDescription;
    private String banDescription;
    private boolean judge;

    public ReportAggregate() {
    }

    @CommandHandler
    public ReportAggregate(CreateReportCommand createReportCommand){
        if (createReportCommand.getReportDescription() == null || createReportCommand.getReportDescription().isBlank()){
            throw new IllegalArgumentException("Can't Des Empty");
        }
        ReportCreatedEvent reportCreatedEvent = new ReportCreatedEvent();
        BeanUtils.copyProperties(createReportCommand, reportCreatedEvent);
        AggregateLifecycle.apply(reportCreatedEvent);
    }

    @CommandHandler
    public void handle(UpdateReportCommand updateReportCommand){
        if (updateReportCommand.getReportDescription() == null || updateReportCommand.getReportDescription().isBlank()){
            throw new IllegalArgumentException("Can't Des Empty");
        }
        ReportUpdatedEvent reportUpdatedEvent = new ReportUpdatedEvent();
        BeanUtils.copyProperties(updateReportCommand, reportUpdatedEvent);
        AggregateLifecycle.apply(reportUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(ReportCreatedEvent reportCreatedEvent){
        this._id = reportCreatedEvent.get_id();
        this.userReportId = reportCreatedEvent.getUserReportId();
        this.userReportName = reportCreatedEvent.getUserReportName();
        this.typeReport = reportCreatedEvent.getTypeReport();
        this.itemIdReport = reportCreatedEvent.getItemIdReport();
        this.reportHeader = reportCreatedEvent.getReportHeader();
        this.reportDescription = reportCreatedEvent.getReportDescription();
        this.banDescription = reportCreatedEvent.getBanDescription();
        this.judge = reportCreatedEvent.isJudge();
    }

    @EventSourcingHandler
    public void on(ReportUpdatedEvent reportUpdatedEvent){
        this._id = reportUpdatedEvent.get_id();
        this.userReportId = reportUpdatedEvent.getUserReportId();
        this.userReportName = reportUpdatedEvent.getUserReportName();
        this.typeReport = reportUpdatedEvent.getTypeReport();
        this.itemIdReport = reportUpdatedEvent.getItemIdReport();
        this.reportHeader = reportUpdatedEvent.getReportHeader();
        this.reportDescription = reportUpdatedEvent.getReportDescription();
        this.banDescription = reportUpdatedEvent.getBanDescription();
        this.judge = reportUpdatedEvent.isJudge();
    }
}
