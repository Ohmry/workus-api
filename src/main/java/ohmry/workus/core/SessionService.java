package ohmry.workus.core;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionService {
    public static final int SESSION_INTERVAL_MINUTE = 30;
    
    public Object get(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        return session.getAttribute(key);
    }

    public void set(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(true);
        session.setAttribute(key, value);
        session.setMaxInactiveInterval(SESSION_INTERVAL_MINUTE);
    }

    public void expire(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
    }
}
