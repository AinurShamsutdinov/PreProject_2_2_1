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

      for(int i = 6; i > 0; i--){
         String firstName = "User" + i;
         String lastName = "LastName" + i;
         String email = "user" + i + "@mail.ru";
         String carName = "TY666T 6"+ i +"rus";
         int series = 34 + i;
         User user = new User(firstName, lastName, email);
         Car car = new Car(carName, series);
         user.setCar(car);
         car.setUser(user);
         userService.add(user);
      }

      Car car = new Car("TY666T 65rus", 39);
      User user = userService.getByCar(car);

      System.out.println("Id = "+user.getId());
      System.out.println("First Name = "+user.getFirstName());
      System.out.println("Last Name = "+user.getLastName());
      System.out.println("Email = "+user.getEmail());
      System.out.println("Car =" + user.getCar().toString());
      System.out.println();


      List<User> users = userService.listUsers();
      for (User userSaved : users) {
         System.out.println("Id = "+userSaved.getId());
         System.out.println("First Name = "+userSaved.getFirstName());
         System.out.println("Last Name = "+userSaved.getLastName());
         System.out.println("Email = "+userSaved.getEmail());
         System.out.println("Car =" + userSaved.getCar().toString());
         System.out.println();
      }

      context.close();
   }
}
