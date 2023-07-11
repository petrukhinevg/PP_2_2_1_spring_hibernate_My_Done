package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("zil", 1, user1);
        Car car2 = new Car("lada", 2, user2);
        Car car3 = new Car("moskvich", 3, user3);
        Car car4 = new Car("zhiguli", 4, user4);

//        userService.addUser(user1);
//        userService.addUser(user2);
//        userService.addUser(user3);
//        userService.addUser(user4);
//        можно было передавать в конструктор User'a экземпляр Car'a
//        тогда добавление car производилось бы через User'a


        carService.addCar(car1);
        carService.addCar(car2);
        carService.addCar(car3);
        carService.addCar(car4);


        List<User> users = userService.getListUsers();
        for (User user : users) {
            System.out.println(user);
        }
        List<Car> cars = carService.getListCars();
        for (Car car : cars) {
            System.out.println(car);
        }

        String mod = "lada";
        int ser = 2;

        User user = userService.getUserByModelAndSeries(mod, ser);
        System.out.println("Владелец " + mod + " v" + ser + " - " + user);

        context.close();
    }
}
