package org.daming.hoteler.auth.repository;

import org.daming.hoteler.common.exceptions.HotelerException;

/**
 * @author gming001
 * @version 2023-11-04 16:41
 */
public interface IUserRoleDao {

    int create(int userId, long roleId) throws HotelerException;
}
