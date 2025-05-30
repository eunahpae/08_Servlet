package com.ohgiraffers.section01.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 클라이언트의 GET 요청을 처리하고 HTML 응답을 동적으로 생성하여 출력하는 서블릿 클래스
 */
@WebServlet("/response")  // 해당 서블릿은 "/response" URL로 매핑됨
public class ResponseTestServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    /*
     * 서블릿의 역할은 크게 세 가지로 나눌 수 있다.
     * 1. 요청 받기: 클라이언트가 보낸 요청을 처리 (GET/POST 방식 등)
     * 2. 비즈니스 로직 처리: DB 처리, 데이터 가공 등 CRUD 핵심 처리 수행
     * 3. 응답 하기: 클라이언트에게 결과를 HTML 또는 JSON 등으로 전달
     */

    /*
     * 응답을 클라이언트(브라우저)로 전달하기 위해 HttpServletResponse 객체를 사용한다.
     * getWriter() 메서드는 문자 기반 출력 스트림인 PrintWriter 객체를 반환한다.
     * 이 스트림을 통해 HTML 등의 문자열을 응답 본문에 작성할 수 있다.
     */
    PrintWriter out = response.getWriter();

    /*
     * StringBuilder 를 사용하여 HTML 구조의 문자열을 동적으로 생성한다.
     * 이렇게 생성된 문자열은 결국 클라이언트의 브라우저에서 HTML 페이지로 렌더링된다.
     */
    StringBuilder responseBuilder = new StringBuilder();
    responseBuilder.append("<!doctype html>\n")
        .append("<html>\n")
        .append("<head>\n")
        .append("    <meta charset='UTF-8'>\n")  // 인코딩 명시 (추천)
        .append("    <title>Servlet Response</title>\n")
        .append("</head>\n")
        .append("<body>\n")
        .append("    <h1>안녕 servlet response</h1>\n")
        .append("</body>\n")
        .append("</html>");

    /*
     * 응답의 Content-Type을 "text/html"로 설정하여
     * 브라우저가 HTML 형식으로 내용을 해석하도록 지정한다.
     * 문자 인코딩도 UTF-8로 설정했었으나 업데이트 되면서 해당 설정은 생략 가능하다.
     */
    // response.setContentType("text/html; charset=UTF-8");
    response.setContentType("text/html");

    // 생성한 HTML 응답을 출력 스트림에 작성하여 클라이언트로 전송
    out.print(responseBuilder);
    out.flush();  // 버퍼에 있는 데이터를 강제로 출력
    out.close();  // 스트림 닫기
  }
}
