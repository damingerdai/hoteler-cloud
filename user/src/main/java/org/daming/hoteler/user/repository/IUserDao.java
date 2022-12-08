package org.daming.hoteler.user.repository;

import org.daming.hoteler.user.domain.User;

/**
 * @author gming001
 * @version 2022-12-08 12:10
 */
public interface IUserDao {

    int create(User user);

    User get(int id);
}
