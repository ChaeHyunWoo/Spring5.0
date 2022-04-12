package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Calendar;

//MVC2와 다른점은 여기서는 year,month,day를 하나로 합쳐서 한다.
// 많은 매개변수가 있을때는 새로운 클래스를 하나 정의해서 매개변수 갯수를 줄일 수 있다.!!!

// 년월일을 입력하면 요일을 알려주는 프로그램
// - YoilTeller.java를 스프링 패턴을 이용해서 관심사를 분리할 것이다.
@Controller
public class YoilTellerMVC4 {
	// 이 어노테이션은 ( ) 괄호 안의 예외가 발생했을 때 이 메서드가 호출된다.
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace(); //에러 내용 출력하기
		
		return "yoilError";//처리한 결과를 yoilError이라는 뷰로 보여준다.
	}
	
    @RequestMapping("/getYoilMVC4") // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
    public String main(MyDate date, Model model) {
        
    	//1. 유효성 검사
    	if(!isValid(date))
    		return "yoilError";
    	
    	//2. 요일 계산
        char yoil = getYoil(date);
        
        //3. 계산한 결과를 model에 저장
        model.addAttribute("myDate",date);
        model.addAttribute("yoil",yoil);
        
        // - model에 저장한 데이터를 뷰(View)로 보낸다. View : yoil.jsp
        return "yoil";// WEB-INF/views/yoil.jsp를 반환한다.

    } 
    
	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
    }

	private char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
        cal.set(year, month -1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);// 1:일요일, 2:월요일 ...
        
        return" 일월화수목금토".charAt(dayOfWeek);
	}
}