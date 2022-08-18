import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld beanHello1 =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(beanHello1.getMessage());
        HelloWorld beanHello2 =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(beanHello2.getMessage());

        Cat beanCat1 =
                (Cat) applicationContext.getBean("Cat");
        System.out.println(beanCat1.getName());
        Cat beanCat2 =
                (Cat) applicationContext.getBean("Cat");
        System.out.println(beanCat2.getName());
        System.out.println("helloworld(equals):");
        System.out.println(beanHello1.equals(beanHello2));
        System.out.println("Cats(equals):");
        System.out.println(beanCat1.equals(beanCat2));

    }


}