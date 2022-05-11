package com.fastcampus.ch3;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.*;
import org.springframework.web.context.support.*;

import javax.servlet.*;
import javax.servlet.http.*;

@Controller
public class HomeController {

	// - Servlet AC(ApplicationContext)는 컨트롤러에 인스턴스 변수를 선언하고
	// @Autowired 어노테이션을 써줘서 주입받을 수 있다.
	@Autowired
	WebApplicationContext servletAC; // Servlet AC

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, HttpServletRequest request, Model model) {
		// 원래는 request.getServletContext()지만, 컨트롤러는 HttpServlet을 상속받지 않아서 아래와 같이 해야함.

		// - Root AC(ApplicationContext)를 컨트롤러에서 얻어올려면 하단의 2줄이 있어야 한다.
		// Servlet에서는 간단하게 얻어올 수 있는데 컨트롤러에서는 이렇게 해야한다.
		ServletContext sc = request.getSession().getServletContext(); // ApplicationContextFacade
		WebApplicationContext rootAC = WebApplicationContextUtils.getWebApplicationContext(sc); // Root AC

		System.out.println("webApplicationContext = " + rootAC);
		System.out.println("servletAC = " + servletAC);

		System.out.println("rootAC.getBeanDefinitionNames() = " + Arrays.toString(rootAC.getBeanDefinitionNames()));
		System.out.println("servletAC.getBeanDefinitionNames() = " + Arrays.toString(servletAC.getBeanDefinitionNames()));

		System.out.println("rootAC.getBeanDefinitionCount() = " + rootAC.getBeanDefinitionCount());
		System.out.println("servletAC.getBeanDefinitionCount() = " + servletAC.getBeanDefinitionCount());

		System.out.println("servletAC.getParent()==rootAC = " + (servletAC.getParent() == rootAC)); // servletAC.getParent()==rootAC = true
		return "home";
	}
}