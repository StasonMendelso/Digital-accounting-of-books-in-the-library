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

    public List<Book> readAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book read(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, \"year\") VALUES(?,?,?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET author=?,title=?,\"year\"=? WHERE id=?",
                book.getAuthor(),
                book.getTitle(),
                book.getYear(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",
                id);
    }
}
