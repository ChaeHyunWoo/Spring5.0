package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

// Spring DI(의존성 주입)

//1. Spring DI 흉내내기

class Car {}
class SportsCar extends Car {}
class Truck extends Car {}
class Engine {}

// @핵심 : Appcontext (ac) 객체를 생성하고, getBean메서드를 이용해서 Map에 저장되어있는 객체를 사용한 것
// - 지금은 일단 하드코딩 한 것이다. 하드코딩은 안좋지만 초반에 잘 안될때는 하드코딩으로 일단 해보는 방법이 좋다. -> 조언(꿀팁)
class AppContext {
    Map map; //객체 저장소

    AppContext() {
        try {
            Properties p = new Properties();
            p.load(new FileReader("config.txt"));

            // Properties에 저장된 내용을 Map에 저장
            map = new HashMap(p);

            //반복문으로 클래스 이름을 얻어서 객체를 생성해서 다시 map에 저장장
           for(Object key : map.keySet()) {
                //클래스 객체(설계도)를 얻어서
                Class clazz = Class.forName((String)map.get(key));
                map.put(key, clazz.newInstance());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key) {
        return map.get(key);
    }
}

public class Main2 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();
        Car car = (Car) ac.getBean("car"); // getObject -> getBean으로 바꿀때 ctrl + R -> 한방에 바꿈
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
