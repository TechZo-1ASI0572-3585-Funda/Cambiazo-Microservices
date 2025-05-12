package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.aggregates.User;
import com.cambiazo.user.domain.model.dto.UserNameDto;
import com.cambiazo.user.domain.model.queries.GetAllUsersQuery;
import com.cambiazo.user.domain.model.queries.GetUserByEmailQuery;
import com.cambiazo.user.domain.model.queries.GetUserByIdQuery;
import com.cambiazo.user.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);
    Optional<UserNameDto> handle(GetUserByEmailQuery query);
}
