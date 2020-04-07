package com.example.bookProject.services;

import com.example.bookProject.domain.entities.BookEntity;
import com.example.bookProject.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public BookEntity saveBook(String isbn, String title, String author) {
        BookEntity book = new BookEntity();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Transactional
    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public BookEntity findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<BookEntity> findByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Transactional
    public List<BookEntity> findByTitleOrIsbn(String titleOrIsbn) {
        return bookRepository.findAllWhereTitleLikeOrIsbnLike('%' + titleOrIsbn + '%');
    }
}
