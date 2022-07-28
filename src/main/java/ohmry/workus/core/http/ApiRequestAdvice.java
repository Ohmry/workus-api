package ohmry.workus.core.http;

import ohmry.workus.core.RequestObject;
import ohmry.workus.core.exception.BadRequestException;
import ohmry.workus.core.exception.IllegalRequestException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@RestControllerAdvice(annotations = RestController.class)
public class ApiRequestAdvice extends RequestBodyAdviceAdapter {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (!(body instanceof RequestObject)) {
            throw new IllegalRequestException();
        }

        RequestObject requestObject = (RequestObject) body;
        if (!requestObject.validate()) {
            throw new BadRequestException(body.getClass());
        }

        return body;
    }
}
