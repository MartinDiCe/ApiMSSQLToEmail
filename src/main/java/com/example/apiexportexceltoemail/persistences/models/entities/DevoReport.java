package com.example.apiexportexceltoemail.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DevoReport {

    private LocalDateTime ExecuteDate;
    private String errorMessage;
    private String order;
    private String item;
    private String type;
    private String sapCode;
    private String fileName;
    private String status;

}
