package com.diceprojects.apiexport.controller;


import com.diceprojects.apiexport.config.email.SendReportRequest;
import com.diceprojects.apiexport.dto.ReportDTO;
import com.diceprojects.apiexport.dto.RequestDataDTO;
import com.diceprojects.apiexport.persistences.models.ParamFecha;
import com.diceprojects.apiexport.service.EmailService;
import com.diceprojects.apiexport.service.GetReportService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/export")
public class ReportController {

        private final GetReportService service;
        private final EmailService emailService;


    public  ReportController(GetReportService service, EmailService emailService) {

        this.service = service;
        this.emailService = emailService;

    }

    @RequestMapping(value = "/getReport", method = RequestMethod.GET)
  /*  @GetMapping("/getReport")*/
    public ResponseEntity<List<ReportDTO>> getReport(@RequestParam("date") RequestDataDTO requestData) {
        ParamFecha date = requestData.getDate();
        List<ReportDTO> fileContent = service.convertResultToFile(date.getDate());
        return ResponseEntity.ok().body(fileContent);
    }

    @RequestMapping(value = "/downloadCSV", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadCSV(@RequestParam("date") RequestDataDTO requestData) throws IOException {
        ParamFecha date = requestData.getDate();
        List<ReportDTO> fileContent = service.convertResultToFile(date.getDate());
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

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadExcel(@RequestParam("date") RequestDataDTO requestData) throws IOException {
        ParamFecha date = requestData.getDate();
        List<ReportDTO> fileContent = service.convertResultToFile(date.getDate());
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

    @RequestMapping(value = "/sendReportByEmail", method = RequestMethod.POST)
    public ResponseEntity<String> sendReportByEmail(@RequestBody(required = true) SendReportRequest request) {

        try {
            // Verificar si falta algún parámetro obligatorio
            if (request.getRecipientEmail() == null) {
                return ResponseEntity.badRequest().body("Error: 'recipientEmail' parameter is missing");
            }
            if (request.getSubject() == null) {
                return ResponseEntity.badRequest().body("Error: 'subject' parameter is missing");
            }
            if (request.getBody() == null) {
                return ResponseEntity.badRequest().body("Error: 'body' parameter is missing");
            }
            if (request.getDate() == null || request.getDate().getDate() == null) {
                return ResponseEntity.badRequest().body("Error: 'date' parameter is missing");
            }

            // Obtener el contenido del archivo Excel
            List<ReportDTO> fileContent = service.convertResultToFile(request.getDate().getDate());
            Resource excelFile = service.createExcelFile(fileContent);

            // Enviar el archivo Excel por correo electrónico
            try {
                emailService.sendEmail(request.getRecipientEmail(), request.getSubject(), request.getBody(), excelFile);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending report");
            }

            return ResponseEntity.ok().body("Report sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending report");
        }
    }

    //Validador de resultados
    private ResponseEntity<Map<String,String>> validate(BindingResult result) {

        if (result.hasErrors()){

            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

            return ResponseEntity.badRequest().body(errors);

        }

        return null;

    }
}