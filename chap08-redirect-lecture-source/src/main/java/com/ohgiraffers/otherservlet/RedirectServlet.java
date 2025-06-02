package com.ohgiraffers.otherservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * 이 서블릿은 "/otherservlet"에서 sendRedirect()를 통해 리다이렉트된 요청을 처리한다.
 * 클라이언트는 이 서블릿을 직접 요청하게 되며, 브라우저 주소창에는 "/redirect"가 표시된다.
 */
@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 서버 측 로그: redirect 요청이 성공적으로 이 서블릿에 도달했음을 확인
        System.out.println("이 서블릿으로 resirect 성공 확인");

        // HTML 형식의 응답 본문 구성
        StringBuilder redirectText = new StringBuilder();
        redirectText.append("<!doctype html>\n")
            .append("<head>\n")
            .append("</head>\n")
            .append("<body>\n")
            .append("<h1>이 서블릿으로 redirect 성공!</h1>")
            .append("</body>\n")
            .append("</html>");

        // 응답의 콘텐츠 타입을 HTML로 설정
        response.setContentType("text/html");

        // 응답 출력 스트림을 통해 HTML을 클라이언트에 전송
        PrintWriter out = response.getWriter();
        out.print(redirectText);
        out.close();

        /*
         * sendRedirect() 방식은 서버가 클라이언트에게 "다른 주소로 이동하라"고 응답하는 방식이다.
         * 이때 클라이언트는 새 URL로 브라우저에서 직접 재요청을 수행하게 되며,
         * 브라우저 주소창에도 새로운 URL(/redirect)이 표시된다.
         *
         * 요청 객체(HttpServletRequest)는 요청마다 새로 생성되므로,
         * 이전 서블릿(예: /otherservlet)에서 설정한 request의 정보는 이 서블릿에서 사용할 수 없다.
         *
         * 즉, forward는 서버 내부에서 요청 객체를 공유하지만,
         * redirect는 클라이언트가 새로 요청을 보내는 구조이므로 요청 간의 데이터 공유가 불가능하다.
         *
         * 이러한 특징 때문에 redirect는 사용자 인증 후 새로고침에 의한 중복 처리를 방지하거나,
         * URL을 변경해 사용자에게 명확한 페이지 이동을 알릴 때 주로 사용된다.
         */
    }
}
