package com.example.apiexportexceltoemail.service;


import com.example.apiexportexceltoemail.dto.ReportDTO;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface GetReportService {

    List<ReportDTO> convertResultToFile2(LocalDate date);
    Resource createFile(List<ReportDTO> fileContent) throws IOException;

}
