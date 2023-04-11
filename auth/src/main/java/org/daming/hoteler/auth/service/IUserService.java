package org.daming.hoteler.auth.service;

import org.daming.hoteler.auth.domain.User;

/**
 * @author gming001
 * @version 2022-12-08 13:52
 */
public interface IUserService {

    int create(User user);

    User get(int id);

    User getUserByUsername(String username);
}
