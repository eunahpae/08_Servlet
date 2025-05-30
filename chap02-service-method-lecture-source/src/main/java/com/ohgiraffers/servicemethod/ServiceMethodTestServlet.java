package com.ohgiraffers.servicemethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet 클래스에서 service() 메서드를 직접 오버라이딩하여
 * HTTP method(GET/POST 등)에 따라 적절한 처리를 직접 분기하는 예제
 */
@WebServlet("/request-service")
public class ServiceMethodTestServlet extends HttpServlet {

  /**
   * HttpServlet 클래스에서는 기본적으로 service() 메서드가
   * 요청의 HTTP 메서드(GET, POST 등)를 확인하여 자동으로 doGet(), doPost() 등으로 위임해 준다.
   *
   * 이 예제에서는 service() 메서드를 오버라이딩하여, 개발자가 직접 메서드 확인 및 분기를 수행하는 방식으로 처리하고 있다.
   */
  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    // ServletRequest와 ServletResponse는 HttpServletRequest/Response의 상위 타입이므로, 다운캐스팅 필요
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    // HTTP 요청 메서드(GET, POST 등)를 확인한다.
    String httpMethod = httpRequest.getMethod();
    System.out.println("httpMethod = " + httpMethod);

    // 요청 메서드에 따라 알맞은 처리 메서드로 위임
    if ("GET".equals(httpMethod)) {
      doGet(httpRequest, httpResponse);
    } else if ("POST".equals(httpMethod)) {
      doPost(httpRequest, httpResponse);
    }
    // 필요한 경우 PUT, DELETE 등 추가 메서드 분기도 구현 가능
  }

  /**
   * GET 요청 시 호출되는 메서드
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("GET 요청을 처리할 메소드 호출");
  }

  /**
   * POST 요청 시 호출되는 메서드
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("POST 요청을 처리할 메소드 호출");
  }
}
