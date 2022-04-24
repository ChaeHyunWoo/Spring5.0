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

// �α��� ȭ�� ��Ű ���� 
@Controller
@RequestMapping("/login")
public class LoginController {
	
	//�α���
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	//�α׾ƿ�
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 1. ������ ����
		session.invalidate();
		// 2. Ȩ���� �̵�
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, 
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		// 1. id�� pwd�� Ȯ��
		if(!loginCheck(id,pwd)) {
			// 2-1 ��ġ���� ������, loginForm���� �̵�
			String msg = URLEncoder.encode("id �Ǵ� pwd�� ��ġ���� �ʽ��ϴ�.","utf-8");
			
			return "redirect:/login/login?msg=" + msg; //login.jsp�� msg�� �ٿ��� ����
		}
		
		// 2-2 id�� pwd�� ��ġ�ϸ�, Ȩ���� �̵�
		// ���� ��ü�� ������(requeset���� ����)
		HttpSession session = request.getSession();
		// ��ġ�ϸ� ���� ��ü�� id�� ����
		session.setAttribute("id", id);
		
		if(rememberId) {
			//1. ��Ű ����
			Cookie cookie = new Cookie("id", id);
			// 2. ���信 ����
			response.addCookie(cookie);
		}else {
			//��Ű ����
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0); //��Ű ����
			response.addCookie(cookie);
		}
		
		
		
		// 3. Ȩ���� �̵�
		return "redirect:/"; //home.jsp
	}

	private boolean loginCheck(String id, String pwd) {
		//ID�� asdf / ��й�ȣ�� 1234�϶� �α��� ����
		return "asdf".equals(id) && "1234".equals(pwd);
	}

}
