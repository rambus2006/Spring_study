import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name;
    private int age;

    // 기본 생성자(no-args constructor)는 필수
    public Person() {
        System.out.println("Person() called!");
    }

    public Person(String name, int age) {
        System.out.println("Person(String name, int age) called!");
        this.name = name;
        this.age = age;
    }

    public void sayName() {
        System.out.println(this.name);
    }

    // @Autowired를 통해 ApplicationContext를 주입받음
    @Autowired
    public void setSomething(ApplicationContext context) {
        // Bean 이름을 직접 사용하지 않고, 필요한 객체를 자동으로 주입받음
        Person p = context.getBean(Person.class);
        p.sayName();

        MyCalculatorService myCalculatorService = context.getBean(MyCalculatorService.class);
        System.out.println(myCalculatorService.calcAdd(3, 4));

        Calculator.Calculator calculator = context.getBean(Calculator.Calculator.class);
        System.out.println(calculator.add(5, 4));
    }
}
