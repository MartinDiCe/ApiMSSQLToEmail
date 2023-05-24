package com.diceprojects.apiexportexceltoemail.service;

import com.diceprojects.apiexportexceltoemail.config.email.EmailPropertiesConfig;
import jakarta.activation.DataSource;
import jakarta.activation.MimetypesFileTypeMap;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImplement implements EmailService {

    private final EmailPropertiesConfig emailProperties;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImplement(@Qualifier("emailPropertiesConfigImpl") EmailPropertiesConfig emailProperties, JavaMailSender javaMailSender) {
        this.emailProperties = emailProperties;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String recipientEmail, String subject, String body, Resource attachment) throws Exception {
        javaMailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setTo(recipientEmail);
                messageHelper.setSubject(subject);
                messageHelper.setText(body);

                // Convertir InputStream a DataSource utilizando ByteArrayDataSource
                DataSource dataSource = new ByteArrayDataSource(attachment.getInputStream(), getContentType(attachment));
                messageHelper.addAttachment(attachment.getFilename(), dataSource);
            }
        });
    }

    // Obtener el tipo de contenido MIME del archivo adjunto
    private String getContentType(Resource attachment) {
        try {
            return new MimetypesFileTypeMap().getContentType(attachment.getFile());
        } catch (IOException e) {
            // Manejar la excepción apropiadamente
            e.printStackTrace();
            return null;
        }
    }
}

