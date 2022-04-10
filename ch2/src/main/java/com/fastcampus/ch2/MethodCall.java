package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class ModelController {
	public String main(HashMap map) { //매개변수를 map으로 선언
		//작업 결과를 map에 저장
		map.put("id", "asdf"); // 4. 작업 결과를 map에 저장한다. 
		map.put("pwd", "1111");// 2개의 main이 한 곳을 가르키기 때문에 
		
		return "txtView2"; //뷰 이름을 반환 - 작업이 종료되면서 뷰 이름을 반환
	}					   // 여기서 이 main메서드에서 map을 아래 main에 반환할 필요가 없다.
}						   // why ? -> 애초에 아래 main에서 맵을 만들었기 때문이다.(map 객체생성)

public class MethodCall {
	public static void main(String[] args) throws Exception{
		HashMap map = new HashMap(); //1. map 객체생성
		System.out.println("before:"+map);

		ModelController mc = new ModelController(); // 2. ModelController 객체 생성
		String viewName = mc.main(map); // 3.map을 ModelController의 main 메서드한테 넘겨준다.
										// 이렇게되면 2개의 main이 같은 번지 key/value를 가르키게 된다.
										// viewName을 반환한다.
		System.out.println("after :"+map);
		
		render(map, viewName);
	}
	
	static void render(HashMap map, String viewName) throws IOException {
		String result = "";
		
		// 1. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File(viewName+".txt"));
		
		while(sc.hasNextLine())
			result += sc.nextLine()+ System.lineSeparator();
		
		// 2. map에 담긴 key를 하나씩 읽어서 template의 ${key}를 value바꾼다.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 3. replace()로 key를 value 치환한다.
			result = result.replace("${"+key+"}", (String)map.get(key));
		}
		
		// 4.렌더링 결과를 출력한다.
		System.out.println(result);
	}
}

