package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthor(String author);

    @Query("SELECT book FROM Book book "
            + "WHERE book.title LIKE :title "
            + "OR book.isbn LIKE :isbn")
    List<Book> findAllWhereTitleLikeOrIsbnLike(@Param("title") String title, @Param("isbn") String isbn);
}