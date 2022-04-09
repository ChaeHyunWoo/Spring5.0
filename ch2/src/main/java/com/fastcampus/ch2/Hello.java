package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1. 원격 호출가능한 프로그램을 등록
@Controller
public class Hello {
	int iv = 10; //인스턴스 변수
	static int cv = 20; //static 변수
	
	//2. URL과 메서드를 연결
	//- 브라우저에 hello를 치면 이 메서드가 호출되면서 Hello가 출력되는 것
	// - public 대신 private을 써도 된다.
	@RequestMapping("/hello")
	public void main() { //인스턴스 메서드 - iv,cv를 둘다 사용가능
		System.out.println("Hello - static");
		System.out.println(cv); //OK
		System.out.println(iv); //Ok
	
	// static이 없어 인스턴스 메서드인데 객체생성을 안해줬는데 어떻게 호출하냐? / 톰캣 내부에 객체 생성을 해주는 게 있다.
	// - 인스턴스 메서드가 아니면 객체 생성을 해줘야하는데 이걸 톰캣이 객체 생성을 해주고, 호출을 하게 되는것이다.
	}
	
	public static void main2() { //static 메서드 - cv만 사용가능
		System.out.println(cv); //OK
		//System.out.println(iv); //에러 - static메서드는 cv(static변수)만 사용가능
	}

}
