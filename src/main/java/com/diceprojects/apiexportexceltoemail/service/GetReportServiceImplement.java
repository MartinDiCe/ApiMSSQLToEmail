package com.example.apiexportexceltoemail.service;


import com.example.apiexportexceltoemail.dto.ReportDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetReportServiceImplement implements GetReportService {

    private final EntityManager entityManager;

    @Autowired
    public GetReportServiceImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ReportDTO> convertResultToFile(LocalDate date) {

        StoredProcedureQuery sp = entityManager
                .createStoredProcedureQuery("[dbo].[SP_ReporteDevolucionesPorFecha]");

        sp.registerStoredProcedureParameter("fecha", LocalDate.class, ParameterMode.IN);

        sp.setParameter("fecha", date);

        sp.execute();

        List<Object[]> results=  sp.getResultList();

        // Mapear los resultados a una lista de objetos reportDTO
        List<ReportDTO> reportList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Object[] result : results) {
            String executeDateString = (String) result[0];
            LocalDateTime executeDate = LocalDateTime.parse(executeDateString, formatter);
            String errorMessage = (String) result[1];
            String order = (String) result[2];
            String item = (String) result[3];
            String type = (String) result[4];
            String sapCode = (String) result[5];
            String fileName = (String) result[6];
            String status = (String) result[7];

            ReportDTO report = new ReportDTO
                    (executeDate, errorMessage, order, item,type,sapCode,fileName,status);
            reportList.add(report);
        }

        return reportList;

    }

    @Override
    public Resource createCSVFile(List<ReportDTO> fileContent) throws IOException {
        // Crear un archivo temporal
        File file = File.createTempFile("reporte", ".csv");

        // Escribir el contenido en el archivo
        try (FileWriter writer = new FileWriter(file)) {
            // Escribir las cabeceras
            writer.write("executeDate, errorMessage, order, item, type, sapCode, fileName, status");
            writer.write("\n");

            // Escribir los valores
            for (ReportDTO report : fileContent) {
                writer.write(report.getExecuteDate().toString());
                writer.write(", ");
                writer.write("'" + report.getErrorMessage() + "'");
                writer.write(", ");
                writer.write("'" + report.getOrder() + "'");
                writer.write(", ");
                writer.write("'" + report.getItem() + "'");
                writer.write(", ");
                writer.write("'" + report.getType() + "'");
                writer.write(", ");
                writer.write("'" + report.getSapCode() + "'");
                writer.write(", ");
                writer.write("'" + report.getFileName() + "'");
                writer.write(", ");
                writer.write("'" + report.getStatus() + "'");
                writer.write("\n");
            }
        }

        // Crear un objeto FileSystemResource a partir del archivo
        Resource resource = new FileSystemResource(file);

        return resource;
    }

    @Override
    public Resource createExcelFile(List<ReportDTO> fileContent) throws IOException {
        // Crear un nuevo libro de Excel
        Workbook workbook = new XSSFWorkbook();

        // Crear una hoja en el libro
        Sheet sheet = workbook.createSheet("Reporte");

        // Crear la fila de cabeceras
        Row headerRow = sheet.createRow(0);
        String[] headers = { "executeDate", "errorMessage", "order", "item", "type", "sapCode", "fileName", "status" };
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Escribir los valores
        int rowNum = 1;
        for (ReportDTO report : fileContent) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(report.getExecuteDate().toString());
            row.createCell(1).setCellValue(report.getErrorMessage());
            row.createCell(2).setCellValue(report.getOrder());
            row.createCell(3).setCellValue(report.getItem());
            row.createCell(4).setCellValue(report.getType());
            row.createCell(5).setCellValue(report.getSapCode());
            row.createCell(6).setCellValue(report.getFileName());
            row.createCell(7).setCellValue(report.getStatus());
        }

        // Guardar el libro de Excel en un archivo temporal
        File file = File.createTempFile("reporte", ".xlsx");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        }

        // Crear un objeto FileSystemResource a partir del archivo
        Resource resource = new FileSystemResource(file);

        return resource;
    }

}
