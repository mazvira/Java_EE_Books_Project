package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @RequestMapping({"/", ""})
    public String index(Model model) {
        if (bookService.findAllBooks().size() > 0) {
            model.addAttribute("books", bookService.findAllBooks());
            return "bookList";
        } else {
            return "bookCreate";
        }
    }

    @RequestMapping({"/add-book"})
    public String book() {
        return "bookCreate";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addNewBook(@ModelAttribute Book book) {
        bookService.saveBook(book.getIsbn(), book.getTitle(), book.getAuthor());
        return "redirect:/books-list";
    }

    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "bookList";
    }

    @RequestMapping(value = "/book/{title}/{name}", method = RequestMethod.GET)
    public String findByTitleOrIsbn(@PathVariable("title") String title, @PathVariable("name") String name, Model model) {
        model.addAttribute("books", bookService.findByTitleOrIsbn(title, name));
        return "bookList";
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("books", bookService.findById(id));
        return "bookList";
    }

    @RequestMapping(value = "/booksByAuthor/{author}", method = RequestMethod.GET)
    public String findByAuthor(@PathVariable("author") String author, Model model) {
        model.addAttribute("books", bookService.findByAuthor(author));
        return "bookList";
    }

}