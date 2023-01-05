package com.example.reportservice.query;

import com.example.reportservice.core.ReportEntity;
import com.example.reportservice.core.data.ReportRepository;
import com.example.reportservice.core.event.ReportCreatedEvent;
import com.example.reportservice.core.event.ReportUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ReportEventsHandler {
    private final ReportRepository reportRepository;

    public ReportEventsHandler(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }

    @EventHandler
    public void on(ReportCreatedEvent event){
        ReportEntity reportEntity = new ReportEntity();
        BeanUtils.copyProperties(event, reportEntity);
        reportRepository.save(reportEntity);
    }

    @EventHandler
    public void on(ReportUpdatedEvent event){
        ReportEntity reportEntity = new ReportEntity();
        BeanUtils.copyProperties(event, reportEntity);
        reportRepository.save(reportEntity);
    }
}
