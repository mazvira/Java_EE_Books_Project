package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final EntityManager entityManager;

    @Transactional
    public Book createBook(String isbn, String title, String author) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        return entityManager.merge(book);
    }

    @Transactional
    public List<Book> getAllBooks() {
        return entityManager.createQuery("FROM Book", Book.class)
                .getResultList();
    }

    @Transactional
    public Book findById(Integer id) {
        return entityManager.createNamedQuery(Book.FIND_BY_ID, Book.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public List<Book> findByAuthor(String author) {
        return entityManager.createNamedQuery(Book.FIND_BY_AUTHOR, Book.class)
                .setParameter("author", author)
                .getResultList();
    }
}