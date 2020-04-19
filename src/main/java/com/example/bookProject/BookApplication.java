package com.example.bookProject;

import com.example.bookProject.domain.entities.BookEntity;
import com.example.bookProject.domain.entities.UserEntity;
import com.example.bookProject.dto.RegistrationDTO;
import com.example.bookProject.services.BookService;
import com.example.bookProject.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(BookApplication.class, args);
        BookService bookService = applicationContext.getBean(BookService.class);
        UserService userService = applicationContext.getBean(UserService.class);

        BookEntity book1 = bookService.saveBook("NB132564", "Pollyanna", "Eleanor Porter");
        BookEntity book2 = bookService.saveBook("RE134525", "The Adventures of Tom Sawyer", "Mark Twain");
        BookEntity book3 = bookService.saveBook("LK134522", "The Adventures of Sherlock Holmes", "Arthur Conan Doyle");
        BookEntity book4 = bookService.saveBook("RE134528", "The White Company", "Arthur Conan Doyle");

        List<BookEntity> books = bookService.findAllBooks();
        System.out.println("Receiving all books");
        for (BookEntity book : books) {
            System.out.println(book.getId() + " " + book.getTitle() + " " + book.getIsbn() + " " + book.getAuthor());
        }

        BookEntity foundById = bookService.findById(3);
        System.out.println("Found by Id");
        System.out.println(foundById.getId() + " " + foundById.getTitle() + " " + foundById.getIsbn() + " " + foundById.getAuthor());

        List<BookEntity> foundByAuthor = bookService.findByAuthor("Arthur Conan Doyle");
        System.out.println("Found by Author");
        for (BookEntity book : foundByAuthor) {
            System.out.println(book.getId() + " " + book.getTitle() + " " + book.getIsbn() + " " + book.getAuthor());
        }

        RegistrationDTO adminDTO = new RegistrationDTO("admin", "12345678");
        String adminPassword = adminDTO.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(adminPassword);
        adminDTO.setPassword(encodedPassword);
        userService.registerAsAdmin(adminDTO);
    }
}
