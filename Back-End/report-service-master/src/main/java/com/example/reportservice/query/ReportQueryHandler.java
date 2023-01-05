package com.example.reportservice.query;

import com.example.reportservice.core.ReportEntity;
import com.example.reportservice.core.data.ReportRepository;
import com.example.reportservice.query.rest.ReportRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportQueryHandler {
    private final ReportRepository reportRepository;
    public  ReportQueryHandler(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }

    @QueryHandler
    List<ReportRestModel> findReports(FindReportsQuery query){
        List<ReportRestModel> reportsRest = new ArrayList<>();
        List<ReportEntity> storedReports = reportRepository.findByNonJudge(query.isJudge());
        for (ReportEntity reportEntity : storedReports){
            ReportRestModel reportRestModel = new ReportRestModel();
            BeanUtils.copyProperties(reportEntity, reportRestModel);
            reportsRest.add(reportRestModel);
        }
        return reportsRest;
    }

    @QueryHandler
    ReportEntity handle(FindReportByIdQuery query){
        ReportEntity report = reportRepository.findByReportId(query.get_id());
        return report;
    }
}
