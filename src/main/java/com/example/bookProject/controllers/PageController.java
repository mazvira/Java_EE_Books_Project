package com.example.bookProject.controllers;

import com.example.bookProject.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final BookService bookService;

    @RequestMapping({"/", ""})
    public String index(Model model) {
        if (bookService.findAllBooks().size() > 0) {
            model.addAttribute("books", bookService.findAllBooks());
        }
        return "index";
    }

    @RequestMapping(value = "/add-book" , method = RequestMethod.GET)
    public String book() {
        return "bookCreate";
    }
}
