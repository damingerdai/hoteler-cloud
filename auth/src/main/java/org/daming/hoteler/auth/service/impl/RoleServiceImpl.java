package org.daming.hoteler.auth.service.impl;

import org.daming.hoteler.auth.domain.Role;
import org.daming.hoteler.auth.repository.IRoleDao;
import org.daming.hoteler.auth.service.IRoleService;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gming001
 * @version 2023-10-01 11:12
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private IRoleDao roleDao;

    @Override
    public List<Role> list() throws HotelerException {
        return this.roleDao.list();
    }

    @Override
    public List<Role> listByUserId(int userId) throws HotelerException {
        return this.roleDao.listByUserId(userId);
    }

    public RoleServiceImpl(IRoleDao roleDao) {
        super();
        this.roleDao = roleDao;
    }
}
