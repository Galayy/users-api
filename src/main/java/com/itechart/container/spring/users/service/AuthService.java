package com.itechart.container.spring.users.service;

import com.itechart.container.spring.users.generated.model.*;

public interface AuthService {

    User signUp(SignUpRequest signUpRequest);

    TokenResponse signIn(SignInRequest signInRequest);

    TokenResponse refresh(TokenRequest tokenRequest);

}
