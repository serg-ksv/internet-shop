package com.ksv.internetshop.service;

import com.ksv.internetshop.model.User;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    User create(User user);

    User update(User user);

    Optional<User> findByLogin(String login);
}
