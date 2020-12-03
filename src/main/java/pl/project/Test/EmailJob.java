package pl.project.Test;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import pl.project.payload.EmailSenderService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class EmailJob extends QuartzJobBean {
    ;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    EmailSenderService emailSenderService;

    private static final Logger logger = LoggerFactory.getLogger(EmailJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {


        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String subject = jobDataMap.getString("subject");
        String body = jobDataMap.getString("body");
        String recipientEmail = jobDataMap.getString("email");

        String[] emails = recipientEmail.split(" ");

        for (int i = 0; i < emails.length; i++) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(emails[i]);
            mailMessage.setSubject("You have your " + subject + " exam tomorrow!");
            mailMessage.setFrom("javaquiz123@gmail.com");
            mailMessage.setText(body);
            try {
                emailSenderService.sendEmail(mailMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }

//    private void sendMail(String fromEmail, String toEmail, String subject, String body) {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//
//            MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
//            messageHelper.setSubject(subject);
//            messageHelper.setText(body, true);
//            messageHelper.setFrom(fromEmail);
//            messageHelper.setTo(toEmail);
//
//            mailSender.send(message);
//        } catch (MessagingException ex) {
//            ex.printStackTrace();
//        }
//    }
    }
}
