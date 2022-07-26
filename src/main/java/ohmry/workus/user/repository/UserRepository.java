package ohmry.workus.user.repository;

import ohmry.workus.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsUserById(Long id);
    boolean existsUserByEmail(String email);
    User save(User user);
    void delete(User user);
}
