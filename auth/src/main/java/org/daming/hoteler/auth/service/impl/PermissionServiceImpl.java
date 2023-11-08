package org.daming.hoteler.auth.service.impl;

import org.daming.hoteler.auth.domain.Permission;
import org.daming.hoteler.auth.repository.IPermissionDao;
import org.daming.hoteler.auth.service.IPermissionService;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author gming001
 * @version 2023-11-08 11:21
 */
@Service
public class PermissionServiceImpl implements IPermissionService  {

    private final IPermissionDao permissionDao;

    @Override
    public List<Permission> list() throws HotelerException {
        return this.permissionDao.list();
    }

    @Override
    public Optional<Permission> get(int id) throws HotelerException {
        return this.permissionDao.get(id);
    }

    @Override
    public List<Permission> listByRoleIds(long[] roleIds) throws HotelerException {
        return this.permissionDao.listByRoleIds(roleIds);
    }

    public PermissionServiceImpl(IPermissionDao permissionDao) {
        super();
        this.permissionDao = permissionDao;
    }
}
