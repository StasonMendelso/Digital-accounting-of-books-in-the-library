package org.stanislav.forms.book;

import org.stanislav.forms.Form;
import org.stanislav.models.Book;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Stanislav Hlova
 */
public class AddBookForm implements Form<Book> {
    @NotEmpty(message = "Вкажіть назву книги.")
    @Size(min = 2, max = 100,
            message = "Длина назви книги повинна бути в межах 2-100.")
    private String title;
    @NotEmpty(message = "Вкажіть автора.")
    @Size(min = 2, max = 100,
            message = "Длина прізвища та імені автора повинна бути в межах 2-100.")
    @Pattern(regexp = "^[А-ЯЇЙЄІ\\w][А-яЇЙЄІїйєі'\\-\\w]+ [А-ЯЇЙЄІ\\w][А-яЇЙЄІїйєі'\\-\\w]+$",
            message = "Прізвище та ім'я повинен бути у наступному форматі \"Прізвище Ім\'я")
    private String author;
    @Min(value = 1500, message = "Рік видавництва книги повинен бути не меншим ніж 1500 рік.")
    @Max(value = 2023, message = "Рік видавництва книги повинен бути не більшим ніж 2023 рік.")
    private int year;

    public AddBookForm() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public Book getModel() {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setYear(year);
        return book;
    }
}
