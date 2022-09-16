package com.afalenkin.queryapi.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindUserByIdQuery {
    private String id;
}
