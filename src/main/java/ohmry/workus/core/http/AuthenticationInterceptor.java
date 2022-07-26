package ohmry.workus.core.http;

import ohmry.workus.core.ApiRequestDispatcher;
import ohmry.workus.core.ApiStatus;
import ohmry.workus.core.SessionKeys;
import ohmry.workus.core.UserSessionRequired;
import ohmry.workus.user.domain.User;
import ohmry.workus.user.model.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 요청에 대해 권한이 있는지 확인하고, 사전에 처리하는 클래스
 * @version 0.1
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;

        // 사용자의 세션정보가 필요한 Method에 대해서는 세션정보가 있는지 확인한다.
        // 세션정보가 없을 경우 Unauthorized 예외를 발생시키고,
        // 세션정보가 있을 경우 request 객체에 세션정보를 담아서 Method에 전달한다.
        UserSessionRequired required = method.getMethodAnnotation(UserSessionRequired.class);
        if (required != null) {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute(SessionKeys.USER_INFO);
            if (user == null) {
                request.setAttribute(ApiRequestDispatcher.HTTP_STATUS, HttpStatus.UNAUTHORIZED.value());
                request.setAttribute(ApiRequestDispatcher.API_STATUS, ApiStatus.UNAUTHORIZATION);
                request.getRequestDispatcher("/error").forward(request, response);
                return false;
            }
            request.setAttribute(ApiRequestDispatcher.USER_INFO, user);
        }
        return true;
    }
}
