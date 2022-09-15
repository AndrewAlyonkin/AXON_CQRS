package com.afalenkin.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String userName;
    private String password;
    private List<Role> roles;
}
