package com.cambiazo.user.application.internal.queryservices;

import com.cambiazo.user.domain.model.aggregates.User;
import com.cambiazo.user.domain.model.dto.UserNameDto;
import com.cambiazo.user.domain.model.queries.GetAllUsersQuery;
import com.cambiazo.user.domain.model.queries.GetUserByEmailQuery;
import com.cambiazo.user.domain.model.queries.GetUserByIdQuery;
import com.cambiazo.user.domain.model.queries.GetUserByUsernameQuery;
import com.cambiazo.user.domain.services.UserQueryService;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserQueryService} interface.
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    /**
     * Constructor.
     *
     * @param userRepository {@link UserRepository} instance.
     */
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is used to handle {@link GetAllUsersQuery} query.
     * @param query {@link GetAllUsersQuery} instance.
     * @return {@link List} of {@link User} instances.
     * @see GetAllUsersQuery
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    /**
     * This method is used to handle {@link GetUserByIdQuery} query.
     * @param query {@link GetUserByIdQuery} instance.
     * @return {@link Optional} of {@link User} instance.
     * @see GetUserByIdQuery
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    /**
     * This method is used to handle {@link GetUserByUsernameQuery} query.
     * @param query {@link GetUserByUsernameQuery} instance.
     * @return {@link Optional} of {@link User} instance.
     * @see GetUserByUsernameQuery
     */
    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }

    /**
     * This method is used to handle {@link GetUserByEmailQuery} query.
     * @param query {@link GetUserByEmailQuery} instance.
     * @return {@link Optional} of {@link User} instance.
     * @see GetUserByEmailQuery
     */
    @Override
    public Optional<UserNameDto> handle(GetUserByEmailQuery query) {
        var result = userRepository.findByUsername(query.username());
        return Optional.of(new UserNameDto(result.get().getName(), result.get().getIsGoogleAccount()));
    }
}
