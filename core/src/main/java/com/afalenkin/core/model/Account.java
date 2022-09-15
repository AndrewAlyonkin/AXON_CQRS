package com.afalenkin.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Size(min = 2, message = "username should contains at least 2 characters")
    private String userName;

    @Size(min = 8, message = "password should contains at least 8 characters")
    private String password;

    @NotNull
    private List<Role> roles;
}
