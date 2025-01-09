package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.management.Query;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.addUser(new User("Ivan", "Ivanov", "Ivanov", "ivan@mail.ru", new Car("BMW", 7)));
        userService.addUser(new User("Sergey", "Sergeev", "Sergeev", "sergey@mail.ru", new Car("FORD", 150)));
        userService.addUser(new User("Egor", "Egorov", "Egorov", "egor@mail.ru", new Car("VOLVO", 747)));
        userService.addUser(new User("Stas", "Stasov", "Stasov", "stas@mail.ru", new Car("AUDI", 5)));

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        userService.getUserByCarModelAndSeries("BMW", 7);
        context.close();
    }
}