package az.code.myauto.utils;

import az.code.myauto.models.User;
import az.code.myauto.models.UserConfirmationToken;
import az.code.myauto.models.dtos.UserRegistrationDTO;
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

    public void transactionsNotification(String type, User user, Double amount) {
        String subject = type + environment.getProperty("mail.subject");
        String content = user.getName() + environment.getProperty("mail.amount.info.message")
                + amount + environment.getProperty("mail.balance.info.message") + user.getBalance();
        mailSenderUtil.sendEmail(user.getEmail(), subject, content);
    }

    public void autoPaymentNotification(String to, LocalDateTime date) {
        mailSenderUtil.sendEmail(to, environment.getProperty("mail.notification.subject"),
                environment.getProperty("mail.notification.message") + date.toString());
    }

    public void regVerifyNotification(User user, String token) {
        mailSenderUtil.sendEmail(user.getEmail(),
                "Complete Registration!", "To confirm your account, please click here : " + "http://localhost:8000/api/v1/auth/confirm-account?token=" + token);
    }

    public void successRegister(User user){
        mailSenderUtil.sendEmail(user.getEmail(), "Congratulations","You have registered successfully");
    }
}
