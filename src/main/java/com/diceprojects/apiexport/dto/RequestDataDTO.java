package com.diceprojects.apiexport.dto;

import com.diceprojects.apiexport.persistences.models.ParamFecha;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDataDTO {
    private ParamFecha date;
}
