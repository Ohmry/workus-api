package workus.core;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Cookie와 관련된 함수를 갖는 클래스
 */
public class CookieUtils {
    /**
     * HttpServletRequest 객체에서 cookieName에 해당하는 값을 가져온다.
     * @param request HttpServletRequest 객체
     * @param cookieName 가져올 Cookie의 이름
     * @return Cookie 값. 값이 없을 경우 Null을 반환한다.
     */
    public static String getCookieValue (HttpServletRequest request, String cookieName) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst()
                .orElse(null)
                .getValue();
    }
}
