package com.example.apiexportexceltoemail.persistences.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NamedStoredProcedureQuery(name = "DevoReport.SP_ReporteDevolucionesPorFechaEntity",
        procedureName = "SP_ReporteDevolucionesPorFecha", parameters = {
@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", type = LocalDateTime.class)})
public class DevoReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime ExecuteDate;
    private String errorMessage;
    private String order;
    private String item;
    private String type;
    private String sapCode;
    private String fileName;
    private String status;

}
