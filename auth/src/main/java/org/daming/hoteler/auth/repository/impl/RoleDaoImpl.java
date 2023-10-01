package org.daming.hoteler.auth.repository.impl;

import org.daming.hoteler.auth.domain.Role;
import org.daming.hoteler.auth.repository.IRoleDao;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gming001
 * @version 2023-10-01 11:01
 */
@Repository
public class RoleDaoImpl implements IRoleDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> list() throws HotelerException {
        var statement = "SELECT id, name, description FROM roles WHERE deleted_at IS NULL";
        var roles = this.jdbcTemplate.query(statement, (rs, i) -> {
            var role = new Role();
            role.setId(rs.getLong("id"));
            role.setName(rs.getString("name"));
            role.setDescription(rs.getString("description"));

            return role;
        });
        return roles;
    }

    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
