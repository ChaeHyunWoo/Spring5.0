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
	
	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST}) // �ű�ȸ�� ���� ȭ��
	public String register() {
		return "registerForm"; //Web-INF/views/registerForm.jsp
	}
	
	//POST������θ� ȸ�� ����
	//@RequestMapping(value = "/register/save", method = {RequestMethod.POST})
	@PostMapping("/register/save") // Spring4.3���� ���� - �̷��� ������ �� �� ����.
	public String save(User user, Model m) throws Exception {
		
		//1. ��ȿ�� �˻�
		if(!isValid(user)) {
			//URL������ ���� ���ڵ��� ����� �Ѵ�. -> URLEncoder.encode / ����ó���� �ۼ��ؾ� ��
			// ���� View���� ���ڵ� ����� �Ѵ�. -> registerForm.jsp
			String msg = URLEncoder.encode("id�� �߸��Է��߾�","utf-8");
			
			m.addAttribute("msg",msg);    
			return "forward:/register/add"; // -> �Ʒ� �����ϰ� ���� 2���ϰ� ����.
			//return "redirect:/register/add?msg="+msg; //URL���ۼ�(rewriting)
		}
		//2. DB�� �ű�ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}

}
