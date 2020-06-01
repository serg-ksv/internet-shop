package com.ksv.internetshop.dao;

import com.ksv.internetshop.model.User;
import java.util.Optional;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login);
}
