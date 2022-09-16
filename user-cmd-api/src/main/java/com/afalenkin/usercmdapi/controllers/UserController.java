package com.afalenkin.usercmdapi.controllers;

import com.afalenkin.usercmdapi.commands.DeleteUserCommand;
import com.afalenkin.usercmdapi.commands.RegisterUserCommand;
import com.afalenkin.usercmdapi.commands.UpdateUserCommand;
import com.afalenkin.core.dto.BaseResponse;
import com.afalenkin.usercmdapi.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
    public static final String ERROR_MESSAGE = "Error while processing user with id = %s";
    public static final String REGISTERED_MESSAGE = "User id = %s successfully registered";
    public static final String UPDATE_MESSAGE = "User id = %s successfully updated";
    public static final String DELETE_MESSAGE = "User id = %s successfully deleted";

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid RegisterUserCommand registerCommand) {
        String id = UUID.randomUUID().toString();
        try {
            registerCommand.setId(id);
            commandGateway.sendAndWait(registerCommand);
            return new ResponseEntity<>(new UserResponse(id, String.format(REGISTERED_MESSAGE, id)), HttpStatus.OK);
        } catch (Exception e) {
            String message = String.format(ERROR_MESSAGE, id);
            log.error(message, e);
            return new ResponseEntity<>(new UserResponse(id, message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody @Valid UpdateUserCommand updateCommand,
                                                   @PathVariable(value = "id") @NotEmpty String id) {
        try {
            updateCommand.setId(id);
            commandGateway.sendAndWait(updateCommand);
            return new ResponseEntity<>(new BaseResponse(String.format(UPDATE_MESSAGE, id)), HttpStatus.OK);
        } catch (Exception e) {
            String message = String.format(ERROR_MESSAGE, id);
            log.error(message, e);
            return new ResponseEntity<>(new BaseResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable(value = "id") @NotEmpty String id) {
        try {
            commandGateway.sendAndWait(new DeleteUserCommand(id));
            return new ResponseEntity<>(new BaseResponse(String.format(DELETE_MESSAGE, id)), HttpStatus.OK);
        } catch (Exception e) {
            String message = String.format(ERROR_MESSAGE, id);
            log.error(message, e);
            return new ResponseEntity<>(new BaseResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
