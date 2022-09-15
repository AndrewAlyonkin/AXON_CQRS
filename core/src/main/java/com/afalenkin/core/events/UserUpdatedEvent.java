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
public class UserUpdatedEvent {
    private String id;
    private User user;
}
