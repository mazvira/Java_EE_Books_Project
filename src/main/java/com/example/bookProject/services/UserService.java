package com.example.bookProject.services;

import com.example.bookProject.domain.entities.BookEntity;
import com.example.bookProject.domain.entities.UserEntity;
import com.example.bookProject.domain.type.Permission;
import com.example.bookProject.dto.RegistrationDTO;
import com.example.bookProject.repositories.BookRepository;
import com.example.bookProject.repositories.PermissionRepository;
import com.example.bookProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PermissionRepository permissionRepository;

    public UserEntity save(final UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> findByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    public boolean loginExists(final String login) {
        return userRepository.existsAllByLogin(login);
    }

    public UserEntity registerAsUser(final RegistrationDTO user) {

        return save(UserEntity.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .permissions(Collections.singletonList(permissionRepository.findByPermission(Permission.USER)))
                .build());
    }

    public UserEntity registerAsAdmin(final RegistrationDTO user) {

        return save(UserEntity.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .permissions(Collections.singletonList(permissionRepository.findByPermission(Permission.ADMIN)))
                .build());
    }

    public void addToFavorites(final BookEntity book, final String userLogin) {
        Optional<UserEntity> optionalUser = findByLogin(userLogin);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<BookEntity> favorites = findFavoriteBooks(user.getId());
            favorites.add(book);
            user.setFavoriteBooks(favorites);
            save(user);
        }
    }

    public void deleteFromFavorites(final BookEntity book,
                                    final String userLogin) {
        Optional<UserEntity> optionalUser = findByLogin(userLogin);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<BookEntity> favorites = findFavoriteBooks(user.getId());
            boolean res = favorites.remove(book);
            user.setFavoriteBooks(favorites);
            save(user);
        }
    }

    public List<BookEntity> findFavoriteBooks(final int userId) {
        return bookRepository.findFavoritesForUser(userId);
    }

    public List<BookEntity> findFavoriteBooks(String login) {
        return bookRepository.findFavoritesForUser(login);
    }

    public boolean isFavoriteBook(int bookId, String login) {
        Optional<UserEntity> user = findByLogin(login);
        return user.filter(u -> bookRepository.existsAllByIdAndUsersContains(bookId, u)).isPresent();
    }
}
