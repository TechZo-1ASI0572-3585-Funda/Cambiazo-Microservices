package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.aggregates.User;
import com.cambiazo.user.domain.model.commands.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> validateCredentials(SignInCommand command);
    Optional<User> handle(SignUpCommand command);
    Optional<User>handle(UpdateUserCommand command);

    Optional<ImmutablePair<User, String>>handle(UpdateProfileUserCommand command);



    Optional<User>handle(UpdateUserPasswordCommand command);
//    boolean handleDeleteUserCommand(Long id);
}
