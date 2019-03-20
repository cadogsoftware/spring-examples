package com.example.copsbot.user;


import com.example.copsbot.user.web.CreateOfficerParameters;
import com.example.copsbot.user.web.UserRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
//@ActiveProfiles(SpringProfiles.TEST)
public class UserRestControllerTest {


    private static final String TEST_EMAIL = "alex.foley@beverly-hills.com";
    private static final String TEST_PASSWORD = "my-secret-pwd";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void officer_whenAskingForCurrentUser() throws Exception {

        Optional<User> userOptional = createUser();

        when(userService.getUser(any())).thenReturn(userOptional);
        when(userService.createOfficer(any(), any())).thenReturn(userOptional.get());

        mvc.perform(get("/api/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("email").value(TEST_EMAIL))
                .andExpect(jsonPath("roles").isArray())
                .andExpect(jsonPath("roles[0]").value("OFFICER"));

        verify(userService).getUser(any());

        // The next line is only needed in this temporary implementation, as the GET on currentUser should not be
        //  creating the user.
        verify(userService).createOfficer(TEST_EMAIL, TEST_PASSWORD); // This is a bit of a false assertion here

    }

    @Test
    public void createOfficer_valid() throws Exception {
        CreateOfficerParameters parameters = new CreateOfficerParameters(TEST_EMAIL, TEST_PASSWORD);

        Optional<User> userOptional = createUser();

        when(userService.createOfficer(any(), any())).thenReturn(userOptional.get());

        ObjectMapper objectMapper = new ObjectMapper(); // Could have autowired this at the top of the class.

        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(parameters)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("email").value(TEST_EMAIL))
                .andExpect(jsonPath("roles").isArray())
                .andExpect(jsonPath("roles[0]").value("OFFICER"));

        verify(userService).createOfficer(TEST_EMAIL, TEST_PASSWORD);

    }

    private Optional<User> createUser() {
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.OFFICER);
        UserId userId = new UserId(UUID.randomUUID());
        User user = new User(userId,
                TEST_EMAIL,
                TEST_PASSWORD,
                roles);
        return Optional.of(user);

    }
}
