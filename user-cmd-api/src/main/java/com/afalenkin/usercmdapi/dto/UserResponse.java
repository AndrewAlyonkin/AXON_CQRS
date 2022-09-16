package com.afalenkin.usercmdapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
public class UserResponse extends BaseResponse{
    private String id;

    public UserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
