package org.daming.hoteler.auth.repository;

import org.daming.hoteler.auth.domain.Role;
import org.daming.hoteler.common.exceptions.HotelerException;

import java.util.List;
import java.util.Optional;

/**
 * @author gming001
 * @version 2023-10-01 10:55
 */
public interface IRoleDao {

    List<Role> list() throws HotelerException;

    Optional<Role> get(long id) throws HotelerException;

    Optional<Role> getRoleByName(String roleName) throws HotelerException;

    List<Role> listByUserId(int userId) throws HotelerException;
}
