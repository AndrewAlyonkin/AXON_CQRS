package com.afalenkin.usercmdapi.aggregates;

import com.afalenkin.core.events.UserDeletedEvent;
import com.afalenkin.core.events.UserRegisteredEvent;
import com.afalenkin.core.events.UserUpdatedEvent;
import com.afalenkin.core.model.Account;
import com.afalenkin.core.model.User;
import com.afalenkin.usercmdapi.commands.DeleteUserCommand;
import com.afalenkin.usercmdapi.commands.RegisterUserCommand;
import com.afalenkin.usercmdapi.commands.UpdateUserCommand;
import com.afalenkin.usercmdapi.security.PasswordEncoder;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Aggregate
@NoArgsConstructor
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    @CommandHandler
    public UserAggregate(RegisterUserCommand command, PasswordEncoder passwordEncoder) {
        User registeringUser = command.getUser();
        String registeringId = command.getId();
        normalizeUserData(registeringUser, registeringId, passwordEncoder);

        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .id(registeringId)
                .user(registeringUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();

    }

    @CommandHandler
    public void handle(UpdateUserCommand command, PasswordEncoder passwordEncoder) {
        User updatingUser = command.getUser();
        normalizeUserData(updatingUser, command.getId(), passwordEncoder);

        UserUpdatedEvent event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatingUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @CommandHandler
    public void handle(DeleteUserCommand command) {
        UserDeletedEvent event = new UserDeletedEvent();
        event.setId(command.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }

    private void normalizeUserData(User currentUser, String currentId, PasswordEncoder passwordEncoder) {
        currentUser.setId(currentId);
        String password = Optional.ofNullable(currentUser)
                .map(User::getAccount)
                .map(Account::getPassword)
                .orElseThrow(IllegalArgumentException::new);
        String hashPassword = passwordEncoder.hashPassword(password);
        currentUser.getAccount().setPassword(hashPassword);
    }
}
