package com.ohgiraffers.cookie;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 사용자가 입력한 firstName과 lastName을 쿠키에 저장하여 클라이언트에 전달하는 서블릿
 */
@WebServlet("/cookie")
public class CookieHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 클라이언트가 보낸 이름 정보 수신
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);

        /*
         * 쿠키를 사용하는 일반적인 절차는 다음과 같다:
         * 1. 쿠키 객체를 생성한다. (이름과 값을 지정)
         * 2. 쿠키의 만료 시간을 설정한다. (초 단위, 생략 시 브라우저 종료 시까지 유지)
         * 3. 응답 객체에 쿠키를 추가한다.
         * 4. 클라이언트로 응답을 전송한다.
         *
         * 주의사항:
         * - 쿠키 이름에는 ASCII 문자만 사용 가능하며, 공백 및 일부 특수문자([ ] ( ) = , " \ ? @ : ;)는 사용할 수 없다.
         * - 쿠키 이름은 한 번 설정하면 변경할 수 없으며, 동일 이름의 쿠키를 다시 추가하면 기존 값을 덮어쓴다.
         */

        // 1. 쿠키 생성
        Cookie firstNameCookie = new Cookie("firstName", firstName);
        Cookie lastNameCookie = new Cookie("lastName", lastName);

        // 2. 쿠키 만료 시간 설정 (60초 * 60분 * 24시간 = 1일)
        firstNameCookie.setMaxAge(60 * 60 * 24);
        lastNameCookie.setMaxAge(60 * 60 * 24);

        // 3. 응답에 쿠키 추가
        response.addCookie(firstNameCookie);
        response.addCookie(lastNameCookie);

        // 4. 리다이렉트 처리 (다른 URL로 요청 전달)
        response.sendRedirect("redirect");
    }
}
