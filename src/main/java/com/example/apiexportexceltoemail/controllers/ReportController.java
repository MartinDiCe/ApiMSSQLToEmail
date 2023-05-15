package com.example.apiexportexceltoemail.controllers;


import com.example.apiexportexceltoemail.persistences.models.entities.DevoReport;
import com.example.apiexportexceltoemail.service.GetReportService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/export")
public class ReportController {

        private final GetReportService srvc;


    public  ReportController(GetReportService service) {

        this.srvc = service;

    }

    @PostMapping
    public List<DevoReport> report(@RequestBody LocalDateTime date){

        return srvc.reportList(date);

    }
}
