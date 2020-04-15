package com.example.bookProject.controllers;

import com.example.bookProject.domain.entities.BookEntity;
import com.example.bookProject.dto.BookDTO;
import com.example.bookProject.dto.RegistrationDTO;
import com.example.bookProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.example.bookProject.controllers.BookController.toBookDTO;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody final RegistrationDTO user) {
        if (userService.loginExists(user.getLogin())) {
            return new ResponseEntity<>("Login already exists",
                    HttpStatus.FORBIDDEN);
        }
        String userPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userPassword);
        user.setPassword(encodedPassword);
        userService.registerAsUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/books/favorite")
    public ResponseEntity<List<BookDTO>> getFavorites(final Principal principal) {
        System.out.println("Books favorites ");
        String login = principal.getName();
        List<BookEntity> favoriteBooks = userService.findFavoriteBooks(login);
        List<BookDTO> favoriteDTOBooks = new ArrayList<>();
        favoriteBooks.forEach(book -> {
            favoriteDTOBooks.add(toBookDTO(book));
        });
        return ResponseEntity.ok(favoriteDTOBooks);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/books/favorite/add")
    public ResponseEntity addToFavoriteBooks(final Principal principal, @RequestBody final BookEntity book) {
        String login = principal.getName();
        userService.addToFavorites(book, login);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/books/favorite/delete")
    public ResponseEntity deleteFromFavoriteBooks(
            final Principal principal, @RequestBody final BookEntity book) {
        String login = principal.getName();
        userService.deleteFromFavorites(book, login);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/books/isFavorite/{id}")
    public ResponseEntity<Boolean> isFavoriteBook(
            final Principal principal, @PathVariable("id") final int bookId) {
        if (principal == null) {
            return ok(false);
        }
        String login = principal.getName();
        return ok(userService.isFavoriteBook(bookId, login));
    }
}
