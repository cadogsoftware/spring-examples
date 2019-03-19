package com.example.copsbot.user;


import com.example.copsbot.user.web.UserRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
//@ActiveProfiles(SpringProfiles.TEST)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void officer_whenAskingForCurrentUser() throws Exception {

        final String testEmail = "alex.foley@beverly-hills.com";
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.OFFICER);
        UserId userId = new UserId(UUID.randomUUID());
        User user = new User(userId,
                testEmail,
                "my-secret-pwd",
                roles);
        Optional<User> userOptional = Optional.of(user);

        when(userService.createOfficer(any(), any())).thenReturn(userOptional.get());
        when(userService.getUser(any())).thenReturn(userOptional);

        mvc.perform(get("/api/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("email").value(testEmail))
                .andExpect(jsonPath("roles").isArray())
                .andExpect(jsonPath("roles[0]").value("OFFICER"));

    }
}
