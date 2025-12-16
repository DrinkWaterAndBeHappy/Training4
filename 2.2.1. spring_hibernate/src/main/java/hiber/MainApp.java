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

      Car car1 = new Car("Mazda", 5);
      User user1 = new User("Антон", "Петров", "petrov@mail.ru");
      user1.setCar(car1);
      userService.add(user1);

      Car car2 = new Car("Toyota", 4);
      User user2 = new User("Борис", "Кузнецов", "boris@mail.ru");
      user2.setCar(car2);
      userService.add(user2);

      Car car3 = new Car("Volvo", 7);
      User user3 = new User("Павел", "Игнатьев", "ignat@mail.ru");
      user3.setCar(car3);
      userService.add(user3);

      Car car4 = new Car("BMW", 5);
      User user4 = new User("Егор", "Новосельский", "novo@mail.ru");
      user4.setCar(car4);
      userService.add(user4);

      User foundUSer = userService.getUserByCar("Mazda", 5);
      System.out.println("Владелец данной машины: " + foundUSer.getFirstName() + " " + foundUSer.getLastName());

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      context.close();
   }
}
