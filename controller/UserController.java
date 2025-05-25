package controller;

import dao.UserDao;
import model.User;
// ...existing code...

public class UserController {
    private UserDao userDao = new UserDao();

    public User getUser(int id) {
        return userDao.findById(id);
    }

    public void createUser(User user) {
        userDao.save(user);
    }
    // ...other business logic...
}
