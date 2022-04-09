package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception { //�Ʒ� Ŭ������ ��ã���� ������ ���� ����ó�� �ؾ� ��
		//Hello hello = new Hello();
		//hello.main(); // private�̶� �ܺ� ȣ�� �Ұ�
		
		//������ Hello.java���� �޼��带 private���� �ߴµ� ��� ȣ���� �ǳ�?
		// - Reflection API�� ��� - Ŭ���� ������ ��� �ٷ� �� �ִ� ������ ��� ����
		// - java.lang.reflect��Ű���� ����
		
		//HelloŬ������ Class��ü(Ŭ������ ������ ��� �ִ� ��ü)�� ���´�.
		// - Ŭ���� ����(*.class)�� �޸𸮿� �ö� ��, Ŭ���� ���ϸ��� Class��ü�� �ϳ��� �����ȴ�.
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
		
		//�̰� �̿��ؼ� ��ü�� ���� �� �ִ�
		// - Object�� ����ȯ
		Hello hello = (Hello)helloClass.newInstance(); //Class��ü�� ���� ������ ��ü ����
		Method main = helloClass.getDeclaredMethod("main"); //main�̶�� �޼��� ������ ������
		main.setAccessible(true);//private�� main()�� ȣ�Ⱑ���ϰ� �Ѵ�.
		
		main.invoke(hello); //hello.main()ȣ���ѰͰ� ����. - Hello Ŭ���� ���� main()�޼��带 ȣ��
		
		
		//�ٽ�
		// ������ �����ӿ�ũ�� java�� Reflection API�� �̿��ؼ� ��ü�� ����� ������
		// private�̶� ȣ���� ������ ���̴�.
	}

}
