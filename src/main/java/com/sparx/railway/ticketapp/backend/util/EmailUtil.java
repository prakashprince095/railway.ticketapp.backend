package com.sparx.railway.ticketapp.backend.util;

import com.sparx.railway.ticketapp.backend.entities.PassangerDetailEntity;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import  java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Component
public class EmailUtil {
   @Value("${spring.mail.username}")
    String fromMailSender;
    Logger logger=LoggerFactory.getLogger(EmailUtil.class);
    @Autowired
    private DocumentUtil documentUtil;

    @Autowired
    private SpringTemplateEngine templateEngine;
    public boolean sendEmailOnTicketBooking(){
        return false;
    }
    @Autowired
    private JavaMailSender mailSender;
    String subject= "ticket booking confirmation Mail";
    String htmlContent="<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "  <head>\n" +
            "    <meta charset=\"UTF-8\" />\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
            "    <title>email message</title>\n" +
            "  </head>\n" +
            "  <body>\n" +
            "    <h3>Hii User </h3>\n" +
            "    <p>Your ticket is booked for trainNo 10001 . from new Delhi to patna \n" +
            "       and the passanger Details are xyz\n" +
            "    </p>\n" +
            "    \n" +
            "  </body>\n" +
            "</html>\n";
     String to ="firstTest@yopmail.com";
    public void sendHtmlEmail() throws MessagingException {
        System.out.println("the from address that is withdrawn from the properties file is "+fromMailSender);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true for multipart

        helper.setFrom(fromMailSender);
        helper.setTo("2030709@sliet.ac.in");
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // true for HTML content

        mailSender.send(message);
    }
    @Async
    public void sendEmailWithTemplate(String pnrNo, List<PassangerDetailEntity> passangerDetailList, String toEmail, String name , int trainNo, String fromStation, String toStation, String dateOfTravel) throws MessagingException {
        // Create a context object for Thymeleaf template variables
        try{
            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            documentUtil.generateBookinTicketPDF(pnrNo, passangerDetailList, pdfOutputStream);
            logger.info("Sending email wih template  method has been initialized");
            boolean status=false;
            Context context = new Context();
            context.setVariable("name", name);  // Dynamically populate 'name'
            context.setVariable("trainNo", trainNo);    // Dynamically populate 'url'
            context.setVariable("toStation",toStation);
            context.setVariable("fromStation", fromStation);
            context.setVariable("dateOfTravel", dateOfTravel);
            // Process the Thymeleaf template and generate the final HTML
            String htmlContent = templateEngine.process("TicketBooking", context);

            // Create a MimeMessage for sending HTML email
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            helper.setFrom(fromMailSender);
            helper.setTo("2030709@sliet.ac.in");
            helper.setSubject(subject);
            helper.setText(htmlContent, true);  // true means it's an HTML email
            logger.info("PDF size: " + pdfOutputStream.size() + " bytes");

            // Step 4: Add PDF attachment with explicit filename
            ByteArrayResource pdfAttachment = new ByteArrayResource(pdfOutputStream.toByteArray());

            // Debug: Log the content length
            logger.info("PDF Attachment size: " + pdfAttachment.contentLength());  // Check the size

            // Add the attachment with the explicit filename
            helper.addAttachment("TicketPdf.pdf", pdfAttachment);  // This explicitly sets the filename

            // Step 5: Send the email
            try {
                mailSender.send(mimeMessage);
                logger.info("Email sent successfully with template and PDF attachment.");
                status = true;
            } catch (Exception e) {
                logger.error("Error sending email: " + e.getMessage(), e);
            }

            // Return status
//            return status;

        } catch (Exception ex) {
            // Log and return false in case of error
            logger.error("Error in sending email with template: " + ex.getMessage(), ex);
//            return false;
        }



    }
       @Async
       public void sendCancellationMail(String pnrNo,String toEmail) throws MessagingException {
          try{
              logger.info("Sending mail method is being executed sendCancellationMail");
              Context context = new Context();
              context.setVariable("name", "USER");  // Dynamically populate 'name'
              context.setVariable("pnrNo", pnrNo);
              context.setVariable("date", LocalDate.now());
              context.setVariable("time", LocalTime.now());
              String htmlContent = templateEngine.process("CancelTicket", context);

              // Create a MimeMessage for sending HTML email
              MimeMessage mimeMessage = mailSender.createMimeMessage();
              MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

              helper.setFrom(fromMailSender);
              helper.setTo("2030709@sliet.ac.in");
              helper.setSubject("Ticket Cancellation Confirmation");
              helper.setText(htmlContent, true);
              mailSender.send(mimeMessage);
              logger.info(" ticket cancelllation mail send Succesfully ");
          }catch(Exception ex){
              logger.info("failed to send "+ex.getMessage());


          }


       }


       @Async
       public void sendWelcomeEmail(String name , String email){
        try{
            logger.info("Sending  welcome mail method has been been executing ");
            Context context = new Context();
            context.setVariable("name", name);  // Dynamically populate 'name'
            context.setVariable("email", email);
            String htmlContent = templateEngine.process("WelcomeMail", context);

            // Create a MimeMessage for sending HTML email
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            helper.setFrom(fromMailSender);
            helper.setTo(email);
            helper.setSubject("Welcome to the portal ");
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
            logger.info(" ticket cancelllation mail send Succesfully ");


        }catch(Exception ex){
            logger.error("failed to send welcome email "+ex.getMessage());
        }
       }

    }
