package ohmry.workus.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final int SESSION_INTERVAL_MINUTE = 30;

    public static Object get(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        return session.getAttribute(key);
    }

    public static void set(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(true);
        session.setAttribute(key, value);
        session.setMaxInactiveInterval(SESSION_INTERVAL_MINUTE);
    }

    public static void expire(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
    }
}
