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

    @Value("${mail.balance.info.message}")
    private String balanceNotificationText;

    final
    MailSenderUtil mailSenderUtil;


    public MessageUtil(MailSenderUtil mailSenderUtil) {
        this.mailSenderUtil = mailSenderUtil;
    }

    public void transactionsNotification(String type, User user, Double amount) {
        String subject = type + transactionNotificationSubject;
        String content = user.getName() + transactionNotificationText
                + amount + balanceNotificationText + user.getBalance();
        mailSenderUtil.sendEmail(user.getEmail(), subject, content);
    }

    public void autoPaymentNotification(String to, LocalDateTime date) {
        mailSenderUtil.sendEmail(to, autoPaymentNotificationSubject,
                autoPaymentNotificationText + date.toString());
    }

    public void regVerifyNotification(User user, String token) {
        mailSenderUtil.sendEmail(user.getEmail(), registrationVerifySubject,
                registrationVerifyText + token);
    }

    public void successRegister(User user) {
        mailSenderUtil.sendEmail(user.getEmail(), successMessageSubject, successMessageText);
    }
}
