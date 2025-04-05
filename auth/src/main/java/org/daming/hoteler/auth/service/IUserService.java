package org.daming.hoteler.auth.service;

import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.domain.request.CreateUserRequest;

import java.util.List;

/**
 * @author gming001
 * @version 2022-12-08 13:52
 */
public interface IUserService {

    int create(CreateUserRequest user);

    User get(int id);

    User getUserByUsername(String username);

    User update(User user);

    void delete(int id);

    List<User> list();

    void loginFailed(User user);

    void resetFailedAttempts(User user);

    boolean isAccountLocked(int id);

    boolean isAccountLocked(User user);

    List<User> getUnlockUsers();
}
