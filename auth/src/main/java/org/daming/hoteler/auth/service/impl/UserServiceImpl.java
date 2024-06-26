package org.daming.hoteler.auth.service.impl;

import org.daming.hoteler.auth.domain.Role;
import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.domain.enums.RoleType;
import org.daming.hoteler.auth.repository.IRoleDao;
import org.daming.hoteler.auth.repository.IUserDao;
import org.daming.hoteler.auth.repository.IUserRoleDao;
import org.daming.hoteler.auth.service.ICardIdService;
import org.daming.hoteler.auth.service.IPasswordHelperService;
import org.daming.hoteler.auth.service.IPermissionService;
import org.daming.hoteler.auth.service.IRoleService;
import org.daming.hoteler.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author gming001
 * @version 2022-12-08 13:53
 */
@Service
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    private IUserRoleDao userRoleDao;

    private ICardIdService cardIdService;

    private IRoleService roleService;

    private IPermissionService permissionService;

    private IPasswordHelperService passwordHelperService;

    @Override
    @Transactional
    public int create(User user) {
        var passwordService = this.passwordHelperService.getPasswordService(user.getPasswordType());
        var encodePassword = passwordService.encodePassword(user.getPassword());
        user.setPassword(encodePassword);
        var id = this.userDao.create(user);
        this.userRoleDao.create(id, RoleType.User.id());
        return id;
    }

    @Override
    public User get(int id) {
        var user = this.userDao.get(id);
        if (Objects.nonNull(user)) {
            var roles = this.roleService.listByUserId(user.getId());
            user.setRoles(roles);
            var permissions = this.permissionService.listByRoleIds(roles.stream().mapToLong(Role::getId).toArray());
            user.setPermissions(permissions);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        var user = this.userDao.getUserByUsername(username);
        if (Objects.nonNull(user)) {
            var roles = this.roleService.listByUserId(user.getId());
            user.setRoles(roles);
            var permissions = this.permissionService.listByRoleIds(roles.stream().mapToLong(Role::getId).toArray());
            user.setPermissions(permissions);
        }

        return user;
    }

    @Override
    public void update(User user) {
        var passwordService = this.passwordHelperService.getPasswordService(user.getPasswordType());
        var encodePassword = passwordService.encodePassword(user.getPassword());
        user.setPassword(encodePassword);
        this.userDao.update(user);
    }

    @Override
    public void delete(int id) {
        this.userDao.delete(id);
    }

    @Override
    public List<User> list() {
        return this.userDao.list();
    }

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setCardIdService(ICardIdService cardIdService) {
        this.cardIdService = cardIdService;
    }

    @Autowired
    public void setPasswordHelperService(IPasswordHelperService passwordHelperService) {
        this.passwordHelperService = passwordHelperService;
    }

    public UserServiceImpl(IUserDao userDao, IUserRoleDao userRoleDao) {
        super();
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }
}
