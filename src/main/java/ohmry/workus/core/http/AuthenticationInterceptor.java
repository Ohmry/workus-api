package ohmry.workus.core.http;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 요청에 대해 권한이 있는지 확인하고, 사전에 처리하는 클래스
 * @version 0.1
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
}
