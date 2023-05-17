package com.example.apiexportexceltoemail.persistences.models.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DevoReport {

    private LocalDate ExecuteDate;
    private String errorMessage;
    private String order;
    private String item;
    private String type;
    private String sapCode;
    private String fileName;
    private String status;


}