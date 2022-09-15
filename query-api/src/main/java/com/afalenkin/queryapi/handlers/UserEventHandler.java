package com.afalenkin.queryapi.handlers;

import com.afalenkin.core.events.UserDeletedEvent;
import com.afalenkin.core.events.UserRegisteredEvent;
import com.afalenkin.core.events.UserUpdatedEvent;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserEventHandler {
    void on(UserRegisteredEvent event);

    void on(UserUpdatedEvent event);

    void on(UserDeletedEvent event);
}
