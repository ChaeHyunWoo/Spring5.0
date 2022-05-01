package com.fastcampus.ch3.diCopy3;

import com.google.common.reflect.ClassPath;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

// Spring DI(의존성 주입)

/*
 참고 : https://galid1.tistory.com/494
 @ 추가 자료 @ -> @Bean  vs  Component
 위의 2개의 어노테이션은 Spring(IOC) Container에 Bean을 등록하도록 하는 메타데이터를 가입하는 어노테이션
 둘의 차이점 -> 용도가 다르다.

 1. @Bean
 - 개발자가 직접 제어가 불가능한 외부 라이브러리 등을 Bean으로 만들려햘 때 사용

 2. @Component
 - 개발자가 직접 작성한 class를 Bean으로 등록하기 위한 어노테이션

 */

// @Componet : 개발자가 직접 작성한 Class를 자바 Bean으로 등록하기위한 어노테이션
@Component class Car {}
@Component class SportsCar extends Car {}
@Component class Truck extends Car {}
@Component class Engine {}

// @핵심 : Appcontext (ac) 객체를 생성하고, getBean메서드를 이용해서 Map에 저장되어있는 객체를 사용한 것
// - 지금은 일단 하드코딩 한 것이다. 하드코딩은 안좋지만 초반에 잘 안될때는 하드코딩으로 일단 해보는 방법이 좋다. -> 조언(꿀팁)
class AppContext {
    Map map; //객체 저장소

    AppContext() {
        map = new HashMap();
        doComponentScan();
    }

    private void doComponentScan() {
        try {
            // 1. 패키지내의 클래스 목록을 가져온다.
            // 2. 반복문으로 클래스를 하나씩 읽어와서 @Component이 붙어 있는지 확인
            // 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

            for(ClassPath.ClassInfo classInfo : set) {
                Class clazz = classInfo.load();
                Component component =  (Component) clazz.getAnnotation(Component.class);

                if(component != null) {
                    //StringUtils에 있는 uncaptitalize 메서드를 이용해서 첫글자를 대문자로 바꾼다.
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());

                    map.put(id, clazz.newInstance());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key) { // 객체를 검색할 때 byName으로
        return map.get(key);
    }
    Object getBean(Class clazz) { // 매개변수를 클래스 객체로 받는 것은 byType으로 객체를 찾아서 반환
        for(Object obj : map.values()) {
            if(clazz.isInstance(obj))
                return obj;
        }
        return null; //못찾을 땐 null 반환 - 안쓰면 Error
    }
}

public class Main3 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();
        Car car = (Car) ac.getBean("car"); // byName으로 객체 검색 /getObject -> getBean으로 바꿀때 ctrl + R -> 한방에 바꿈
        Car car2 = (Car) ac.getBean(Car.class); // byType으로 객체를 검색
        Engine engine = (Engine) ac.getBean("engine");
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }

    static Object getBean(String key) throws Exception {
        //config.txt를 읽어서 Properties에 저장
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));
    
        //클래스 객체(설계도)를 얻어서
        Class clazz = Class.forName(p.getProperty(key));
        
        //객체를 생성해서 반환
        return clazz.newInstance();
    }
}
