package org.daming.hoteler.user.service;

import org.daming.hoteler.user.domain.User;

/**
 * @author gming001
 * @version 2022-12-08 13:52
 */
public interface IUserService {

    int create(User user);

    User get(int id);

    User getUserByUsername(String username);
}
