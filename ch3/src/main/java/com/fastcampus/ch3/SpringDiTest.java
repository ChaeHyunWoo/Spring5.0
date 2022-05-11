//package com.fastcampus.ch3;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//
////@Component("engine")
//class Engine{}       // = <bean id="engine" class="com.castcampus.ch3.Engine"/>
//@Component class SuperEngine extends Engine {}
//@Component class TurboEngine extends Engine {}
//@Component class Door{}
//@Component
//// 1. @Autowired -> byType으로 주입
//// 2. @Resource -> byName으로 주입
//class Car{
//
//    @Value("red") String color; // String color = "red"와 동일
//    @Value("100") int oil;
//    // @Autowired는 type을 찾아 Bean을 주입하는데 만약에 같은 타입이 여러 개면 @Qualifier 어노테이션이 동작해서 찾아준다.
//    //@Autowired
//    //@Qualifier("superEngine") // ->  2개의 후보(SuperEngine,TurboEngine)중에 "superEngine"로 결정한다.
//    @Resource(name = "superEngine") //->위의 2줄(@Autowired + @Qualifier) 대신 @Resource 사용가능 / 같은 것은 아니다
//    Engine engine;   // byType - @Autowired는 타입으로 먼저 검색, 여러개면 이름으로 검색, - engine,superEngine,turboEngine
//
//    @Autowired Door[] doors;
//
//    public Car() {} // 생성자를 사용할 땐 기본생성자를 꼭 만들어 줘야 함
//    //setter 대신 생성자 사용 가능 - <constructor-arg>
//    // - 이 태그도 생성자가 있어야 사용 가능하다.
//    public Car(String color, int oil, Engine engine, Door[] doors) {
//        this.color = color;
//        this.oil = oil;
//        this.engine = engine;
//        this.doors = doors;
//    }
//
//    //setter - <property>
//    // - 이 태그는 setter가 있어야 사용 가능하다.
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public void setOil(int oil) {
//        this.oil = oil;
//    }
//
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//
//    public void setDoors(Door[] doors) {
//        this.doors = doors;
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//
//public class SpringDiTest {
//    public static void main(String[] args) {
//        // 1. config.xml에 클래스에 대한 정보를 설정해주고
//        // 2. ApplicationContext 종류 중에 xml 문서를 설정파일로 쓰는 enericXmlApplicationContext -> 이걸로 읽어서
//        // 3. 저장소(ac)에 map 형태로 만들어지는 것
//        // 4. getBean으로 읽어와서 사용
//        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//        //Car car = (Car) ac.getBean("car"); // byName으로 검색 -> 아래와 같은 문장
//        Car car = (Car) ac.getBean("car", Car.class); // 이렇게 하면 형변환을 안해도 된다.
//        //Car car2 = (Car) ac.getBean(Car.class);  //byType으로 검색
//
//        // engine = (Engine) ac.getBean("engine"); // byName
//
//        //같은 Type이 여러개 일때는 type으로 찾으면 안되고 byName으로 찾아야 한다. -> 이름을 줘서 구별
//        // - type은 중복을 허용하기 때문
//        //Engine engine = (Engine) ac.getBean("superEngine"); // byName
//        // Engine engine = (Engine) ac.getBean(Engine.class); // byType -> 같은 타입이 3개라서 Error
//        //Door door = (Door) ac.getBean("door");
//
//        // setter대신 config.xml(property)에 작업을 해준다. -> 코드가 줄어들고, 변경사항이 생겼을 때 xml파일을 수정하면 되서 편리
//        // - xml의 <property> 태그는 setter가 있어야 사용 가능하다.
//        // - 추가로 <property> 대신 <constructor>생성자를 사용 가능
//
//car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);          //getBean의 반환 타입은 Object이기에 형변환 or type을 써준다.
//        car.setDoors(new Door[]{ac.getBean("door",Door.class), (Door)ac.getBean("door")});
//
//        System.out.println("car = " + car); // car, car2와 해쉬코드 값이 같은 이유 : 같은 객체이기 때문
//        //System.out.println("engine = " + engine);
//
//    }
//}
