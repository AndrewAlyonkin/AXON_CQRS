package com.afalenkin.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotEmpty(message = "firstName is mandatory")
    private String firstName;

    @NotEmpty(message = "lastName is mandatory")
    private String lastName;

    @Email(message = "please provide the valid email")
    private String email;

    @NotNull
    @Valid
    private Account account;
}
