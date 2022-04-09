package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Calendar;

// ������� �Է��ϸ� ������ �˷��ִ� ���α׷�
// - YoilTeller.java�� ������ ������ �̿��ؼ� ���ɻ縦 �и��� ���̴�.
@Controller
public class YoilTellerMVC {
	
	/*
    @RequestMapping("/getYoilMVC") // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
    public String main(int year, int month, int day, Model model) throws IOException {
        
    	//1. ��ȿ�� �˻�
    	if(!isValid(year,month,day))
    		return "yoilError";
    	
    	//2. ���� ���
        char yoil = getYoil(year, month, day);
        
        //3. ����� ����� model�� ����
        model.addAttribute("year",year);
        model.addAttribute("month",month);
        model.addAttribute("day",day);
        model.addAttribute("yoil",yoil);
        
        // - model�� ������ �����͸� ��(View)�� ������. View : yoil.jsp
        return "yoil";// WEB-INF/views/yoil.jsp�� ��ȯ�Ѵ�.

    } */
	
	//���� ����� ModelAndView ������� ����
	@RequestMapping("/getYoilMVC") // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
	public ModelAndView main(int year, int month, int day) throws IOException {
	    
		//1. ModelAndView�� �����ϰ�, �⺻ �並 ����
		// - �Ű��������� model�� ����� ���⼭ ModelAndView�� ��ü���� ���ش�.
		ModelAndView mv = new ModelAndView();
		
	    //2. ��ȿ�� �˻�
		if(!isValid(year,month,day))
	    	return mv;
	    	
	    //3. �۾� - ���� ���
	    char yoil = getYoil(year, month, day);
	        
	    //4. ModelAndView�� �۾��� ����� ����(mv)
	    mv.addObject("year",year);
	    mv.addObject("month",month);
	    mv.addObject("day",day);
	    mv.addObject("yoil",yoil);
	    
	    //5. �۾� ����� ������ ��(View)�� �̸��� ���� - yoil�� ����
	    mv.setViewName("yoil");
	    
	    //6. ModelAndView�� ��ȯ
	    return mv; // mv�� ��ȯ�Ѵ�.
	}
    
	private boolean isValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
        cal.set(year, month -1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);// 1:�Ͽ���, 2:������ ...
        
        return" �Ͽ�ȭ�������".charAt(dayOfWeek);
	}
}