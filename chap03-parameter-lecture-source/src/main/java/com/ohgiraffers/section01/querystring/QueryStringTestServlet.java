package com.ohgiraffers.section01.querystring;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/querystring")
public class QueryStringTestServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    /*
     * GET 요청은 URL 뒤에 Query String을 붙여 파라미터를 전송한다.
     * 예시: /querystring?name=홍길동&age=20&birthday=2000-01-01&gender=M&national=한국&hobbies=축구&hobbies=게임
     *
     * HttpServletRequest의 getParameter() 및 getParameterValues() 메서드를 사용해
     * 쿼리스트링으로 전달된 데이터를 추출할 수 있다.
     */

    // 단일 값 파라미터(name) 가져오기
    String name = request.getParameter("name");
    System.out.println("이름 : " + name);

    // 숫자형 데이터(age)는 문자열로 전달되므로 int 타입으로 변환 필요
    int age = Integer.parseInt(request.getParameter("age"));
    System.out.println("나이 : " + age);

    /*
     * java.sql.Date : 데이터베이스와 호환되는 날짜 형식(java.sql.Date)으로 변환
     * valueOf()는 반드시 "yyyy-MM-dd" 형식의 문자열을 요구함
     */
    java.sql.Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
    System.out.println("생일 : " + birthday);

    // 성별 파라미터 추출 (예: "M" 또는 "F")
    String gender = request.getParameter("gender");
    System.out.println("성별 : " + gender);

    // 국적 파라미터 추출
    String national = request.getParameter("national");
    System.out.println("국적 : " + national);

    // 다중 선택 가능한 파라미터(hobbies)는 getParameterValues()로 처리해야 함
    System.out.println("취미 : ");
    String[] hobbies = request.getParameterValues("hobbies");
    if (hobbies != null) {
      for (String hobby : hobbies) {
        System.out.println(hobby);
      }
    } else {
      System.out.println("선택한 취미가 없습니다.");
    }

    /*
     * 참고:
     * - getParameter()는 단일 값만 처리하므로, 같은 이름의 파라미터가 여러 개 있을 경우 첫 번째 값만 반환
     * - getParameterValues()는 체크박스처럼 다중 선택된 값을 배열로 반환
     * - 전달되지 않은 파라미터는 null을 반환하므로 NullPointerException 주의
     */
  }
}
