package org.daming.hoteler.auth.repository;

import org.daming.hoteler.auth.domain.User;

/**
 * @author gming001
 * @version 2022-12-08 12:10
 */
public interface IUserDao {

    int create(User user);

    User get(int id);

    User getUserByUsername(String username);

    void update(User user);

    void delete(int id);
}
