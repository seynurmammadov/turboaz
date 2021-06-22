package az.code.myauto.utils;

import az.code.myauto.models.User;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageUtil {
    final
    MailSenderUtil mailSenderUtil;

    final
    Environment environment;

    public MessageUtil(MailSenderUtil mailSenderUtil, Environment environment) {
        this.mailSenderUtil = mailSenderUtil;
        this.environment = environment;
    }

    public void sendNotification(String type, User user, Double amount) {
        String subject = type + environment.getProperty("mail.subject");
        String content = user.getFullname() + environment.getProperty("mail.amount.info.message")
                + amount + environment.getProperty("mail.balance.info.message") + user.getBalance();
        mailSenderUtil.sendEmail(user.getEmail(), subject, content);
    }

    public void sendNotification(String to, LocalDateTime date) {
        mailSenderUtil.sendEmail(to, environment.getProperty("mail.notification.subject"),
                environment.getProperty("mail.notification.message") + date.toString());
    }


}