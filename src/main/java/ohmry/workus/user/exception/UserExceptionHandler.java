package ohmry.workus.user.exception;

import ohmry.workus.core.ApiResponse;
import ohmry.workus.core.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleEmailAlreadyExistsException() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(ApiStatus.EMAIL_IS_ALREADY_EXISTS));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(ApiStatus.USER_NOT_FOUND));
    }
    @ExceptionHandler(InvalidUserCredentialException.class)
    public ResponseEntity<ApiResponse> handleInvalidUserCredentialException() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse((ApiStatus.INVALID_USER_CREDENTIAL)));
    }
}
