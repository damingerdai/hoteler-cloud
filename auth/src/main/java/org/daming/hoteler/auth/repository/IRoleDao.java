package org.daming.hoteler.auth.repository;

import org.daming.hoteler.auth.domain.Role;
import org.daming.hoteler.common.exceptions.HotelerException;

import java.util.List;

/**
 * @author gming001
 * @version 2023-10-01 10:55
 */
public interface IRoleDao {

    List<Role> list() throws HotelerException;
}
