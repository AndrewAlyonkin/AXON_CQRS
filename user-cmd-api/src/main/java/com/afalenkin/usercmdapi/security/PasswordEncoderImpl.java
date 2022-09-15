package com.afalenkin.usercmdapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@RequiredArgsConstructor
public class PasswordEncoderImpl implements PasswordEncoder {
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
