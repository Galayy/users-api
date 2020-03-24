package com.itechart.container.spring.users.service.impl;

import com.itechart.container.spring.users.entity.UserEntity;
import com.itechart.container.spring.users.exception.EntityAlreadyProcessedException;
import com.itechart.container.spring.users.exception.EntityNotFoundException;
import com.itechart.container.spring.users.exception.ForbiddenAccessException;
import com.itechart.container.spring.users.generated.model.SignUpRequest;
import com.itechart.container.spring.users.generated.model.UpdateUserRequest;
import com.itechart.container.spring.users.generated.model.User;
import com.itechart.container.spring.users.repository.UserRepository;
import com.itechart.container.spring.users.security.UserDetailsImpl;
import com.itechart.container.spring.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

import static com.itechart.container.spring.users.entity.enums.UserEntityRole.ROLE_USER;
import static com.itechart.container.spring.users.mapper.UserMapper.USER_MAPPER;
import static com.itechart.container.spring.users.utils.SecurityUtils.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Page<User> getPage(final Pageable pageable) {
        return userRepository.search(hasAdminAccessLevel(), pageable).map(USER_MAPPER::toModel);
    }

    @Override
    @Transactional
    public User create(final SignUpRequest signUpRequest) {
        var encryptedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        var entity = new UserEntity();
        entity.setPhone(signUpRequest.getPhone());
        entity.setPassword(encryptedPassword);
        entity.setLogin(signUpRequest.getEmail());
        entity.setFirstName(signUpRequest.getFirstName());
        entity.setLastName(signUpRequest.getLastName());
        entity.setRole(ROLE_USER);

        return USER_MAPPER.toModel(userRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrent() {
        return userRepository.findById(getCurrentUser().getId()).map(USER_MAPPER::toModel)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(final UUID id) {
        return userRepository.findById(id).map(USER_MAPPER::toModel)
                .orElseThrow(() -> new EntityNotFoundException(format("User with id %s doesn't exist", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByLogin(final String email) {
        return userRepository.findByLogin(email).map(USER_MAPPER::toModel).orElseThrow(() ->
                new EntityNotFoundException(format("User with email %s doesn't exist or disabled", email)));
    }

    @Override
    @Transactional
    public User update(final UUID id, final UpdateUserRequest userToUpdate) {
        var entityToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("User with id %s doesn't exist", id)));
        var currentUser = getCurrent();
        isEnoughPermissions(currentUser, id);

        entityToUpdate.setPhone(userToUpdate.getNewPhone());
        entityToUpdate.setFirstName(userToUpdate.getNewFirstName());
        entityToUpdate.setLastName(userToUpdate.getNewLastName());

        return USER_MAPPER.toModel(userRepository.save(entityToUpdate));
    }

    @Override
    @Transactional
    public void deleteById(final UUID id) {
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("User with id %s doesn't exist", id)));
        var currentUser = getCurrent();
        isEnoughPermissions(currentUser, entity);

        userRepository.deleteById(id, Instant.now());
    }

    @Override
    @Transactional(readOnly = true)
    public void existsByLogin(final String email) {
        if (userRepository.existsByLogin(email)) {
            throw new EntityAlreadyProcessedException(format("User with email %s already exists", email));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String id) throws UsernameNotFoundException {
        var entity = userRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new UsernameNotFoundException(format("User with id %s not found", id)));
        isUserDeleted(entity.getDeletedAt());

        var userDetails = new UserDetailsImpl();
        userDetails.setId(entity.getId());
        userDetails.setPassword(entity.getPassword());
        userDetails.setRole(entity.getRole());
        return userDetails;
    }

    private void isUserDeleted(final Instant deletedAt) {
        if (deletedAt != null) {
            throw new ForbiddenAccessException(format("User was deleted at %s", deletedAt));
        }
    }

}
