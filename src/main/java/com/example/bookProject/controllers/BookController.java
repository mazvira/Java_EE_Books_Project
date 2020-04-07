package com.example.bookProject.controllers;

import com.example.bookProject.domain.entities.BookEntity;
import com.example.bookProject.dto.BookDTO;
import com.example.bookProject.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public ResponseEntity<BookDTO> addNewBook(@RequestBody final BookDTO bookDTO) {
        BookEntity book = bookService.saveBook(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor());
        return ResponseEntity.ok(toBookDTO(book));
    }

    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> booksList(@RequestParam(name = "titleOrIsbn", required = false) final String titleOrIsbn) {
        List<BookEntity> books;
        if (titleOrIsbn != null) {
            books = bookService.findByTitleOrIsbn(titleOrIsbn);
        } else {
            books = bookService.findAllBooks();
        }
        if (books != null) {
            List<BookDTO> allDTOBooks = new ArrayList<>();
            books.forEach(book -> {
                allDTOBooks.add(toBookDTO(book));
            });
            return ResponseEntity.ok().body(allDTOBooks);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/books/{titleOrIsbn}", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> findByTitleOrIsbn(@PathVariable("titleOrIsbn") final String titleOrIsbn) {
        List<BookEntity> books = bookService.findByTitleOrIsbn(titleOrIsbn);
        if (books != null) {
            List<BookDTO> booksDTO = new ArrayList<>();
            books.forEach(book -> {
                booksDTO.add(toBookDTO(book));
            });
            System.out.println("Here in bookService");
            return ResponseEntity.ok().body(booksDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<BookDTO> findById(@PathVariable("id") final Integer id) {
        BookEntity book = bookService.findById(id);
        if (book != null) {
            return ResponseEntity.ok(toBookDTO(book));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/booksByAuthor/{author}", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> findByAuthor(@PathVariable("author") final String author) {
        List<BookEntity> booksByAuthor = bookService.findByAuthor(author);
        if (booksByAuthor != null) {
            return ResponseEntity.notFound().build();
        } else {
            List<BookDTO> booksDTO = new ArrayList<>();
            booksByAuthor.forEach(book -> {
                booksDTO.add(toBookDTO(book));
            });
            return ResponseEntity.ok(booksDTO);
        }

    }

    public static BookDTO toBookDTO(BookEntity book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .build();
    }
}
