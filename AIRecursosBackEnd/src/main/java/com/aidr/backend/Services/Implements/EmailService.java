package com.aidr.backend.Services.Implements;

//import org.springframework.beans.factory.annotation.Value;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public  void  sendEmailTemplate(String to, String token, String nombreEmpresa) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper heloer = new MimeMessageHelper(message, true);
            Context context = new Context();
            context.setVariable("token", token);
            context.setVariable("nombreEmpresa", nombreEmpresa);
            String htmlText = templateEngine.process("email-template", context);
            heloer.setFrom("carlos.gonzalez@kohmi.net");
            heloer.setTo(to);
            heloer.setSubject("RECUPERACIÓN DE CONTRASEÑA");
            heloer.setText(htmlText, true);
            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
