package edu.wctc.nap.napmidtermapp.util;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
 
@Service("mailService")
public class simpleRegistrationService
{
    @Autowired
    private JavaMailSender  mailSender;
     
  
    /**
     * This method will send compose and send the message 
     * */
    public void sendMail(String to, String subject, String body) throws MessagingException 
    {
         MimeMessage message = mailSender.createMimeMessage();

            message.setSubject(subject);
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("Support@AnimeShop.com");
            helper.setTo(to);
            helper.setText(body, true);
            mailSender.send(message);
    }
}