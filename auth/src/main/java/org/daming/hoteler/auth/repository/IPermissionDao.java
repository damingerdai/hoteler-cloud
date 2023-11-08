package org.daming.hoteler.auth.repository;

import org.daming.hoteler.auth.domain.Permission;
import org.daming.hoteler.common.exceptions.HotelerException;

import java.util.List;
import java.util.Optional;

/**
 * @author gming001
 * @version 2023-11-06 20:28
 */
public interface IPermissionDao {

    List<Permission> list() throws HotelerException;

    Optional<Permission> get(int id) throws HotelerException;

    List<Permission> listByUserId(int userId) throws HotelerException;

    List<Permission> listByRoleId(long roleId) throws HotelerException;

    List<Permission> listByRoleIds(long[] roleIds) throws HotelerException;
}
