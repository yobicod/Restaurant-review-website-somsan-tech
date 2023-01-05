package com.example.reportservice.query.rest;

import com.example.reportservice.core.ReportEntity;
import com.example.reportservice.core.data.ReportRepository;
import com.example.reportservice.query.FindReportByIdQuery;
import com.example.reportservice.query.FindReportsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/report")
public class ReportQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping("/getAll")
    public List<ReportRestModel> getReports(){
        FindReportsQuery findReportsQuery = new FindReportsQuery(false);
        List<ReportRestModel> reports = queryGateway
                .query(findReportsQuery, ResponseTypes.multipleInstancesOf(ReportRestModel.class)).join();
        return reports;
    }

    @GetMapping("findById")
    public ResponseEntity<ReportEntity> getReportById(@RequestParam String id){
        ReportEntity report = queryGateway.query(new FindReportByIdQuery(id), ReportEntity.class).join();
        if (report == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
