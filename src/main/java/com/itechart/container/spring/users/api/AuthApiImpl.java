package com.itechart.container.spring.users.api;

import com.itechart.container.spring.users.generated.api.AuthApi;
import com.itechart.container.spring.users.generated.model.*;
import com.itechart.container.spring.users.service.AuthService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Api(tags = "auth")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthApiImpl implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<User> signUp(@Valid @RequestBody final SignUpRequest signUpRequest) {
        var user = authService.signUp(signUpRequest);
        return new ResponseEntity<>(user, CREATED);
    }

    @Override
    public ResponseEntity<TokenResponse> signIn(@Valid @RequestBody final SignInRequest signInRequest) {
        var tokenResponse = authService.signIn(signInRequest);
        return new ResponseEntity<>(tokenResponse, CREATED);
    }

    @Override
    public ResponseEntity<TokenResponse> refresh(@RequestBody final TokenRequest tokenRequest) {
        var tokenResponse = authService.refresh(tokenRequest);
        return new ResponseEntity<>(tokenResponse, CREATED);
    }

}
