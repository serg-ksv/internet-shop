package com.ksv.internetshop.security;

import com.ksv.internetshop.exception.AuthenticationException;
import com.ksv.internetshop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
