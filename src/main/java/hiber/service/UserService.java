package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    List<User> getUserByCarModelAndSeries(String model, int series);
}