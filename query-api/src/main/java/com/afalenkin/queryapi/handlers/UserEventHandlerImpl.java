package com.afalenkin.queryapi.handlers;

import com.afalenkin.core.events.UserDeletedEvent;
import com.afalenkin.core.events.UserRegisteredEvent;
import com.afalenkin.core.events.UserUpdatedEvent;
import com.afalenkin.queryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@RequiredArgsConstructor
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserDeletedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
