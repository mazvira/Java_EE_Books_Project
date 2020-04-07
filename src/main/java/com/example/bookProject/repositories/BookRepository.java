package com.example.bookProject.repositories;

import java.util.List;

import com.example.bookProject.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findAllByAuthor(String author);

    @Query("SELECT book FROM BookEntity book "
            + "WHERE book.title LIKE :titleOrIsbn "
            + "OR book.isbn LIKE :titleOrIsbn")
    List<BookEntity> findAllWhereTitleLikeOrIsbnLike(@Param("titleOrIsbn") String titleOrIsbn);
}