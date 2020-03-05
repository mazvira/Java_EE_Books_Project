package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries(value = {
        @NamedQuery(query = "SELECT book FROM Book book WHERE book.id = :id", name = Book.FIND_BY_ID),
        @NamedQuery(query = "SELECT book FROM Book book WHERE book.author = :author", name = Book.FIND_BY_AUTHOR),
})
class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;


    public static final String FIND_BY_ID = "Book.FIND_BY_ID";
    public static final String FIND_BY_AUTHOR = "Book.FIND_BY_AUTHOR";
}
