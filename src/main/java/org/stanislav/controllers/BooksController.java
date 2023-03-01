package org.stanislav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stanislav.dao.BookDao;
import org.stanislav.models.Book;

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
        model.addAttribute("bookList", bookDao.readAll());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("book", bookDao.read(id));
        return "books/book";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/newBook";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") Book book) {
        bookDao.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("book", bookDao.read(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id,
                             @ModelAttribute("book") Book book) {
        bookDao.update(id,book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDao.delete(id);
        return "redirect:/books";
    }
}
