package com.diceprojects.apiexport.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ReportDTO {

    private LocalDateTime executeDate;
    private String errorMessage;
    private String order;
    private String item;
    private String type;
    private String sapCode;
    private String fileName;
    private String status;

    @Override
    public String toString() {
        return "ReportDTO{" +
                "executeDate=" + executeDate +
                ", errorMessage='" + errorMessage + '\'' +
                ", order='" + order + '\'' +
                ", item='" + item + '\'' +
                ", type='" + type + '\'' +
                ", sapCode='" + sapCode + '\'' +
                ", fileName='" + fileName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public ReportDTO(LocalDateTime executeDate, String errorMessage, String order, String item, String type, String sapCode, String fileName, String status) {
        this.executeDate = executeDate;
        this.errorMessage = errorMessage;
        this.order = order;
        this.item = item;
        this.type = type;
        this.sapCode = sapCode;
        this.fileName = fileName;
        this.status = status;
    }


}
