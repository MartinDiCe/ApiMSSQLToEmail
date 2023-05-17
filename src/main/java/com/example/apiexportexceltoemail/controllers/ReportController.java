package com.example.apiexportexceltoemail.controllers;


import com.example.apiexportexceltoemail.dto.ReportDTO;
import com.example.apiexportexceltoemail.persistences.models.ParamFecha;
import com.example.apiexportexceltoemail.service.GetReportService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/export")
public class ReportController {

        private final GetReportService service;


    public  ReportController(GetReportService service) {

        this.service = service;

    }

    @GetMapping("/getReport")
    public ResponseEntity<List<ReportDTO>> getReport(@RequestBody ParamFecha date) {
        List<ReportDTO> fileContent = service.convertResultToFile2(date.date);
        return ResponseEntity.ok().body(fileContent);
    }

    @GetMapping("/downloadReport")
    public ResponseEntity<Resource> downloadReport(@RequestBody ParamFecha date) throws IOException {
        List<ReportDTO> fileContent = service.convertResultToFile2(date.date);
        Resource fileResource = service.createFile(fileContent);

        // Crear el encabezado de la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "reporte.csv");

        // Devolver el ResponseEntity con el archivo descargable
        return ResponseEntity.ok().headers(headers).body(fileResource);
    }
}
