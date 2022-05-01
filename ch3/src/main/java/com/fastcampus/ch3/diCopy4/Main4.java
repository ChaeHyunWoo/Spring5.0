package com.fastcampus.ch3.diCopy4;

import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

// Spring DI(의존성 주입)
// 객체를 자동으로 연결 - @Autowired - 객체의 type으로 검색
// 객체를 자동으로 연결 - @Resource -  객체의 name으로 검색
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
@Component class Car {

    // @Autowired를 써서 객체를 자동으로 연결
    // - 그리고 @Autowired를 처리해주는 코드가 필요
    @Resource //@Autowired
    Engine engine;
   // @Resource //@Autowired
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}
@Component class SportsCar extends Car {}
@Component class Truck extends Car {}
@Component class Engine {}
@Component class Door{}

// @핵심 : Appcontext (ac) 객체를 생성하고, getBean메서드를 이용해서 Map에 저장되어있는 객체를 사용한 것
class AppContext {
    Map map; //객체 저장소

    AppContext() {
        map = new HashMap();
        doComponentScan();
        doAutowired(); //@Autowired 를 처리해주는 메서드
        doResource();

    }

    private void doResource() {
        // map에서 저장된 객체의 iv중에 @Resource가 붙어 있으면
        // map에서 iv의 이름에 맞는 객체를 찾아서 연결( 객체의 주소를 iv저장)
        try {
            // 1. map에 value에 저장된 것들을 가져다가
            for(Object bean : map.values()) {
                for(Field fld : bean.getClass().getDeclaredFields()) {
                    // 2. 거기에 Autowired가 붙어있으면 해당하는 Type을 뒤져서 저장해준다.
                    if(fld.getAnnotation(Resource.class)!=null) //byName
                        fld.set(bean, getBean(fld.getName())); // car.engine = obj;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
        // map에서 저장된 객체의 iv중에 @Autowired가 붙어 있으면
        // map에서 iv의 타입에 맞는 객체를 찾아서 연결( 객체의 주소를 iv저장)
        try {
            // 1. map에 value에 저장된 것들을 가져다가
            for(Object bean : map.values()) {
                for(Field fld : bean.getClass().getDeclaredFields()) {
                    // 2. 거기에 Autowired가 붙어있으면 해당하는 Type을 뒤져서 저장해준다.
                    if(fld.getAnnotation(Autowired.class)!=null) //byType
                        fld.set(bean, getBean(fld.getType())); // car.engine = obj;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doComponentScan() {
        try {
            // 1. 패키지내의 클래스 목록을 가져온다.
            // 2. 반복문으로 클래스를 하나씩 읽어와서 @Component이 붙어 있는지 확인
            // 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");

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

public class Main4 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();
        Car car = (Car) ac.getBean("car"); // byName으로 객체 검색
        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean(Door.class); // byType으로 객체를 검색

        //수동으로 객체를 연결
        //car.engine = engine;
        //car.door = door;
        
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);
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
