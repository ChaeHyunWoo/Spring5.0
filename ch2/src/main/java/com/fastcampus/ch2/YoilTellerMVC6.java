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

//���� ���¹�
@Controller
public class YoilTellerMVC6 {
	// �� ������̼��� ( ) ��ȣ ���� ���ܰ� �߻����� �� �� �޼��尡 ȣ��ȴ�.
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		
		System.out.println("result : " + result);
		FieldError error = result.getFieldError();
		
		System.out.println("code=" + error.getCode());
		System.out.println("field=" + error.getField());
		System.out.println("msg=" + error.getDefaultMessage());
		
		ex.printStackTrace(); //���� ���� ����ϱ�
		
		return "yoilError";//ó���� ����� yoilError�̶�� ��� �����ش�.
	}
	
    @RequestMapping("/getYoilMVC6") // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
    //public String main(@ModelAttribute MyDate date, Model model) {
    public String main(MyDate date, BindingResult result) {
    	
    	System.out.println("result : " + result);   
    	//1. ��ȿ�� �˻�
    	if(!isValid(date))
    		return "yoilError";
    	
    	//2. ���� ���
        //char yoil = getYoil(date); -> �׷��� �޼��� ȣ���� �κ��� �ʿ������
        
        //3. ����� ����� model�� ����
        //model.addAttribute("myDate",date);
        //model.addAttribute("yoil",yoil); -> ���� 2�ٵ� �ʿ������
        
        // - model�� ������ �����͸� ��(View)�� ������. View : yoil.jsp
        return "yoil";// WEB-INF/views/yoil.jsp�� ��ȯ�Ѵ�.

    } 
    
	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	//@ModelAttribute�� ���� �� �޼��尡 �ڵ����� ȣ���� �ǰ�, �� ����� Model�� ����
	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
        cal.set(year, month -1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);// 1:�Ͽ���, 2:������ ...
        
        return" �Ͽ�ȭ�������".charAt(dayOfWeek);
	}
	
	private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // ������ üũ 
    }
}