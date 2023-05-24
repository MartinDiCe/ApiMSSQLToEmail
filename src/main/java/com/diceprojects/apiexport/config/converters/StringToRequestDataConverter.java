package com.diceprojects.apiexport.config.converters;

import com.diceprojects.apiexport.dto.RequestDataDTO;
import com.diceprojects.apiexport.persistences.models.ParamFecha;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StringToRequestDataConverter implements Converter<String, RequestDataDTO> {
    @Override
    public RequestDataDTO convert(String source) {
        // Realiza la conversión personalizada de String a RequestDataDTO
        RequestDataDTO requestDataDTO = new RequestDataDTO();
        LocalDate localDate = LocalDate.parse(source); // Convertir String a LocalDate

        ParamFecha paramFecha = new ParamFecha();
        paramFecha.setDate(localDate); // Establecer la fecha en ParamFecha

        requestDataDTO.setDate(paramFecha); // Establecer ParamFecha en RequestDataDTO

        return requestDataDTO;
    }
}