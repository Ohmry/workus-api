package ohmry.workus.core.exception;

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
    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<ApiResponse> handleIllegalRequestException(IllegalRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(ApiStatus.INVALID_PARAMETER));
    }
    @RequestMapping("/error")
    public ResponseEntity<ApiResponse> handle (HttpServletRequest request) {
        Object requestUriObject = request.getAttribute("requestUri");
        Object apiStatusObject = request.getAttribute("apiStatus");

        String requestUri = requestUriObject == null ? "/error" : requestUriObject.toString();
        ApiStatus apiStatus = apiStatusObject == null ? ApiStatus.UNAUTHORIZATION : ApiStatus.valueOf(apiStatusObject.toString());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .header("Location", requestUri)
                .body(new ApiResponse(apiStatus));
    }
}
