package org.stanislav.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.stanislav.models.Book;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> readByOwnerId(int ownerId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{ownerId}, new BeanPropertyRowMapper<>(Book.class));
    }
}
