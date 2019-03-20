package com.example.copsbot.user.web;


import com.example.copsbot.user.User;
import com.example.copsbot.user.UserDto;
import com.example.copsbot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserDto currentUser() {
        // TODO: pass in user details in order to authenticate

        // For now just create a user and return it... very temporary implementation
        User someNewUser = userService.createOfficer("alex.foley@beverly-hills.com", "my-secret-pwd");
        Optional<User> userReturned = userService.getUser(someNewUser.getId());
        return new UserDto(userReturned.orElse(null)); // For now

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createOfficer(@Valid @RequestBody CreateOfficerParameters parameters) {

        // Note: request mapping is not on this method, so it defaults to the class' @RequestMapping

        User officer = userService.createOfficer(parameters.getEmail(), parameters.getPassword());

        return new UserDto(officer);

    }


}
