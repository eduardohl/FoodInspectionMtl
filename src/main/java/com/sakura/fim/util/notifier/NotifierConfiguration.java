package com.sakura.fim.util.notifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotifierConfiguration {

    @Value("${notification.recipients}")
    private String notificationRecipients;
    
    @Value("${notification.sender}")
    private String notificationSender;

    public String[] getNotificationRecipients() {
        return notificationRecipients.split(",");
    }
    
    public String getSender() {
        return notificationSender;
    }
}
