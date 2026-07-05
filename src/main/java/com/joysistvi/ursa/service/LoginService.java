package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.Login;
import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.repository.UserRepository;
import com.joysistvi.ursa.repository.impl.UserRepositoryImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class LoginService {
    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepositoryImpl();
    }

    public User authenticate(Login login) throws SQLException {

        User user = userRepository.getUserByEmail(login.getUsername());

        if (user == null) {
            return null;
        }

        if (BCrypt.checkpw(login.getPassword(), user.getPassword())) {
            return user;
        }

        return null;
    }
}