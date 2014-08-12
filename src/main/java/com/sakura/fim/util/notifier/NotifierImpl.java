package com.sakura.fim.util.notifier;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class NotifierImpl implements Notifier {

    public static final Logger LOGGER = LoggerFactory.getLogger(NotifierImpl.class.getName());

    @Autowired
    private NotifierConfiguration config;

    @Autowired
    private JavaMailSender mailSender;

    public void send(NotificationProducer notificationProducer) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(config.getNotificationRecipients());
            helper.setFrom(config.getSender());
            helper.setSubject(notificationProducer.getSubject());
            switch (notificationProducer.getNotifierType()) {
                case EMAIL_HTML :
                    helper.setText(notificationProducer.getBodyMessage(), true);
                    break;
                case EMAIL_TEXT :
                default:
                    helper.setText(notificationProducer.getBodyMessage(), false);
            }

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("An unexpected error happened while trying to send a notification!", e);
        }
    }
}
