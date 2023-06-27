package org.daming.hoteler.auth.service.impl;

import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.repository.IUserDao;
import org.daming.hoteler.auth.service.ICardIdService;
import org.daming.hoteler.auth.service.IPasswordHelperService;
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

    private IPasswordHelperService passwordHelperService;

    @Override
    public int create(User user) {
        var passwordService = this.passwordHelperService.getPasswordService(user.getPasswordType());
        var encodePassword = passwordService.encodePassword(user.getPassword());
        user.setPassword(encodePassword);
        var id = this.userDao.create(user);
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

    @Override
    public void update(User user) {
        var passwordService = this.passwordHelperService.getPasswordService(user.getPasswordType());
        var encodePassword = passwordService.encodePassword(user.getPassword());
        user.setPassword(encodePassword);
        this.userDao.update(user);
    }

    @Autowired
    public void setCardIdService(ICardIdService cardIdService) {
        this.cardIdService = cardIdService;
    }

    @Autowired
    public void setPasswordHelperService(IPasswordHelperService passwordHelperService) {
        this.passwordHelperService = passwordHelperService;
    }

    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }
}
