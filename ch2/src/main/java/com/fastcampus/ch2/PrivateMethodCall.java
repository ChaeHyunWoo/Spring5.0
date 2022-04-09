package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception { //아래 클래스를 못찾으면 에러가 떠서 예외처리 해야 함
		//Hello hello = new Hello();
		//hello.main(); // private이라서 외부 호출 불가
		
		//하지만 Hello.java에서 메서드를 private으로 했는데 어떻게 호출이 되나?
		// - Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능 제공
		// - java.lang.reflect패키지를 제공
		
		//Hello클래스의 Class객체(클래스의 정보를 담고 있는 객체)를 얻어온다.
		// - 클래스 파일(*.class)이 메모리에 올라갈 때, 클래스 파일마다 Class객체가 하나씩 생성된다.
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
		
		//이걸 이용해서 객체를 만들 수 있다
		// - Object라서 형변환
		Hello hello = (Hello)helloClass.newInstance(); //Class객체가 가진 정보로 객체 생성
		Method main = helloClass.getDeclaredMethod("main"); //main이라는 메서드 정보를 가져옴
		main.setAccessible(true);//private인 main()을 호출가능하게 한다.
		
		main.invoke(hello); //hello.main()호출한것과 같다. - Hello 클래스 안의 main()메서드를 호출
		
		
		//핵심
		// 스프링 프레임워크는 java의 Reflection API를 이용해서 객체를 만들기 때문에
		// private이라도 호출이 가능한 것이다.
	}

}
