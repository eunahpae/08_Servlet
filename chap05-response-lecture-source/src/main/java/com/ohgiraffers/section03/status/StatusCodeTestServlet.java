package com.ohgiraffers.section03.status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 클라이언트 요청에 대해 HTTP 응답 상태 코드를 설정하여 오류 페이지를 전송하는 서블릿
 */
@WebServlet("/status")
public class StatusCodeTestServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    /*
     * 클라이언트에게 오류 응답을 보낼 때는 sendError(statusCode, message) 메서드를 사용한다.
     * 이 메서드는 다음과 같은 두 가지 역할을 한다:
     * 1. HTTP 상태 코드를 지정하여 클라이언트에 전달한다.
     * 2. 상태 코드에 해당하는 기본 오류 페이지 또는 커스터마이징된 오류 페이지를 보여준다.
     */

     // 404 Not Found - 요청한 리소스(경로)에 해당하는 페이지가 존재하지 않을 때 사용
     // response.sendError(404, "없는 페이지입니다. 경로를 확인해 주세요.");

    /*
     * 500 Internal Server Error - 서버 내부에서 예기치 않은 오류가 발생했음을 의미
     * 주로 개발자 측 로직 문제 또는 시스템 오류로 인해 발생함
     */
    response.sendError(500, "서버 내부 오류입니다. 서버 오류는 개발자의 잘못입니다.");

    /*
     * 위 메서드 호출 이후에는 추가로 HTML 출력이나 응답 내용 작성이 불필요하다.
     * 서블릿 컨테이너(예: Tomcat)가 상태 코드에 따른 기본 오류 페이지를 자동으로 출력한다.
     * 만약 web.xml 또는 컨테이너 설정에 오류 페이지가 매핑되어 있으면 해당 페이지가 표시된다.
     */
  }
}
