package com.diceprojects.apiexportexceltoemail.config.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Getter
@Setter
public class EmailConfig {

    private String host;
    private int port;
    private String username;
    private String password;
    private EmailPropertiesConfig properties;

    @Bean
    public JavaMailSender javaMailSender() {
        // Configura las propiedades del JavaMailSender según tus necesidades
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("youemail@gmail.com");
        mailSender.setPassword("youpassword");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true"); // Habilitar STARTTLS
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Establecer el host de confianza


            return mailSender;
    }
}
