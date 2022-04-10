package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class ModelController {
	public String main(HashMap map) { //�Ű������� map���� ����
		//�۾� ����� map�� ����
		map.put("id", "asdf"); // 4. �۾� ����� map�� �����Ѵ�. 
		map.put("pwd", "1111");// 2���� main�� �� ���� ����Ű�� ������ 
		
		return "txtView2"; //�� �̸��� ��ȯ - �۾��� ����Ǹ鼭 �� �̸��� ��ȯ
	}					   // ���⼭ �� main�޼��忡�� map�� �Ʒ� main�� ��ȯ�� �ʿ䰡 ����.
}						   // why ? -> ���ʿ� �Ʒ� main���� ���� ������� �����̴�.(map ��ü����)

public class MethodCall {
	public static void main(String[] args) throws Exception{
		HashMap map = new HashMap(); //1. map ��ü����
		System.out.println("before:"+map);

		ModelController mc = new ModelController(); // 2. ModelController ��ü ����
		String viewName = mc.main(map); // 3.map�� ModelController�� main �޼������� �Ѱ��ش�.
										// �̷��ԵǸ� 2���� main�� ���� ���� key/value�� ����Ű�� �ȴ�.
										// viewName�� ��ȯ�Ѵ�.
		System.out.println("after :"+map);
		
		render(map, viewName);
	}
	
	static void render(HashMap map, String viewName) throws IOException {
		String result = "";
		
		// 1. ���� ������ ���پ� �о �ϳ��� ���ڿ��� �����.
		Scanner sc = new Scanner(new File(viewName+".txt"));
		
		while(sc.hasNextLine())
			result += sc.nextLine()+ System.lineSeparator();
		
		// 2. map�� ��� key�� �ϳ��� �о template�� ${key}�� value�ٲ۴�.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 3. replace()�� key�� value ġȯ�Ѵ�.
			result = result.replace("${"+key+"}", (String)map.get(key));
		}
		
		// 4.������ ����� ����Ѵ�.
		System.out.println(result);
	}
}

