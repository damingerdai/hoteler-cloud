package org.daming.hoteler.auth.repository.impl;

import org.daming.hoteler.auth.domain.Role;
import org.daming.hoteler.auth.repository.IRoleDao;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    public Optional<Role> get(long id) throws HotelerException {
        var statement = "SELECT id, name, description FROM roles WHERE id = ? AND deleted_at IS NULL";
        var role =  this.jdbcTemplate.query(statement, this::extractData, id);

        return Optional.ofNullable(role);
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) throws HotelerException {
        var statement = "SELECT id, name, description FROM roles WHERE name = ? AND deleted_at IS NULL";
        var role =  this.jdbcTemplate.query(statement, this::extractData, roleName);

        return Optional.ofNullable(role);
    }

    @Override
    public List<Role> listByUserId(int userId) throws HotelerException {
        var statement = """
           SELECT r.id AS id, r.name AS name, r.description AS description FROM roles r
           JOIN user_roles ur ON ur.role_id = r.id AND ur.user_id  = ? AND ur.deleted_at  IS NULL
           WHERE r.deleted_at IS NULL
        """;

        return this.jdbcTemplate.query(statement, this::rowsMapper, userId);
    }

    private Role rowsMapper(ResultSet rs, int i) throws SQLException {
        var role = new Role();
        role.setId(rs.getLong("id"));
        role.setName(rs.getString("name"));
        role.setDescription(rs.getString("description"));

        return role;
    }

    private Role extractData(ResultSet rs) throws SQLException, DataAccessException {
        while (rs.next()) {
            var role = new Role();
            role.setId(rs.getLong("id"));
            role.setName(rs.getString("name"));
            role.setDescription(rs.getString("description"));
            return role;
        }

        return null;
    }

    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
