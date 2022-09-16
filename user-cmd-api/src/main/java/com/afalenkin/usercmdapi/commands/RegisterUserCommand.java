package com.afalenkin.usercmdapi.commands;

import com.afalenkin.core.model.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
@Builder
public class RegisterUserCommand {
    @TargetAggregateIdentifier
    private String id;

    @NotNull
    @Valid
    private User user;
}
