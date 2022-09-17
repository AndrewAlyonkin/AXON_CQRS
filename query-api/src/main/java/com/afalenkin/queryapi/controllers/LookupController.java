package com.afalenkin.queryapi.controllers;

import com.afalenkin.queryapi.dto.QueryResponse;
import com.afalenkin.queryapi.queries.FindAllUsersQuery;
import com.afalenkin.queryapi.queries.FindUserByIdQuery;
import com.afalenkin.queryapi.queries.SearchUsersQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(path = "api/v1/users/lookup")
@RequiredArgsConstructor
@Slf4j
public class LookupController {
    public static final String ERROR_MESSAGE = "Operation failed...";
    private final QueryGateway queryGateway;

    @GetMapping
    @PreAuthorize("hasAuthority('READER')")
    public ResponseEntity<QueryResponse> getAllUsers() {
        return getResponse(new FindAllUsersQuery());
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('READER')")
    public ResponseEntity<QueryResponse> getUserById(@PathVariable(value = "id") String id) {
        return getResponse(new FindUserByIdQuery(id));
    }

    @GetMapping(path = "search/{filter}")
    @PreAuthorize("hasAuthority('READER')")
    public ResponseEntity<QueryResponse> getUserByFilter(@PathVariable(value = "filter") String filter) {
        return getResponse(new SearchUsersQuery(filter));
    }

    private ResponseEntity<QueryResponse> getResponse(Object query) {
        try {
            QueryResponse response = queryGateway.query(query, ResponseTypes.instanceOf(QueryResponse.class)).join();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
            return new ResponseEntity<>(
                    new QueryResponse(ERROR_MESSAGE, Collections.emptyList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
