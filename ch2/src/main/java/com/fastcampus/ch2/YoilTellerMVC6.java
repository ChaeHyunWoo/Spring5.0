package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Calendar;

//에러 보는법
@Controller
public class YoilTellerMVC6 {
	// 이 어노테이션은 ( ) 괄호 안의 예외가 발생했을 때 이 메서드가 호출된다.
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		
		System.out.println("result : " + result);
		FieldError error = result.getFieldError();
		
		System.out.println("code=" + error.getCode());
		System.out.println("field=" + error.getField());
		System.out.println("msg=" + error.getDefaultMessage());
		
		ex.printStackTrace(); //에러 내용 출력하기
		
		return "yoilError";//처리한 결과를 yoilError이라는 뷰로 보여준다.
	}
	
    @RequestMapping("/getYoilMVC6") // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
    //public String main(@ModelAttribute MyDate date, Model model) {
    public String main(MyDate date, BindingResult result) {
    	
    	System.out.println("result : " + result);   
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

	//@ModelAttribute를 쓰면 이 메서드가 자동으로 호출이 되고, 그 결과를 Model에 저장
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