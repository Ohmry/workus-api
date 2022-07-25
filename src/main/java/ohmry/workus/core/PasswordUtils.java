package ohmry.workus.core;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encrypt(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public static boolean verify(String encryptedPassword, String plainPassword) {
        return encoder.matches(plainPassword, encryptedPassword);
    }
}
