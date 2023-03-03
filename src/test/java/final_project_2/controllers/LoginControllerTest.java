package final_project_2.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import final_project_2.FinalProject2Application;
import final_project_2.configs.Authority;
import final_project_2.configs.AuthorityEnum;
import final_project_2.configs.AuthorityRepo;
import final_project_2.configs.SecurityConfig;
import final_project_2.models.User;
import final_project_2.repositories.UserRepository;
import final_project_2.services.AnswerService;
import final_project_2.services.LoginService;
import final_project_2.services.NewTestService;
import final_project_2.services.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
@ContextConfiguration(classes = FinalProject2Application.class)
@Import(SecurityConfig.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NewTestService newTestService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    AuthorityRepo authorityRepo;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RestTemplateBuilder restTemplateBuilder;

    @MockBean
    AnswerService answerService;

    @MockBean
    QuestionService questionService;

    @MockBean
    LoginService loginService;

    @Test
    public void testController() throws Exception {
        User user = new User("user", "user", Collections.singletonList(Authority.builder()
                        .authority(AuthorityEnum.ROLE_USER)
                        .build()));

        mockMvc.perform(post("/submit")
                        .with(user(user))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}