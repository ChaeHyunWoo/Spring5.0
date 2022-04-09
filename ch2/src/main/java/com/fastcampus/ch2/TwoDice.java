package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//서버는 2가지 리소스를 제공한다.
// - TwoDice 클래스는 실행할때마다 결과가 바뀌는데 이것을 동적 리소스라고 한다.(프로그램이 생성하는 결과)
// - dice1.jpg 등은 변하지 않기에 정적 리소스라고 함(ex:js,css,html,이미지)
@Controller // ctrl + shif + o 는 자동 import
public class TwoDice {
	
    @RequestMapping("/rollDice")
    public void main(HttpServletResponse response) throws IOException {
        int idx1 = (int)(Math.random()*6)+1;
        int idx2 = (int)(Math.random()*6)+1;

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<img src='resources/img/dice"+idx1+".jpg'>");
        out.println("<img src='resources/img/dice"+idx2+".jpg'>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}