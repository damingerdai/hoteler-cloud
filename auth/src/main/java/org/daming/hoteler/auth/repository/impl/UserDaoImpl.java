package org.daming.hoteler.auth.repository.impl;

import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.repository.IUserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * @author gming001
 * @version 2022-12-08 12:13
 */
@Repository
public class UserDaoImpl implements IUserDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(User user) {
        var keyHolder = new GeneratedKeyHolder();
        var statement = """
            INSERT INTO users
               (username, first_name, last_name, password, password_type, create_dt, create_user, update_dt, update_user)
            VALUES (?, ?, ?, ?, ?, now(),'system', now(), 'system')""";
        this.jdbcTemplate.update(con -> {
            var ps = con.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPasswordType());
            return ps;
        }, keyHolder);
        var id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        user.setId(id);
        return id;
    }

    @Override
    public User get(int id) {
        var statement = "SELECT id, username, first_name, last_name, password, password_type FROM users WHERE id = ?";
        return this.jdbcTemplate.query(statement, (rse) -> {
            while (rse.next()) {
                var user = new User();
                user.setId(rse.getInt("id"));
                user.setUsername(rse.getString("username"));
                user.setFirstName(rse.getString("first_name"));
                user.setLastName(rse.getString("last_name"));
                user.setPassword(rse.getString("password"));
                user.setPasswordType(rse.getString("password_type"));
                return user;
            }
            return null;
        }, id);
    }

    @Override
    public User getUserByUsername(String username) {
        var statement = "SELECT id, username, first_name, last_name, password, password_type FROM users WHERE username = ?";
        return this.jdbcTemplate.query(statement, (rse) -> {
            while (rse.next()) {
                var user = new User();
                user.setId(rse.getInt("id"));
                user.setUsername(rse.getString("username"));
                user.setFirstName(rse.getString("first_name"));
                user.setLastName(rse.getString("last_name"));
                user.setPassword(rse.getString("password"));
                user.setPasswordType(rse.getString("password_type"));
                return user;
            }
            return null;
        }, username);
    }

    @Override
    public void update(User user) {
        var statement = """
             update users
             set username = ?,
                 first_name = ?,
                 last_name = ?,
                 password = ?,
                 password_type = ?,
                 update_dt = now(),
                 update_user = 'system'
             where id = ?
                 
        """;
        this.jdbcTemplate.update(statement, user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getPasswordType(), user.getId());
    }

    @Override
    public void delete(int id) {
        var statement = "update users set deleted_at = now() where id = ?";
        this.jdbcTemplate.update(statement, id);
    }

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }
}
