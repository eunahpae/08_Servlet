package com.ohgiraffers.forward;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/* "/forward" 서블릿에서 forward 방식으로 전달된 요청을 처리하는 서블릿
 * 클라이언트는 이 서블릿을 직접 요청하지 않았지만, 서버 내부적으로 요청이 위임되어 실행된다.
 */
@WebServlet("/print")
public class PrintLoginSuccessServlet extends HttpServlet {

    /*
     * forward 방식으로 요청을 받은 경우에도 원래 요청 방식(POST)이 유지되므로
     * doPost() 메서드가 실행된다.
     *
     * "/forward" 서블릿에서 request.setAttribute("userId", userId)로 전달된 데이터를
     * request.getAttribute()를 통해 꺼내 사용할 수 있다.
     *
     * forward는 요청 객체(request)와 응답 객체(response)를 복사하지 않고 그대로 전달하므로,
     * 요청 스코프의 데이터(userId 등)를 공유할 수 있다.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 이전 서블릿에서 request에 저장한 "userId" 값을 꺼낸다.
        String userId = (String) request.getAttribute("userId");

        // HTML 응답 내용을 문자열로 구성
        StringBuilder responseText = new StringBuilder();
        responseText.append("<!doctype html>\n")
            .append("<html>\n")
            .append("<head>\n")
            .append("</head>\n")
            .append("<body>\n")
            .append("<h3 align=\"center\">")
            .append(userId)
            .append(" 님 환영합니다.</h3>")
            .append("</body>\n")
            .append("</html>");

        // 응답 데이터의 콘텐츠 타입을 HTML로 설정
        response.setContentType("text/html");

        // 응답 스트림을 통해 클라이언트에게 HTML 전송
        PrintWriter out = response.getWriter();
        out.print(responseText);
        out.close();

        /*
         * forward 방식은 서버 내부에서 요청을 전달하는 방식으로,
         * 클라이언트는 현재 실행 중인 서블릿이 변경되었음을 인지할 수 없다.
         * 즉, 브라우저의 주소(URL)은 여전히 "/forward"로 표시된다.
         *
         * forward는 동일한 request와 response 객체를 공유하므로 데이터 전달이 간편하다.
         * 단점으로는 POST 요청 후 새로고침( = 재요청) 시 중복 요청이 발생할 수 있으므로,
         * 데이터 삽입(insert)보다는 조회(read) 목적에 주로 사용된다.
         *
         * 기본적으로 지역 변수는 메서드 내에서만 유효하므로,
         * 서로 다른 서블릿 간 데이터를 공유하려면 request, session, application 스코프를 사용해야 한다.
         * 이 예제에서는 request 스코프를 이용하여 데이터를 전달하였다.
         */
    }
}
