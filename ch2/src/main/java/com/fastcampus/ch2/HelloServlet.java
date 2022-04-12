package com.fastcampus.ch2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿이나 스프링이나 하나의 객체를 만들어서 여러 객체를 처리한다 - 싱글톤 패턴(디자인 패턴)
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		//서블릿이 초기화될때 자동 호출되는 메서드
		//1. 서블릿의 초기화 작업 담당
		System.out.println("[HelloServlet] init() is called.");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.입력
		//2.처리
		//3.출력
		System.out.println("[HelloServlet] service() is called.");
	}

	@Override
	public void destroy() {
		//3. 뒷정리 - 서블릿이 메모리에서 제거될때 서블릿 컨테이너에 의해서 자동 호출
		System.out.println("[HelloServlet] destroy() is called.");
	}

	

	
}
