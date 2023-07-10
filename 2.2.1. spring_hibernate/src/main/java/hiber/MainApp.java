package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        user1.setCar(new Car("zil", 1, user1));
        user2.setCar(new Car("lada", 2, user2));
        user3.setCar(new Car("moskvich", 3, user3));
        user4.setCar(new Car("zhiguli", 4, user4));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }

        String mod = "zil";
        int ser = 1;

        User user = userService.getUserByModelAndSeries(mod, ser);
        System.out.println("Владелец " + mod + " v" + ser + " - " + user);

        context.close();
    }
}
