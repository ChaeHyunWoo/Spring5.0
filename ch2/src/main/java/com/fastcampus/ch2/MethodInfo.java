package com.fastcampus.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;


// ���� : void main(javax.servlet.http.HttpServletRequest arg0, javax.servlet.http.HttpServletResponse arg1)
// YoilTeller (HttpServletRequest request, HttpServletResponse response)
// �� Ŭ������ �������� �� �Ű����� Ÿ���� �߿��ѵ� �Ű����� �̸��� �߿����� �ʱ⿡ �Ű����� �̸��� request,response
// ������ arg0, arg1���� ������ ���̴�.
// �Ű� ���� �̸��� �ʿ��ϸ� ������ �ɼǿ� -parameters �ɼ��� ����Ѵ�. �̰� �Ű����� �̸��� �����ϴ� �ɼ��̴�.(JDK8���� ��밡��)
public class MethodInfo {
	public static void main(String[] args) throws Exception{
		
		// 1. YoilTeller Ŭ������ ��ü ����
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// 2. ��� �޼��� ������ �����ͼ� �迭�� ���� - �޼��尡 ����� �𸣱⿡ �迭�� �޴´�.
		Method[] methodArr = clazz.getDeclaredMethods();
		
		//�ݺ������� �޼��带 �ϳ��� �о ������ ���
		for(Method m : methodArr) {
			String name = m.getName(); //�޼��� �̸�
			Parameter[] paramArr = m.getParameters(); //�Ű����� ����� �迭�� ������
//			Class[] paramTypeArr = m.getParameterTypes();
			Class returnType = m.getReturnType(); // �޼����� ��ȯ Ÿ��
			
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
