package org.daming.hoteler.auth.repository.impl;

import org.daming.hoteler.auth.domain.Role;
import org.daming.hoteler.auth.repository.IRoleDao;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return this.jdbcTemplate.query(statement, this::rowsMapper);
    }

    @Override
    public List<Role> listByUserId(int userId) throws HotelerException {
        var statement = """
           SELECT r.id AS id, r.name AS name, r.description AS description FROM roles r
           JOIN user_roles ur ON ur.role_id = r.id AND ur.user_id  = ? AND ur.deleted_at  IS NULL
           WHERE r.deleted_at IS NULL
        """;

        return this.jdbcTemplate.query(statement, this::rowsMapper, new Object[] { userId });
    }

    private Role rowsMapper(ResultSet rs, int i) throws SQLException {
        var role = new Role();
        role.setId(rs.getLong("id"));
        role.setName(rs.getString("name"));
        role.setDescription(rs.getString("description"));

        return role;
    }

    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
