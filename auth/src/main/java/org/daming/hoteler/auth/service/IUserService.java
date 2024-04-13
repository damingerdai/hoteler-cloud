package org.daming.hoteler.auth.service;

import org.daming.hoteler.auth.domain.User;

import java.util.List;

/**
 * @author gming001
 * @version 2022-12-08 13:52
 */
public interface IUserService {

    int create(User user);

    User get(int id);

    User getUserByUsername(String username);

    void update(User user);

    void delete(int id);

    List<User> list();
}
