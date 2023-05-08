package org.daming.hoteler.auth.service.impl;

import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.repository.IUserDao;
import org.daming.hoteler.auth.service.ICardIdService;
import org.daming.hoteler.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gming001
 * @version 2022-12-08 13:53
 */
@Service
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    private ICardIdService cardIdService;

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

    @Autowired
    public void setCardIdService(ICardIdService cardIdService) {
        this.cardIdService = cardIdService;
    }

    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }
}
