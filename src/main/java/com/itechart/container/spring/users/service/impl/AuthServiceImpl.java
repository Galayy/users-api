package com.itechart.container.spring.users.service.impl;

import com.itechart.container.spring.users.exception.EntityAlreadyProcessedException;
import com.itechart.container.spring.users.generated.model.*;
import com.itechart.container.spring.users.security.JwtTokenProvider;
import com.itechart.container.spring.users.service.AuthService;
import com.itechart.container.spring.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.itechart.container.spring.users.utils.SecurityUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public User signUp(final SignUpRequest signUpRequest) {
        isSignedIn();
        isNotExist(signUpRequest.getEmail());
        return userService.create(signUpRequest);
    }

    @Override
    public TokenResponse signIn(final SignInRequest signInRequest) {
        var user = userService.getByLogin(signInRequest.getEmail());
        var authenticationToken = new UsernamePasswordAuthenticationToken(user.getId(), signInRequest.getPassword());
        var authentication = authenticationManager.authenticate(authenticationToken);
        return jwtTokenProvider.createToken(authentication);
    }

    @Override
    public TokenResponse refresh(final TokenRequest tokenRequest) {
        return jwtTokenProvider.refreshTokens(tokenRequest.getToken());
    }

    private void isSignedIn() {
        if (getCurrentUser() != null) {
            throw new EntityAlreadyProcessedException("You can't sign up until logout");
        }
    }

    private void isNotExist(final String email) {
        userService.existsByLogin(email);
    }

}
