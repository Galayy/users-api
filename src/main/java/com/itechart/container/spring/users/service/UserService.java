package com.itechart.container.spring.users.service;

import com.itechart.container.spring.users.generated.model.SignUpRequest;
import com.itechart.container.spring.users.generated.model.UpdateUserRequest;
import com.itechart.container.spring.users.generated.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<User> getPage(Pageable pageable);

    User create(SignUpRequest signUpRequest);

    User getCurrent();

    User getById(UUID id);

    User getByLogin(String email);

    User update(UUID id, UpdateUserRequest newUser);

    void deleteById(UUID id);

    void existsByLogin(String email);

}
