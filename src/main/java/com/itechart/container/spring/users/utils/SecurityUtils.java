package com.itechart.container.spring.users.utils;

import com.itechart.container.spring.users.entity.UserEntity;
import com.itechart.container.spring.users.exception.ForbiddenAccessException;
import com.itechart.container.spring.users.generated.model.User;
import com.itechart.container.spring.users.generated.model.UserRole;
import com.itechart.container.spring.users.security.UserDetailsImpl;
import com.itechart.container.spring.users.entity.enums.UserEntityRole;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@UtilityClass
public class SecurityUtils {

    public static UserDetailsImpl getCurrentUser() {
        var principal = getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetailsImpl) principal;
        }
        return null;
    }

    public static boolean hasAdminAccessLevel() {
        return getCurrentUser() != null && getCurrentUser().getRole().equals(UserEntityRole.ROLE_ADMIN);
    }

    public static void isEnoughPermissions(final User currentUser, final UUID requestedId) {
        if (!(currentUser.getId().equals(requestedId) || currentUser.getRole().equals(UserRole.ADMIN))) {
            throw new ForbiddenAccessException("Low access level");
        }
    }

    public static void isEnoughPermissions(final User currentUser, final UserEntity requestedUser) {
        if (!(currentUser.getId().equals(requestedUser.getId()) || requestedUser.getRole().equals(UserEntityRole.ROLE_USER) &&
                currentUser.getRole().equals(UserRole.ADMIN))) {
            throw new ForbiddenAccessException(String.format("You cannot delete user with id %s", requestedUser.getId()));
        }
    }

}
