package com.cambiazo.user.interfaces.rest;

import com.cambiazo.security.jwt.JwtUtil;
import com.cambiazo.user.domain.services.UserCommandService;
import com.cambiazo.user.interfaces.rest.resources.AuthenticatedUserResource;
import com.cambiazo.user.interfaces.rest.resources.SignInResource;
import com.cambiazo.user.interfaces.rest.resources.SignUpResource;
import com.cambiazo.user.interfaces.rest.resources.UserResource;
import com.cambiazo.user.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.cambiazo.user.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.cambiazo.user.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.cambiazo.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(UserCommandService userCommandService, JwtUtil jwtUtil) {
        this.userCommandService = userCommandService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/validate")
    public ResponseEntity<AuthenticatedUserResource> validateCredentials(@RequestBody SignInResource signInResource) {
        var cmd = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var userOpt = userCommandService.validateCredentials(cmd);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var user = userOpt.get();
        String token = jwtUtil.generateToken(user.getUsername(), "ROLE_USER,ROLE_ADMIN");
        var resource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(user, token);
        return ResponseEntity.ok(resource);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}