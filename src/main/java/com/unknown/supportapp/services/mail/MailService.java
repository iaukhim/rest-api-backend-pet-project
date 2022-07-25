package com.unknown.supportapp.services.mail;

import com.sun.mail.smtp.SMTPSendFailedException;
import com.unknown.supportapp.config.MailProps;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@DependsOn("mailProps")
@Service
public class MailService {
    private MailProps mailProps;
    private Properties properties;

    @Autowired
    public MailService(MailProps mailProps) {
        this.mailProps = mailProps;
        properties = new Properties();
        properties.putAll(mailProps.getProp());

    }

    public String sendConfirmationEmail(String receiverEmail) {
        String randomedCode = null;
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password"));
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(properties.getProperty("from")));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail));

            message.setSubject("Confirmation Email");
            randomedCode = RandomStringUtils.randomAlphanumeric(6);
            message.setText(randomedCode);
            Transport.send(message);
        }
        catch (SMTPSendFailedException e) {
            System.out.println("fail in mail");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return randomedCode;
    }
}

