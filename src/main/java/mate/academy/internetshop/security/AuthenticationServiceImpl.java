package mate.academy.internetshop.security;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

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
