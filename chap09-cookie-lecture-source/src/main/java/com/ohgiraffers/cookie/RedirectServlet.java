package com.ohgiraffers.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 쿠키를 클라이언트로부터 받아 출력하는 서블릿 "/redirect" 경로로 요청이 들어왔을 때 실행된다.
 */
@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        System.out.println("redirect servlet 요청 확인");

        /*
         * 쿠키를 요청에서 사용하는 절차:
         * 1. HttpServletRequest 객체에서 쿠키 배열을 가져온다. (request.getCookies())
         * 2. 반복문을 통해 각 쿠키의 이름(getName)과 값(getValue)을 확인하거나 처리한다.
         *
         * 주의:
         * - 쿠키는 클라이언트가 서버에 매 요청마다 함께 전송하는 방식이므로,
         *   쿠키의 크기와 보안성을 항상 고려해야 한다.
         */

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("cookie = " + cookie);
                System.out.println("key = " + cookie.getName());
                System.out.println("value = " + cookie.getValue());
            }
        } else {
            System.out.println("클라이언트로부터 전달된 쿠키가 없습니다.");
        }
    }

    /*
     * [쿠키와 보안]
     * - 쿠키는 클라이언트 측(브라우저)에 텍스트 파일 형태로 저장된다.
     * - 이로 인해 쿠키는 사용자가 직접 접근하거나 수정할 수 있으며,
     *   특히 공용 PC 에서는 개인정보 유출의 위험이 있다.
     *
     * [쿠키 vs 세션]
     * - 쿠키: 클라이언트에 저장, 서버 부하 적음, 보안에 상대적으로 취약
     * - 세션: 서버에 저장, 보안 우수, 서버 자원 소모
     *
     * ※ 민감한 개인정보(예: 주민등록번호, 비밀번호 등)는 쿠키가 아닌 세션 또는 별도의 보안 저장소 사용 권장
     */
}
