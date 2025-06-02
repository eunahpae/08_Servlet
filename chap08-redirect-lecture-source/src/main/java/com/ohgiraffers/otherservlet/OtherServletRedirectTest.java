package com.ohgiraffers.otherservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 이 서블릿은 "/otherservlet" 경로로 들어온 GET 요청을 처리하며,
 * 처리 후 클라이언트를 "/redirect" 경로로 리다이렉트(재요청)시키는 역할을 한다.
 */
@WebServlet("/otherservlet")
public class OtherServletRedirectTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 서버 측 로그: 정상적으로 GET 요청을 수신했음을 출력
        System.out.println("GET 요청 정상 수락");

        /*
         * 클라이언트에게 "redirect" 경로로 이동하라는 응답을 보낸다.
         * response.sendRedirect("redirect")는 클라이언트에게 302 상태 코드와 함께
         * Location 헤더를 포함하여 브라우저가 지정된 경로로 새롭게 요청하도록 유도한다.
         *
         * 이 방식은 클라이언트가 실제로 두 번 요청하게 되므로 URL이 변경되며,
         * 최초 요청과 이후 요청은 서로 다른 요청으로 간주된다.
         * 따라서 request 객체의 데이터는 유지되지 않고, 필요 시 session 등을 이용해야 한다.
         *
         * 보안이나 상태 변경 후 새로 고침 중복 방지를 위해 주로 사용된다.
         */
        response.sendRedirect("redirect");
    }
}
