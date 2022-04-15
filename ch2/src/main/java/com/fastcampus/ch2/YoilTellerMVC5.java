package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Calendar;

// @ ModelAttribute 어노테이션 학습 - MVC4를 복사에서 활용
// - 이 어노테이션은 참조형 매개변수 앞에 선언 가능(생략가능) / 생략해도 하단의 주석처리처럼 안써도된다.
// - 생략하면 (@ModelAttribute("myDate") 이런 의미

// 요약 - 매개변수가 기본형,String일 경우 @RequestParam이 생략된 것
//	   - 매개변수가 참조형일 경우 @ModelAttribute가 생략된 것

// - YoilTeller.java를 스프링 패턴을 이용해서 관심사를 분리할 것이다.
@Controller
public class YoilTellerMVC5 {
	// 이 어노테이션은 ( ) 괄호 안의 예외가 발생했을 때 이 메서드가 호출된다.
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace(); //에러 내용 출력하기
		
		return "yoilError";//처리한 결과를 yoilError이라는 뷰로 보여준다.
	}
	
    @RequestMapping("/getYoilMVC5") // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
    //public String main(@ModelAttribute("myDate") MyDate date, Model model) {
    //("myDate")를 생략하면 타입(MyDate)를 첫글자를 소문자로 한 것을 Key로 쓴다.
    public String main(@ModelAttribute MyDate date, Model model) {
        
    	//1. 유효성 검사
    	if(!isValid(date))
    		return "yoilError";
    	
    	//2. 요일 계산
        //char yoil = getYoil(date); -> 그래서 메서드 호출한 부분이 필요없어짐
        
        //3. 계산한 결과를 model에 저장
        //model.addAttribute("myDate",date);
        //model.addAttribute("yoil",yoil); -> 여기 2줄도 필요없어짐
        
        // - model에 저장한 데이터를 뷰(View)로 보낸다. View : yoil.jsp
        return "yoil";// WEB-INF/views/yoil.jsp를 반환한다.

    } 
    
	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	//@ModelAttribute를 쓰면 이 메서드가 자동으로 호출이 되고, 그 결과를 Model에 저장한다. 39번째줄
	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
        cal.set(year, month -1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);// 1:일요일, 2:월요일 ...
        
        return" 일월화수목금토".charAt(dayOfWeek);
	}
	
	private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
    }
}