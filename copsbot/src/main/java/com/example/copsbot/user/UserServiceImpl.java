package com.example.copsbot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createOfficer(String email, String password) {

        UserRole userRole = UserRole.OFFICER;
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        UserId userId = userRepository.nextId();

        User user = new User(userId, email, password, userRoles);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(UserId userId) {

        return userRepository.findById(userId);
    }
}
