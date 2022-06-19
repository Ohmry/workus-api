package workus.api.sign;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import workus.api.user.UserRepository;

@Service
public class SignService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public SignService (PasswordEncoder passwordEncoder,
                        UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
}
