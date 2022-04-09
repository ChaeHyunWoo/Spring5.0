package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Calendar;

// 년월일을 입력하면 요일을 알려주는 프로그램
// - YoilTeller.java를 스프링 패턴을 이용해서 관심사를 분리할 것이다.
@Controller
public class YoilTellerMVC {
	
	/*
    @RequestMapping("/getYoilMVC") // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
    public String main(int year, int month, int day, Model model) throws IOException {
        
    	//1. 유효성 검사
    	if(!isValid(year,month,day))
    		return "yoilError";
    	
    	//2. 요일 계산
        char yoil = getYoil(year, month, day);
        
        //3. 계산한 결과를 model에 저장
        model.addAttribute("year",year);
        model.addAttribute("month",month);
        model.addAttribute("day",day);
        model.addAttribute("yoil",yoil);
        
        // - model에 저장한 데이터를 뷰(View)로 보낸다. View : yoil.jsp
        return "yoil";// WEB-INF/views/yoil.jsp를 반환한다.

    } */
	
	//위의 방법을 ModelAndView 방식으로 변경
	@RequestMapping("/getYoilMVC") // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
	public ModelAndView main(int year, int month, int day) throws IOException {
	    
		//1. ModelAndView를 생성하고, 기본 뷰를 지정
		// - 매개변수에서 model을 지우고 여기서 ModelAndView를 객체생성 해준다.
		ModelAndView mv = new ModelAndView();
		
	    //2. 유효성 검사
		if(!isValid(year,month,day))
	    	return mv;
	    	
	    //3. 작업 - 요일 계산
	    char yoil = getYoil(year, month, day);
	        
	    //4. ModelAndView에 작업한 결과를 저장(mv)
	    mv.addObject("year",year);
	    mv.addObject("month",month);
	    mv.addObject("day",day);
	    mv.addObject("yoil",yoil);
	    
	    //5. 작업 결과를 보여줄 뷰(View)의 이름을 지정 - yoil로 지정
	    mv.setViewName("yoil");
	    
	    //6. ModelAndView를 반환
	    return mv; // mv를 반환한다.
	}
    
	private boolean isValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
        cal.set(year, month -1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);// 1:일요일, 2:월요일 ...
        
        return" 일월화수목금토".charAt(dayOfWeek);
	}
}