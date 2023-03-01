package org.stanislav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stanislav.dao.BookDao;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;

    @Autowired
    public BooksController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String showBooks(Model model) {
        model.addAttribute("bookList",bookDao.readAll());
        return "books/books";
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id,
                           Model model){
        model.addAttribute("book", bookDao.read(id));
        return "books/book";
    }
}
