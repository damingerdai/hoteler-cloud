package org.daming.hoteler.auth.repository.impl;

import org.daming.hoteler.auth.domain.Permission;
import org.daming.hoteler.auth.repository.IPermissionDao;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author gming001
 * @version 2023-11-06 20:33
 */
@Repository
public class PermissionDaoImpl implements IPermissionDao {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Permission> list() throws HotelerException {
        var statement = "SELECT id, name, description FROM permissions WHERE deleted_at IS NULL";
        return this.jdbcTemplate.query(statement, this::rowsMapper);
    }

    @Override
    public Optional<Permission> get(int id) throws HotelerException {
        var statement = "SELECT id, name, description FROM permissions WHERE id = ? deleted_at IS NULL LIMIT 1";
        return Optional.ofNullable(this.jdbcTemplate.query(statement, this::extractData, id));
    }

    @Override
    public List<Permission> listByUserId(int userId) throws HotelerException {
        var statement = """
            SELECT DISTINCT(p.id), p.name, p.description FROM permissions p
            JOIN role_permissions rp ON rp.permission_id = p.id AND rp.deleted_at  IS NULl
            JOIN roles r  ON r.id = rp.role_id AND r.deleted_at IS NULL
            JOIN  user_roles ur ON ur.user_id  = ? AND ur.deleted_at IS NULL
            WHERE p.deleted_at IS NULL;
        """;
        return this.jdbcTemplate.query(statement, this::rowsMapper, userId);
    }

    @Override
    public List<Permission> listByRoleId(long roleId) throws HotelerException {
        var statement = """
                SELECT p.id, p.name, p.description FROM permissions p
                JOIN role_permissions rp ON rp.role_id  = ? AND rp.permission_id = p.id AND rp.deleted_at  IS NULl
                WHERE p.deleted_at IS NULL;
         """;
        return this.jdbcTemplate.query(statement, this::rowsMapper, roleId);
    }

    @Override
    public List<Permission> listByRoleIds(long[] roleIds) throws HotelerException {
        var statement = """
                SELECT DISTINCT(p.id), p.name, p.description FROM permissions p
                JOIN role_permissions rp ON rp.role_id  IN (:roleIds) AND rp.permission_id = p.id AND rp.deleted_at  IS NULl
                WHERE p.deleted_at IS NULL;
         """;
        var params = new MapSqlParameterSource();
        params.addValue("roleIds", Arrays.stream(roleIds).boxed().toList());
        return this.namedParameterJdbcTemplate.query(statement, params, this::rowsMapper);
    }

    private Permission rowsMapper(ResultSet rs, int i) throws SQLException {
        var permission = new Permission();
        permission.setId(rs.getLong("id"));
        permission.setName(rs.getString("name"));
        permission.setDescription(rs.getString("description"));

        return permission;
    }

    private Permission extractData(ResultSet rs) throws SQLException, DataAccessException {
        while (rs.next()) {
            var permission = new Permission();
            permission.setId(rs.getLong("id"));
            permission.setName(rs.getString("name"));
            permission.setDescription(rs.getString("description"));
            return permission;
        }

        return null;
    }

    public PermissionDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
