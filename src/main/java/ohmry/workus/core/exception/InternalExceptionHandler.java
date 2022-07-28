package ohmry.workus.core.exception;

import ohmry.workus.core.ApiRequestDispatcher;
import ohmry.workus.core.ApiResponse;
import ohmry.workus.core.ApiStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestController
@RestControllerAdvice
public class InternalExceptionHandler implements ErrorController {
    @ExceptionHandler({ BadRequestException.class, IllegalRequestException.class })
    public ResponseEntity<ApiResponse> handleBadRequestException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(ApiStatus.INVALID_PARAMETER));
    }

    @RequestMapping("/error")
    public ResponseEntity<ApiResponse> handle (HttpServletRequest request) {
        HttpStatus httpStatus = (HttpStatus) request.getAttribute(ApiRequestDispatcher.HTTP_STATUS);
        ApiStatus apiStatus = (ApiStatus) request.getAttribute(ApiRequestDispatcher.API_STATUS);
        return ResponseEntity
                .status(httpStatus)
                .body(new ApiResponse(apiStatus));
    }
}
