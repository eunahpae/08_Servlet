package com.ohgiraffers.forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 클라이언트로부터 전달받은 요청 정보를 다른 서블릿으로 전달(forward)하는 역할을 수행하는 서블릿 */
@WebServlet("/forward")
public class ReceiveInformationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 클라이언트가 보낸 요청의 문자 인코딩을 UTF-8로 설정하여 한글 등이 깨지지 않도록 처리
        request.setCharacterEncoding("UTF-8");

        // 클라이언트가 form 등을 통해 전송한 userId, password 값을 파라미터로 받음
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        // 서버 콘솔에 전달받은 값 출력 (디버깅용 로그)
        System.out.println("userId = " + userId);
        System.out.println("password = " + password);

        /*
         * 요청을 다른 서블릿에게 전달하기 전에, 필요한 데이터를 request 범위에 저장한다.
         * setAttribute() 메서드는 request 객체에 데이터를 key-value 형태로 저장하는 기능이다.
         * 이후 전달받은 서블릿에서는 getAttribute("userId")로 해당 데이터를 꺼내어 사용할 수 있다.
         */
        request.setAttribute("userId", userId);

        /*
         * RequestDispatcher는 다른 자원(서블릿, JSP 등)으로 요청을 위임할 때 사용하는 객체이다.
         * getRequestDispatcher("print")는 현재 요청을 "/print" 경로의 자원으로 전달할 준비를 함.
         * forward(request, response)를 호출하면 현재 서블릿의 작업을 중단하고
         * 지정한 서블릿(또는 JSP)이 나머지 요청 처리를 이어받는다.
         *
         * forward는 클라이언트에게는 하나의 요청처럼 보이며, 브라우저 주소는 변경되지 않는다.
         */
        RequestDispatcher rd = request.getRequestDispatcher("print");
        rd.forward(request, response);
    }
}
