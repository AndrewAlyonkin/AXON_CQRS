package com.afalenkin.usercmdapi.commands;

import com.afalenkin.core.model.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;

    @Valid
    private User user;
}
