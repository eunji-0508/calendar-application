package org.example.calendarapplication.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {
    // 인증을 하지 않아도 될 URL Path 배열
    private static final String[] WHITE_LIST = {"/", "/signup", "/login"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            // 로그인하지 않은 사용자일 겨우
            if (session == null || session.getAttribute("LOGIN_USER") == null) {
                throw new RuntimeException("로그인 해주시길 바랍니다.");
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
