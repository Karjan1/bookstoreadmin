package com.karoljanowski.service;

import com.karoljanowski.domain.User;
import com.karoljanowski.domain.security.UserRole;

import java.util.Set;

/**
 * Created by Karol Janowski on 2017-06-26.
 */

public interface UserService {

    User findByUsername(String username);
    User findByEmail(String email);
    User createUser(User user, Set<UserRole> userRoles) throws Exception;
    User save(User user);
}
