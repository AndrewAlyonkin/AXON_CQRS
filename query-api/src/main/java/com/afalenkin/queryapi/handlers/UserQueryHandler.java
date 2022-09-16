package com.afalenkin.queryapi.handlers;

import com.afalenkin.queryapi.dto.QueryResponse;
import com.afalenkin.queryapi.queries.FindAllUsersQuery;
import com.afalenkin.queryapi.queries.FindUserByIdQuery;
import com.afalenkin.queryapi.queries.SearchUsersQuery;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserQueryHandler {

    QueryResponse getUserById(FindUserByIdQuery query);

    QueryResponse getAllUsers(FindAllUsersQuery query);

    QueryResponse searchUsers(SearchUsersQuery query);
}
