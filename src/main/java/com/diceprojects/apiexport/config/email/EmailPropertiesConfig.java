package com.diceprojects.apiexportexceltoemail.config.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "email")
@Configuration
public class EmailPropertiesConfig {

    private boolean auth;
    private boolean starttlsEnable;
    private boolean starttlsRequired;
    private String sslTrust;
    private boolean sslEnable;

}
