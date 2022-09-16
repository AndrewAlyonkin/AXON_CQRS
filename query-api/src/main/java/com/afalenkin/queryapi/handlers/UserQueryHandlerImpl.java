package com.afalenkin.queryapi.handlers;

import com.afalenkin.core.model.User;
import com.afalenkin.queryapi.dto.QueryResponse;
import com.afalenkin.queryapi.queries.FindAllUsersQuery;
import com.afalenkin.queryapi.queries.FindUserByIdQuery;
import com.afalenkin.queryapi.queries.SearchUsersQuery;
import com.afalenkin.queryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    @QueryHandler
    @Override
    public QueryResponse getUserById(FindUserByIdQuery query) {
        List<User> user = userRepository.findById(query.getId())
                .map(List::of)
                .orElse(Collections.emptyList());

        return new QueryResponse(user);
    }

    @QueryHandler
    @Override
    public QueryResponse getAllUsers(FindAllUsersQuery query) {
        return new QueryResponse(userRepository.findAll());
    }

    @QueryHandler
    @Override
    public QueryResponse searchUsers(SearchUsersQuery query) {
        return new QueryResponse(userRepository.findFiltered(query.getFilter()));
    }
}
