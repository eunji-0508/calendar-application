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
        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // 로그인을 체크해야 하는 URL인지 검사함
        // whiteListURL에 포함된 경우 true 반환함 -> !true = false
        if (!isWhiteList(requestURI)) {
            // 로그인하지 않았을 경우 HttpSession이 null로 반환하도록 false로 지정함
            HttpSession session = httpRequest.getSession(false);

            // 로그인하지 않은 사용자일 겨우
            if (session == null || session.getAttribute("LOGIN_USER") == null) {
                throw new RuntimeException("로그인 해주시길 바랍니다.");
            }
        }
        // 다음 필터가 존재하지 않으면 Servlet -> Controller 호출함
        chain.doFilter(request, response);
    }

    // 로그인 여부를 확인하는 URL인지 체크하는 메서드
    private boolean isWhiteList(String requestURI) {
        // request URI가 whiteListURL에 포함되는지 확인함
        // 포함되면 true 반환함
        // 포함되지 않으면 false 반환함
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
