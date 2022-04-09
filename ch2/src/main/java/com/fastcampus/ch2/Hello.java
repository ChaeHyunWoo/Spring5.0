package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1. ���� ȣ�Ⱑ���� ���α׷��� ���
@Controller
public class Hello {
	int iv = 10; //�ν��Ͻ� ����
	static int cv = 20; //static ����
	
	//2. URL�� �޼��带 ����
	//- �������� hello�� ġ�� �� �޼��尡 ȣ��Ǹ鼭 Hello�� ��µǴ� ��
	// - public ��� private�� �ᵵ �ȴ�.
	@RequestMapping("/hello")
	public void main() { //�ν��Ͻ� �޼��� - iv,cv�� �Ѵ� ��밡��
		System.out.println("Hello - static");
		System.out.println(cv); //OK
		System.out.println(iv); //Ok
	
	// static�� ���� �ν��Ͻ� �޼����ε� ��ü������ ������µ� ��� ȣ���ϳ�? / ��Ĺ ���ο� ��ü ������ ���ִ� �� �ִ�.
	// - �ν��Ͻ� �޼��尡 �ƴϸ� ��ü ������ ������ϴµ� �̰� ��Ĺ�� ��ü ������ ���ְ�, ȣ���� �ϰ� �Ǵ°��̴�.
	}
	
	public static void main2() { //static �޼��� - cv�� ��밡��
		System.out.println(cv); //OK
		//System.out.println(iv); //���� - static�޼���� cv(static����)�� ��밡��
	}

}
