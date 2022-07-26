package ohmry.workus.user.service;

import ohmry.workus.core.PasswordUtils;
import ohmry.workus.user.domain.User;
import ohmry.workus.user.exception.EmailAlreadyExistsException;
import ohmry.workus.user.exception.InvalidUserCredentialException;
import ohmry.workus.user.exception.UserNotFoundException;
import ohmry.workus.user.model.UserInfo;
import ohmry.workus.user.model.UserInfoWithCredential;
import ohmry.workus.user.model.UserUpdateRequest;
import ohmry.workus.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(String email, String password, String name) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
        User user = new User(email, PasswordUtils.encrypt(password), name);
        userRepository.save(user);
        return user;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }



    public void verify(UserInfoWithCredential credential, String password) {
        if (!PasswordUtils.verify(credential.password, password)) {
            throw new InvalidUserCredentialException();
        }
    }

    @Transactional
    public UserInfo updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.updateName(request.name);
        return UserInfo.valueOf(user);
    }
}
