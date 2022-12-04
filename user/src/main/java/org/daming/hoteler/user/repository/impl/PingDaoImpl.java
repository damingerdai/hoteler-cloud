package org.daming.hoteler.user.repository.impl;

import org.daming.hoteler.user.repository.IPingDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author gming001
 * @version 2022-12-04 19:05
 */
@Repository
public class PingDaoImpl implements IPingDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public String ping() {
        return this.jdbcTemplate.queryForObject("SELECT 'pong'", String.class);
    }

    public PingDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }
}
