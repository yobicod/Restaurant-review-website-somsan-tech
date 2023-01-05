package com.example.reportservice.core.data;

import com.example.reportservice.core.ReportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReportRepository extends MongoRepository<ReportEntity, String> {
    @Query(value = "{_id: ?0}")
    ReportEntity findByReportId(String reportId);

    @Query(value = "{judge: ?0}")
    List<ReportEntity> findByNonJudge(boolean judge);
}
