package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.repository.UserRepository;
import com.joysistvi.ursa.repository.impl.UserRepositoryImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepositoryImpl();
    }

    public void addUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        User hashedUser = new User(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getEmail(),
                hashedPassword,
                user.getGender(),
                user.getAccountStatus(),
                user.getRole(),
                user.getDateOfBirth()
        );

        userRepository.addUser(hashedUser);
    }
    public List<User> getAllUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) throws SQLException {
        return userRepository.getUserById(id);
    }

    public void updateUser(User user) throws SQLException {
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
        } else {
            User existingUser = userRepository.getUserById(user.getId());
            if (existingUser != null) {
                user.setPassword(existingUser.getPassword());
            }
        }
        userRepository.updateUser(user);
    }

    public void deleteUser(int id) throws SQLException {
        userRepository.deleteUser(id);
    }
}