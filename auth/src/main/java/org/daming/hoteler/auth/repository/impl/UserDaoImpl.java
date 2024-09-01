package org.daming.hoteler.auth.repository.impl;

import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.repository.IUserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
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
               (username, first_name, last_name, password, password_type, failed_login_attempts, account_non_locked, lock_time, create_dt, create_user, update_dt, update_user)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?,now(),'system', now(), 'system')""";
        this.jdbcTemplate.update(con -> {
            var ps = con.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPasswordType());
            ps.setInt(6, user.getFailedLoginAttempts());
            ps.setBoolean(7, user.isAccountNonLocked());
            ps.setObject(8, user.getLockTime());
            return ps;
        }, keyHolder);
        var id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        user.setId(id);
        return id;
    }

    @Override
    public User get(int id) {
        var statement = "SELECT id, username, first_name, last_name, password, password_type, failed_login_attempts, account_non_locked, lock_time FROM users WHERE id = ? AND deleted_at is null";
        return this.jdbcTemplate.query(statement, (rse) -> {
            while (rse.next()) {
                return getUser(rse);
            }
            return null;
        }, id);
    }

    @Override
    public User getUserByUsername(String username) {
        var statement = "SELECT id, username, first_name, last_name, password, password_type, failed_login_attempts, account_non_locked, lock_time FROM users WHERE username = ? AND deleted_at is null";
        return this.jdbcTemplate.query(statement, (rse) -> {
            while (rse.next()) {
                return getUser(rse);
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
                 failed_login_attempts = ?,
                 account_non_locked = ?,
                 lock_time = ?,
                 update_dt = now(),
                 update_user = 'system'
             where id = ?                
        """;
        this.jdbcTemplate.update(statement, user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getPasswordType(), user.getFailedLoginAttempts(), user.isAccountNonLocked(), user.getLockTime(),  user.getId());
    }

    @Override
    public void delete(int id) {
        var statement = "update users set deleted_at = now() where id = ?";
        this.jdbcTemplate.update(statement, id);
    }

    @Override
    public List<User> list() {
        var statement = "SELECT id, username, first_name, last_name, password, password_type, failed_login_attempts, account_non_locked, lock_time FROM users WHERE deleted_at is null";
        var users = this.jdbcTemplate.query(statement, (rs, i) -> getUser(rs));
        return users;
    }

    @Override
    public List<User> getUnlockUsers() {
        var statement = """
                SELECT 
                    id, username, first_name, last_name, password, password_type, failed_login_attempts, account_non_locked, lock_time 
                FROM users 
                WHERE account_non_locked = 0 
                    AND lock_time >= DATE_SUB(NOW(), INTERVAL 5 MINUTE)
                    AND deleted_at is null""";
        var users = this.jdbcTemplate.query(statement, (rs, i) -> getUser(rs));
        return users;
    }

    private User getUser(ResultSet rs) throws SQLException {
        var user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        user.setPasswordType(rs.getString("password_type"));
        user.setFailedLoginAttempts(rs.getInt("failed_login_attempts"));
        user.setAccountNonLocked(rs.getBoolean("account_non_locked"));
        user.setLockTime(rs.getObject("lock_time", LocalDateTime.class));
        return user;
    }

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }
}
