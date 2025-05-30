package com.ohgiraffers.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 예외 발생 시 에러 페이지로 포워딩되는 서블릿
 * web.xml 또는 어노테이션으로 지정한 error-page의 location 으로 매핑됨
 */
@WebServlet("/showErrorPage")
public class ExceptionHandlerServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    /* 에러 처리 서블릿에서는 jakarta.servlet.error.XXX 이름의 속성을 통해 예외 정보를 전달받을 수 있다.
     * 대표적인 속성은 다음과 같다:
     * - jakarta.servlet.error.status_code : HTTP 상태 코드
     * - jakarta.servlet.error.message : 예외 메시지 또는 상태 메시지
     * - jakarta.servlet.error.exception_type : 예외 타입 (Class)
     * - jakarta.servlet.error.exception : 실제 Exception 객체
     * - jakarta.servlet.error.request_uri : 예외가 발생한 URI
     * - jakarta.servlet.error.servlet_name : 예외가 발생한 서블릿 이름
     */

    // 전달된 모든 request 속성 이름을 출력 (디버깅용)
    Enumeration<String> attrName = request.getAttributeNames();
    while (attrName.hasMoreElements()) {
      System.out.println(attrName.nextElement());
    }

    // HTTP 상태 코드와 메시지를 request 속성에서 꺼내옴
    Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
    String message = (String) request.getAttribute("jakarta.servlet.error.message");

    System.out.println("statusCode = " + statusCode);
    System.out.println("message = " + message);

    // 에러 정보를 포함한 HTML 페이지를 동적으로 생성
    StringBuilder errorPage = new StringBuilder();
    errorPage.append("<!doctype html>\n")
        .append("<html>\n")
        .append("<head>\n")
        .append("<meta charset='UTF-8'>\n")
        .append("<title>오류 발생</title>\n")
        .append("</head>\n")
        .append("<body>\n")
        .append("<h1>")
        .append(statusCode)
        .append(" - ")
        .append(message)
        .append("</h1>\n")
        .append("</body>\n")
        .append("</html>\n");

    // 응답의 content-type을 HTML로 설정하고 페이지 전송
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.print(errorPage);
    out.close();
  }
}
