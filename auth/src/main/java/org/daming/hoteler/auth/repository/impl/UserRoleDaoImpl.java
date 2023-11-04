package org.daming.hoteler.auth.repository.impl;

import org.daming.hoteler.auth.repository.IUserRoleDao;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.Objects;

/**
 * @author gming001
 * @version 2023-11-04 16:43
 */
@Repository
public class UserRoleDaoImpl implements IUserRoleDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(int userId, int roleId) throws HotelerException {
        var statement = "INSERT INTO user_roles (user_id, role_id, create_dt, create_user) VALUE (?, ?, now(), 'system')";
        var keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            var ps = con.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public UserRoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
