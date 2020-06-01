package com.ksv.internetshop.security;

import com.ksv.internetshop.exception.AuthenticationException;
import com.ksv.internetshop.lib.Inject;
import com.ksv.internetshop.lib.Service;
import com.ksv.internetshop.model.User;
import com.ksv.internetshop.service.UserService;
import com.ksv.internetshop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        var user = userService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect username or password"));
        var hashPassword = HashUtil.hashPassword(password, user.getSalt());
        if (user.getPassword().equals(hashPassword)) {
            return user;
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
