package com.afalenkin.usercmdapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserCommand {
    @TargetAggregateIdentifier
    private String id;
}
