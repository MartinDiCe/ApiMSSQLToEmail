package com.diceprojects.apiexportexceltoemail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfig {

    private String host;
    private int port;
    private String username;
    private String password;
    private EmailProperties properties;

    // getters y setters

    @Bean
    public EmailProperties emailProperties() {
        return properties;
    }

    // otros beans y configuraciones relacionadas con el correo
}