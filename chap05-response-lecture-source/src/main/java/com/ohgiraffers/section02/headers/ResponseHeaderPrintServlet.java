package com.ohgiraffers.section02.headers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

/**
 * 클라이언트 요청에 대한 응답 시, 설정된 HTTP 응답 헤더를 출력하는 서블릿
 */
@WebServlet("/headers")
public class ResponseHeaderPrintServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    /*
     * 응답 헤더의 Content-Type을 지정
     * "text/html" 형식의 콘텐츠를 UTF-8 문자 인코딩으로 전달하겠다는 의미
     * 브라우저가 HTML 형식으로 응답 본문을 올바르게 해석하게 된다.
     */
    response.setContentType("text/html;charset=UTF-8");

    // "Refresh" 헤더를 설정하면 브라우저가 지정된 간격(초)마다 자동으로 새로고침하게 된다.
    // response.setHeader("Refresh", "1");

    // 문자 출력 스트림 확보
    PrintWriter out = response.getWriter();

    // 현재 시스템 시간을 밀리초 단위로 가져옴
    long currentTime = System.currentTimeMillis();

    // 현재 시간을 HTML 형식으로 응답 본문에 출력
    out.print("<h1>" + currentTime + "</h1>");

    out.close();

    /*
     * 응답에 포함된 모든 헤더 이름을 가져옴
     * getHeaderNames()는 응답에 설정된 헤더들의 이름을 Collection<String> 형태로 반환
     */
    Collection<String> responseHeaders = response.getHeaderNames();

    /*
     * 응답 헤더를 콘솔에 출력 (클라이언트가 아닌 서버 개발자가 확인 가능)
     * 각 헤더 이름과 해당 이름에 대한 값(여러 개일 수 있음)을 출력
     */
    Iterator<String> iter = responseHeaders.iterator();
    while (iter.hasNext()) {
      String headerName = iter.next();
      System.out.println(headerName + " : " + response.getHeaders(headerName));
    }

    /*
     * 주의:
     * 위의 헤더 출력은 System.out.println()을 사용하여 서버 콘솔에 출력되는 것이므로,
     * 클라이언트는 볼 수 없고 오직 서버 로그에서 확인할 수 있다.
     */
  }
}
