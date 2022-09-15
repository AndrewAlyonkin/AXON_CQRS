package com.afalenkin.usercmdapi.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
public class DeleteUserCommand {
    @TargetAggregateIdentifier
    private String id;
}
