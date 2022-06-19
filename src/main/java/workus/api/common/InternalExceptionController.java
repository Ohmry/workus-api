package workus.api.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * 컨트롤러나 서비스에서 발생하지 않는 Error로 인하여 /error 로 Redirect가 발생할 때,
 * 이 컨트롤러에서 Restful API 형태로 데이터를 변환하여 반환한다.
 */
@RestController
public class InternalExceptionController implements ErrorController {
    @GetMapping("/error")
    public ResponseEntity handle (HttpServletRequest request) {
        int status = Integer.parseInt(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
        return ResponseEntity.status(status).build();
    }
}
