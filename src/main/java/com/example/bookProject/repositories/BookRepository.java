package com.example.bookProject.repositories;

import java.util.List;

import com.example.bookProject.domain.entities.BookEntity;
import com.example.bookProject.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findAllByAuthor(String author);

    @Query("SELECT book FROM BookEntity book "
            + "WHERE book.title LIKE :titleOrIsbn "
            + "OR book.isbn LIKE :titleOrIsbn")
    List<BookEntity> findAllWhereTitleLikeOrIsbnLike(@Param("titleOrIsbn") String titleOrIsbn);

    @Query("SELECT book FROM BookEntity book "
            + "JOIN book.users user "
            + "WHERE user.id = :user_id")
    List<BookEntity> findFavoritesForUser(@Param("user_id") final int userId);

    @Query("SELECT book FROM BookEntity book "
            + "JOIN book.users user "
            + "WHERE user.login = :login")
    List<BookEntity> findFavoritesForUser(@Param("login") final String login);

    boolean existsAllByIdAndUsersContains(final int bookId, final UserEntity user);
}
