package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book saveBook(String isbn, String title, String author) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Transactional
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Book> findByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Transactional
    public List<Book> findByTitleOrIsbn(String title, String isbn) {
        return bookRepository.findAllWhereTitleLikeOrIsbnLike('%' + title + '%', '%' + isbn + '%');
    }
}
