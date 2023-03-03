package final_project_2.controllers;

import final_project_2.configs.AuthorityRepo;
import final_project_2.repositories.UserRepository;
import final_project_2.services.AnswerService;
import final_project_2.services.NewTestService;
import final_project_2.services.QuestionService;
import final_project_2.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@WebMvcTest(TestController.class)
@ContextConfiguration
public class SimpleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    AuthorityRepo authorityRepo;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

    @MockBean
    NewTestService newTestService;

    @MockBean
    QuestionService questionService;

    @MockBean
    AnswerService answerService;

    @MockBean
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    TestController testController;

    @Test
    public void vewTestList() { assertThat(testController).isNotNull();
    }

    @Test
    public void testWebOnlyContextLoads() {
        assertThat(testController).isNotNull();
    }
}