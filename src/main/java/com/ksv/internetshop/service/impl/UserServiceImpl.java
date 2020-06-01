package com.ksv.internetshop.service.impl;

import com.ksv.internetshop.dao.UserDao;
import com.ksv.internetshop.lib.Inject;
import com.ksv.internetshop.lib.Service;
import com.ksv.internetshop.model.User;
import com.ksv.internetshop.service.UserService;
import com.ksv.internetshop.util.HashUtil;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        byte[] salt = HashUtil.getSalt();
        var hashPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashPassword);
        user.setSalt(salt);
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).get();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }
}
