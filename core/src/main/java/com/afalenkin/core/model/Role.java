package com.afalenkin.core.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public enum Role implements GrantedAuthority {
    READER,
    WRITER;

    @Override
    public String getAuthority() {
        return name();
    }
}
