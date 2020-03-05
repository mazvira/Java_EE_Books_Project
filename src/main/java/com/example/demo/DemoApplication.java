package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        BookService bookService = applicationContext.getBean(BookService.class);

      /*  Book book1 = bookService.createBook("NB132564", "Pollyanna", "Eleanor Porter");
        Book book2 = bookService.createBook("RE134525", "The Adventures of Tom Sawyer", "Mark Twain");
        Book book3 = bookService.createBook("LK134522", "The Adventures of Sherlock Holmes", "Arthur Conan Doyle");
        Book book4 = bookService.createBook("RE134528", "The White Company", "Arthur Conan Doyle");

        List<Book> books = bookService.getAllBooks();
        System.out.println("Receiving all books");
        for (Book book : books) {
            System.out.println(book.getId() + " " + book.getTitle() + " " + book.getIsbn() + " " + book.getAuthor());
        }

        Book foundById = bookService.findById(1);
        System.out.println("Found by Id");
        System.out.println(foundById.getId() + " " + foundById.getTitle() + " " + foundById.getIsbn() + " " + foundById.getAuthor());

        List<Book> foundByAuthor = bookService.findByAuthor("Arthur Conan Doyle");
        System.out.println("Found by Author");
        for (Book book : foundByAuthor) {
            System.out.println(book.getId() + " " + book.getTitle() + " " + book.getIsbn() + " " + book.getAuthor());
        }*/

    }
}
