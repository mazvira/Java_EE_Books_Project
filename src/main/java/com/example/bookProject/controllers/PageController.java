package com.example.bookProject.controllers;

import com.example.bookProject.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final BookService bookService;

    @GetMapping({"/", ""})
    public String index(Model model) {
        if (bookService.findAllBooks().size() > 0) {
            model.addAttribute("books", bookService.findAllBooks());
        }
        return "index";
    }

    @GetMapping(value = "/add-book")
    public String book() {
        return "bookCreate";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/book/{id}")
    public String getBookPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("books", bookService.findById(id));
        return "booksList";
    }
}
