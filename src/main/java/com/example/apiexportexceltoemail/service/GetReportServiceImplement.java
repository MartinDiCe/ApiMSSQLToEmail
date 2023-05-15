package com.example.apiexportexceltoemail.service;

import com.example.apiexportexceltoemail.persistences.models.entities.DevoReport;
import com.example.apiexportexceltoemail.persistences.repositories.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GetReportServiceImplement implements GetReportService {

    private final ReportRepository repository;

    public GetReportServiceImplement(ReportRepository repository){

        this.repository = repository;

    }

    @Transactional(readOnly = true)
    public List<DevoReport> reportList(LocalDateTime date){

        return this.repository.getReport(date);

    }

}
