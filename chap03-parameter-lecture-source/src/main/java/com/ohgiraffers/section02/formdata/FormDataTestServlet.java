package com.ohgiraffers.section02.formdata;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HTML의 form 태그를 이용하여 전송된 form-data 형식의 요청 데이터를 처리하는 서블릿
 */
@WebServlet("/formdata")
public class FormDataTestServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    /*
     * 클라이언트가 form 태그를 통해 POST 방식으로 요청을 전송한 경우,
     * 요청 바디에 포함된 form-data를 추출하여 처리할 수 있다.
     *
     * request.getParameter("key")            : 단일 파라미터 값 추출
     * request.getParameterMap()              : 모든 파라미터를 Map<String, String[]> 형태로 반환
     * request.getParameterNames()            : 모든 파라미터 이름들을 Enumeration 으로 반환
     * request.getParameterValues("key")      : 다중 선택된 값들을 String[]로 반환
     */

    // 단일 파라미터(name) 추출
    String name = request.getParameter("name");
    System.out.println("이름 : " + name);

    // 모든 파라미터를 Map 으로 추출 (key: 파라미터명, value: 값 배열)
    Map<String, String[]> requestMap = request.getParameterMap();
    Set<String> keySet = requestMap.keySet();          // 파라미터 키들의 Set
    Iterator<String> keyIter = keySet.iterator();      // 키 반복자

    // 모든 파라미터 출력
    while (keyIter.hasNext()) {
      String key = keyIter.next();
      String[] value = requestMap.get(key);

      System.out.println("key = " + key);
      for (int i = 0; i < value.length; i++) {
        System.out.println("value[" + i + "] : " + value[i]);
      }
    }

    /*
     * request.getParameterNames()를 사용하면,
     * form 에서 전송된 모든 파라미터의 이름(키) 목록만 열거할 수 있다.
     */
    Enumeration<String> names = request.getParameterNames();
    while (names.hasMoreElements()) {
      System.out.println(names.nextElement());
    }

    /*
     * 주의:
     * - getParameterMap()은 value를 String[]로 제공하므로, 체크박스나 다중 선택된 값도 포함된다.
     */
  }
}
