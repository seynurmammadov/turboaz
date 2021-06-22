package az.code.myauto.utils;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

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

    public void sendNotification(String type, String to, String name, Double amount, Double balance) {
        String subject = type + environment.getProperty("mail.subject");
        String content = name + environment.getProperty("mail.amount.info.message")
                + amount + environment.getProperty("mail.balance.info.message") + balance;
        mailSenderUtil.sendEmail(to, subject, content);
    }
}
