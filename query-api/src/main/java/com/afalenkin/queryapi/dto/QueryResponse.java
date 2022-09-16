package com.afalenkin.queryapi.dto;

import com.afalenkin.core.dto.BaseResponse;
import com.afalenkin.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Getter
@Setter
public class QueryResponse extends BaseResponse {
    private List<User> users;

    public QueryResponse(String message, List<User> users) {
        super(message);
        this.users = users;
    }

    public QueryResponse(List<User> users) {
        super(null);
        this.users = users;
    }
}
