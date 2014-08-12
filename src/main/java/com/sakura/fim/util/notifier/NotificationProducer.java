package com.sakura.fim.util.notifier;

public interface NotificationProducer {
    String getSubject();
    String getBodyMessage();
    NotifierType getNotifierType();
}
