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

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setUserCar(new Car("carModel1", 111));
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setUserCar(new Car("carModel2", 222));
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setUserCar(new Car("carModel3", 333));
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        user4.setUserCar(new Car("carModel4", 444));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("User car model = " + user.getUserCar().getModel());
            System.out.println("User car series = " + user.getUserCar().getSeries());
            System.out.println();
        }

        User user = userService.getUserByCarModelAndSeries("carModel3", 333);
        System.out.println("------------");
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println("User car model = " + user.getUserCar().getModel());
        System.out.println("User car series = " + user.getUserCar().getSeries());

        context.close();
    }
}
