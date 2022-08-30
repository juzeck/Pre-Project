package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      User Tom = new User("Tom","Tomson", "Tom@email");
      User John = new User("John", "Johnson", "John@email");
      Car car1 = new Car("ferrari", 2012);
      Car car2 = new Car("lamborghini", 2015);
      Tom.setCar(car1);
      John.setCar(car2);
      userService.add(Tom);
      userService.add(John);

      List<User> users = userService.listUsers();
      System.out.println("All users: ");
      for (User user : users) {
         System.out.println("n Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<User> list = userService.getUserByCar(car1.getModel(), car1.getSeries());
      System.out.println("\n users with car(model: " + car1.getModel() + ", series: " +car1.getSeries() + ") : ");
      System.out.println( list + "\n");

      list = userService.getUserByCar(car2.getModel(), car2.getSeries());
      System.out.println("\n users with car(model: " + car2.getModel() + ", series: " +car2.getSeries() + ") : ");
      System.out.println(list + "\n");

      userService.cleanTables();
      context.close();
   }
}
