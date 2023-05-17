package com.example.apiexportexceltoemail.controllers;


import com.example.apiexportexceltoemail.dto.ReportDTO;
import com.example.apiexportexceltoemail.persistences.models.ParamFecha;
import com.example.apiexportexceltoemail.service.GetReportService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
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
        List<ReportDTO> fileContent = service.convertResultToFile(date.date);
        return ResponseEntity.ok().body(fileContent);
    }

    @GetMapping("/downloadCSV")
    public ResponseEntity<Resource> downloadCSV(@RequestBody ParamFecha date) throws IOException {
        List<ReportDTO> fileContent = service.convertResultToFile(date.date);
        Resource fileResource = service.createCSVFile(fileContent);

        // Leer el contenido del archivo en un arreglo de bytes
        byte[] fileBytes = IOUtils.toByteArray(fileResource.getInputStream());

        // Crear un objeto ByteArrayResource con el contenido del archivo
        ByteArrayResource resource = new ByteArrayResource(fileBytes);

        // Crear el encabezado de la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "reporte.csv");

        // Devolver el ResponseEntity con el archivo descargable
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileBytes.length)
                .body(resource);
    }

    @GetMapping("/downloadExcel")
    public ResponseEntity<Resource> downloadExcel(@RequestBody ParamFecha date) throws IOException {
        List<ReportDTO> fileContent = service.convertResultToFile(date.date);
        Resource fileResource = service.createExcelFile(fileContent);

        // Leer el contenido del archivo en un arreglo de bytes
        byte[] fileBytes = IOUtils.toByteArray(fileResource.getInputStream());

        // Crear un objeto ByteArrayResource con el contenido del archivo
        ByteArrayResource resource = new ByteArrayResource(fileBytes);

        // Crear el encabezado de la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "reporte.xlsx");

        // Devolver el ResponseEntity con el archivo descargable
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileBytes.length)
                .body(resource);
    }

}
