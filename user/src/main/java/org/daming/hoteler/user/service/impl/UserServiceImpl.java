package org.daming.hoteler.user.service.impl;

import org.daming.hoteler.user.domain.User;
import org.daming.hoteler.user.repository.IUserDao;
import org.daming.hoteler.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author gming001
 * @version 2022-12-08 13:53
 */
@Service
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    @Override
    public int create(User user) {
        var id = this.userDao.create(user);
        user.setId(id);
        return id;
    }

    @Override
    public User get(int id) {
        return this.userDao.get(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userDao.getUserByUsername(username);
    }

    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }
}
