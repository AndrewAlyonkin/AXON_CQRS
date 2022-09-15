package com.afalenkin.usercmdapi.security;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface PasswordEncoder {
    String hashPassword(String password);
}
