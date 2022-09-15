package com.afalenkin.usercmdapi.controllers;

import com.afalenkin.usercmdapi.commands.RegisterUserCommand;
import com.afalenkin.usercmdapi.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(path = "api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    public static final String ERROR_MESSAGE = "Error while registering user with id = %s";
    public static final String SUCCESS_MESSAGE = "User id = %s successfully registered";
    private final CommandGateway commandGateway;


    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserCommand registerCommand) {
        String id = UUID.randomUUID().toString();
        try {
            registerCommand.setId(id);
            commandGateway.sendAndWait(registerCommand);

            return new ResponseEntity<>(new UserResponse(String.format(SUCCESS_MESSAGE, id)), HttpStatus.CREATED);

        } catch (Exception e) {
            String message = String.format(ERROR_MESSAGE, id);
            log.error(message, e);
            return new ResponseEntity<>(new UserResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
