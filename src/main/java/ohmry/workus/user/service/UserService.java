package ohmry.workus.user.service;

import ohmry.workus.core.PasswordUtils;
import ohmry.workus.user.domain.User;
import ohmry.workus.user.exception.EmailAlreadyExistsException;
import ohmry.workus.user.exception.InvalidUserCredentialException;
import ohmry.workus.user.exception.UserNotFoundException;
import ohmry.workus.user.model.UserInfo;
import ohmry.workus.user.model.UserInfoWithCredential;
import ohmry.workus.user.model.UserUpdateRequest;
import ohmry.workus.user.repository.UserJpaRepository;
import ohmry.workus.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userRepository = userJpaRepository;
    }

    @Transactional
    public User createUser(String email, String password, String name) {
        if (existsEmailInDatabase(email)) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    public boolean existsEmailInDatabase(String email) {
        return userRepository.existsUserByEmail(email);
    }


//    private final UserJpaRepository userJpaRepository;
//
//    public UserService(UserJpaRepository userJpaRepository) {
//        this.userJpaRepository = userJpaRepository;
//    }
//
//    @Transactional
//    public User createUser(String email, String password, String name) {
//        if (userJpaRepository.existsByEmail(email)) {
//            throw new EmailAlreadyExistsException(email);
//        }
//        User user = new User(email, PasswordUtils.encrypt(password), name);
//        userJpaRepository.save(user);
//        return user;
//    }
//
//    public User getUserByEmail(String email) {
//        return userJpaRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
//    }
//
//    public User getUser(Long id) {
//        return userJpaRepository.findById(id).orElseThrow(UserNotFoundException::new);
//    }
//
//
//
//    public void verify(UserInfoWithCredential credential, String password) {
//        if (!PasswordUtils.verify(credential.password, password)) {
//            throw new InvalidUserCredentialException();
//        }
//    }
//
//    @Transactional
//    public UserInfo updateUser(Long id, UserUpdateRequest request) {
//        User user = userJpaRepository.findById(id)
//                .orElseThrow(UserNotFoundException::new);
//        user.updateName(request.name);
//        return UserInfo.valueOf(user);
//    }
}
