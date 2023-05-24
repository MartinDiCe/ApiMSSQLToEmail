package com.diceprojects.apiexportexceltoemail;

import com.diceprojects.apiexportexceltoemail.config.email.EmailConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.diceprojects.apiexportexceltoemail.persistences.*")
@EntityScan(basePackages = "com.diceprojects.apiexportexceltoemail.models.*")
@EnableConfigurationProperties(EmailConfig.class)
public class ApiExportExcelToEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiExportExcelToEmailApplication.class, args);
    }

}
