package com.ohgiraffers.headers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 클라이언트가 요청 시 전송한 HTTP 요청 헤더를 서버 콘솔에 출력하는 서블릿
 */
@WebServlet("/headers")
public class RequestHeaderPrintServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    /*
     * 클라이언트가 서버로 요청을 보낼 때, 다양한 부가 정보가 '요청 헤더(Request Header)'에 담겨 전송된다.
     * 이 메서드는 요청 시 함께 전달된 모든 요청 헤더의 이름과 값을 서버 콘솔에 출력한다.
     */

    /*
     * HTTP 헤더는 크게 다음과 같은 4가지 카테고리로 분류된다:
     *
     * 1. General Header
     *    - 요청/응답 모두에 적용될 수 있으나, 메시지 본문(body)와는 직접적인 관련이 없는 일반 정보 포함
     *    - 예: Cache-Control, Connection, Date
     *
     * 2. Request Header
     *    - 클라이언트가 서버로 요청을 보낼 때 포함하는 정보
     *    - 예: User-Agent, Host, Accept, Cookie, Referer 등
     *
     * 3. Response Header
     *    - 서버가 응답 시 부가 정보를 담아 전달
     *    - 예: Location, Server, Set-Cookie 등
     *
     * 4. Entity Header
     *    - 전송되는 컨텐츠 자체에 대한 상세 정보를 포함
     *    - 요청과 응답 모두에 사용 가능 (단, GET 요청에는 메시지 바디가 없기 때문에 사용되지 않음)
     *    - 예: Content-Type, Content-Length 등
     */

    // 클라이언트가 전송한 모든 요청 헤더의 이름들을 열거형으로 가져온다.
    Enumeration<String> headerNames = request.getHeaderNames();

    // 모든 요청 헤더 이름을 반복하며 콘솔에 출력
    while (headerNames.hasMoreElements()) {
      String header = headerNames.nextElement();
      System.out.println(header);
    }

    // 개별 요청 헤더 값을 직접 확인할 수도 있다.
    // 클라이언트가 어떤 콘텐츠 타입을 수신할 수 있는지 명시하는 "accept" 헤더 출력
    System.out.println("accept : " + request.getHeader("accept"));

    // 클라이언트가 보낸 쿠키 정보가 담긴 "cookie" 헤더 출력
    System.out.println("cookie : " + request.getHeader("cookie"));

    /*
     * 주의:
     * 이 출력은 클라이언트에게 보여지는 것이 아니라 서버 콘솔에 출력되는 것이다.
     * 클라이언트에게 결과를 보여주려면 response.getWriter() 등을 이용해 출력해야 한다.
     */
  }
}
