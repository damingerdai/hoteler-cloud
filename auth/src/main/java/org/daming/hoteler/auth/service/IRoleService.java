package org.daming.hoteler.auth.service;

import org.daming.hoteler.auth.domain.Role;
import org.daming.hoteler.common.exceptions.HotelerException;

import java.util.List;

/**
 * @author gming001
 * @version 2023-10-01 11:07
 */
public interface IRoleService {

    List<Role> list() throws HotelerException;

    List<Role> listByUserId(int userId) throws HotelerException;
}
