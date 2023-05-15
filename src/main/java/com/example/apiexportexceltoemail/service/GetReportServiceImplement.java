package com.example.apiexportexceltoemail.service;

import com.example.apiexportexceltoemail.persistences.models.entities.DevoReport;
import com.example.apiexportexceltoemail.persistences.repositories.ReportRepository;
import org.springframework.cglib.core.Local;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public class GetReportServiceImplement {

    private final ReportRepository report;

    public GetReportServiceImplement(ReportRepository report){
        this.report = report;
    }

    @Transactional(readOnly = true)
    public List<DevoReport> reportList(LocalDateTime date) {

        return report.getReport(date);

    }


}
