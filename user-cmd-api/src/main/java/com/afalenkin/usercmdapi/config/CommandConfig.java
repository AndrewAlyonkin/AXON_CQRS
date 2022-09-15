package com.afalenkin.usercmdapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Configuration
public class CommandConfig {
    public static final int ENCODING_STRENGTH = 12;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(ENCODING_STRENGTH);
    }
}
