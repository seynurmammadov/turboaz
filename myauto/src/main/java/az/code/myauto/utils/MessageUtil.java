package az.code.myauto.utils;

import az.code.myauto.models.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageUtil {

    @Value("${mail.registration.success.subject}")
    private String successMessageSubject;

    @Value("${mail.registration.success.message}")
    private String successMessageText;

    @Value("${mail.registration.complete.subject}")
    private String registrationVerifySubject;

    @Value("${mail.registration.success.message}")
    private String registrationVerifyText;

    @Value("${mail.notification.subject}")
    private String autoPaymentNotificationSubject;

    @Value("${mail.notification.message}")
    private String autoPaymentNotificationText;

    @Value("${mail.subject}")
    private String transactionNotificationSubject;

    @Value("${mail.amount.info.message}")
    private String transactionNotificationText;

    final
    MailSenderUtil mailSenderUtil;


    public MessageUtil(MailSenderUtil mailSenderUtil) {
        this.mailSenderUtil = mailSenderUtil;
    }

    public void transactionsNotification(String type, User user, Double amount) {
        mailSenderUtil.sendEmail(user.getEmail(), String.format(transactionNotificationSubject, type),
                String.format(transactionNotificationText,user.getName(), type.toLowerCase(), amount ,user.getBalance()));
    }

    public void autoPaymentNotification(String to, LocalDateTime date) {
        mailSenderUtil.sendEmail(to, autoPaymentNotificationSubject,
                String.format(autoPaymentNotificationText, date.toString()));
    }

    public void regVerifyNotification(User user, String token) {
        mailSenderUtil.sendEmail(user.getEmail(), registrationVerifySubject,
                String.format(registrationVerifyText, token));
    }

    public void successRegister(User user) {
        mailSenderUtil.sendEmail(user.getEmail(), successMessageSubject, successMessageText);
    }
}
