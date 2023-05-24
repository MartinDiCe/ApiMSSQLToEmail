package com.diceprojects.apiexport.dto;

import com.diceprojects.apiexport.persistences.models.ParamFecha;

public class RequestData {
    private ParamFecha date;

    public ParamFecha getDate() {
        return date;
    }

    public void setDate(ParamFecha date) {
        this.date = date;
    }
}
