package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	
	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST}) // 신규회원 가입 화면
	public String register() {
		return "registerForm"; //Web-INF/views/registerForm.jsp
	}
	
	//POST방식으로만 회원 가입
	//@RequestMapping(value = "/register/save", method = {RequestMethod.POST})
	@PostMapping("/register/save") // Spring4.3부터 생김 - 이렇게 간단히 쓸 수 있음.
	public String save(User user, Model m) throws Exception {
		
		//1. 유효성 검사
		if(!isValid(user)) {
			//URL에서는 직접 인코딩을 해줘야 한다. -> URLEncoder.encode / 예외처리도 작성해야 함
			// 또한 View에서 디코딩 해줘야 한다. -> registerForm.jsp
			String msg = URLEncoder.encode("id를 잘못입력했어","utf-8");
			
			m.addAttribute("msg",msg);    
			return "forward:/register/add"; // -> 아래 한줄하고 여기 2줄하고 같다.
			//return "redirect:/register/add?msg="+msg; //URL재작성(rewriting)
		}
		//2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}

}
