package com.diceprojects.apiexportexceltoemail.config.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EmailProperties {

    private boolean auth;
    private boolean starttlsEnable;
    private boolean starttlsRequired;
    private String sslTrust;
    private boolean sslEnable;


}
