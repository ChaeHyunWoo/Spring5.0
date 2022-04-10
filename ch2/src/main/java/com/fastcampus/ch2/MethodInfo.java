package com.fastcampus.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;


// 실행 : void main(javax.servlet.http.HttpServletRequest arg0, javax.servlet.http.HttpServletResponse arg1)
// YoilTeller (HttpServletRequest request, HttpServletResponse response)
// 이 클래스를 컴파일할 때 매개변수 타입은 중요한데 매개변수 이름은 중요하지 않기에 매개변수 이름이 request,response
// 이지만 arg0, arg1으로 나오는 것이다.
// 매개 변수 이름이 필요하면 컴파일 옵션에 -parameters 옵션을 줘야한다. 이건 매개변수 이름을 저장하는 옵션이다.(JDK8부터 사용가능)
public class MethodInfo {
	public static void main(String[] args) throws Exception{
		
		// 1. YoilTeller 클래스의 객체 생성
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// 2. 모든 메서드 정보를 가져와서 배열에 저장 - 메서드가 몇개일지 모르기에 배열로 받는다.
		Method[] methodArr = clazz.getDeclaredMethods();
		
		//반복문으로 메서드를 하나씩 읽어서 정보를 출력
		for(Method m : methodArr) {
			String name = m.getName(); //메서드 이름
			Parameter[] paramArr = m.getParameters(); //매개변수 목록을 배열로 가져옴
//			Class[] paramTypeArr = m.getParameterTypes();
			Class returnType = m.getReturnType(); // 메서드의 반환 타입
			
			StringJoiner paramList = new StringJoiner(", ", "(", ")");
			
			for(Parameter param : paramArr) {
				String paramName = param.getName();
				Class  paramType = param.getType();
				
				paramList.add(paramType.getName() + " " + paramName);
			}
			
			System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
		} //end for
	} // main
}
