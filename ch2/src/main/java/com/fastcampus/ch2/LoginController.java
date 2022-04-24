package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 로그인 화면 쿠키 예제 
@Controller
@RequestMapping("/login")
public class LoginController {
	
	//로그인
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 1. 세션을 종료
		session.invalidate();
		// 2. 홈으로 이동
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, 
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		// 1. id와 pwd를 확인
		if(!loginCheck(id,pwd)) {
			// 2-1 일치하지 않으면, loginForm으로 이동
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.","utf-8");
			
			return "redirect:/login/login?msg=" + msg; //login.jsp에 msg를 붙여서 보냄
		}
		
		// 2-2 id와 pwd가 일치하면, 홈으로 이동
		// 세션 객체를 얻어오기(requeset에서 얻어옴)
		HttpSession session = request.getSession();
		// 일치하면 세션 객체에 id를 저장
		session.setAttribute("id", id);
		
		if(rememberId) {
			//1. 쿠키 생성
			Cookie cookie = new Cookie("id", id);
			// 2. 응답에 저장
			response.addCookie(cookie);
		}else {
			//쿠키 삭제
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0); //쿠키 삭제
			response.addCookie(cookie);
		}
		
		
		
		// 3. 홈으로 이동
		return "redirect:/"; //home.jsp
	}

	private boolean loginCheck(String id, String pwd) {
		//ID가 asdf / 비밀번호가 1234일때 로그인 가능
		return "asdf".equals(id) && "1234".equals(pwd);
	}

}
