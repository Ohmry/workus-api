package ohmry.workus.core.http;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 허가되지 않은 요청에 대해 인증되지 않은 요청으로 응답을 처리하는 클래스
 * @version 0.1
 */
@Component
public class RequestDenyInterceptor implements HandlerInterceptor {
}
