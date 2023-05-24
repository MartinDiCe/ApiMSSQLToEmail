package com.diceprojects.apiexport.service;


import com.diceprojects.apiexport.dto.ReportDTO;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface GetReportService {

    List<ReportDTO> convertResultToFile(LocalDate date);
    Resource createCSVFile(List<ReportDTO> fileContent) throws IOException;
    Resource createExcelFile(List<ReportDTO> fileContent) throws IOException;
    Resource convertResultToFile(List<ReportDTO> fileContent) throws IOException;
    byte[] convertResourceToByteArray(Resource resource) throws IOException;

}
