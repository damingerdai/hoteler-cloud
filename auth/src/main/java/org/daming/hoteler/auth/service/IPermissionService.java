package org.daming.hoteler.auth.service;

import org.daming.hoteler.auth.domain.Permission;
import org.daming.hoteler.common.exceptions.HotelerException;

import java.util.List;
import java.util.Optional;

/**
 * @author gming001
 * @version 2023-11-08 10:06
 */
public interface IPermissionService {

    List<Permission> list() throws HotelerException;

    Optional<Permission> get(int id) throws HotelerException;

    List<Permission> listByRoleIds(long[] roleIds) throws HotelerException;
}
