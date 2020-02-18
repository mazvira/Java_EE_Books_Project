package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final BookRepository bookRepository;

    @RequestMapping({"/", ""})
    public String index() {
        return "index";
    }

    @RequestMapping({"/book"})
    public String book() {
        return "book";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addNewBook(@ModelAttribute Book book, Model model) {
        bookRepository.addBook(book);
        return "redirect:/books-list";
    }

    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model) {
        model.addAttribute("books", bookRepository.getBooks());
        /*List<Book> books = new ArrayList<>();
        books.add(new Book("my first book", "1234523523"));
        model.addAttribute("books", books);*/
        return "index";
    }
}
