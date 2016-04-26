package ru.itis.inform.RequestReproduction.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.activation.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by Galiullin_ko on 09.04.16.
 */
public class SQLQueryExecutorImpl implements SQLQueryExecutor {
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public SQLQueryExecutorImpl(DataSource dataSource) {
        NamedParameterJdbcDaoSupport jdbcDaoSupport = new NamedParameterJdbcDaoSupport();
        jdbcDaoSupport.setDataSource((javax.sql.DataSource) dataSource);
        this.jdbcTemplate = jdbcDaoSupport.getJdbcTemplate();
        this.namedParameterJdbcTemplate = jdbcDaoSupport.getNamedParameterJdbcTemplate();
    }

    public  <T> List<T> queryForObjects(String sql, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public <T> List<T> queryForObjects(String sql, Map<String, Object> paramMap, RowMapper<T> rowMapper) {
        return namedParameterJdbcTemplate.query(sql, paramMap, rowMapper);
    }

    public <T> T queryForObject(String sql, Map<String, Object> paramMap, RowMapper<T> rowMapper) {
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, rowMapper);
    }

    public void updateQuery(String sql, Map<String, Object> paramMap) {
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public int queryForInt(String sql, Map<String, Object> paramMap) {
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class); //check jdbc version
    }

    @Override
    public int queryForInt(String sql) {
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
