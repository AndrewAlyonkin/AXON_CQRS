package com.afalenkin.core.events;

import com.afalenkin.core.model.User;
import lombok.Builder;
import lombok.Data;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
@Builder
public class UserRegisteredEvent {
    private String id;
    private User user;
}
