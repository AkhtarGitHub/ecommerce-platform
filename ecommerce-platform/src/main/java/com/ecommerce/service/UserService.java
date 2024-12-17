package com.ecommerce.service;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;
import com.ecommerce.util.BCryptUtil;
import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void registerUser(String username, String password, String email, String role) {
        String hashedPassword = BCryptUtil.hashPassword(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setEmail(email);
        user.setRole(role);
        userDAO.registerUser(user);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }
}
