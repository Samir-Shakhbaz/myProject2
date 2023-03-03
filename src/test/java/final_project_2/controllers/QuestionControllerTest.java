package final_project_2.controllers;

import final_project_2.FinalProject2Application;
import final_project_2.configs.Authority;
import final_project_2.configs.AuthorityEnum;
import final_project_2.configs.AuthorityRepo;
import final_project_2.models.Question;
import final_project_2.models.User;
import final_project_2.repositories.QuestionRepository;
import final_project_2.repositories.UserRepository;
import final_project_2.services.AnswerService;
import final_project_2.services.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.rmi.NoSuchObjectException;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
@ContextConfiguration(classes = FinalProject2Application.class)
@ActiveProfiles("test")
public class QuestionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService questionService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    AuthorityRepo authorityRepo;

    @MockBean
    UserRepository userRepository;

    @MockBean
    QuestionRepository questionRepository;

    @MockBean
    AnswerService answerService;

    @MockBean
    RestTemplateBuilder restTemplateBuilder;

    @Test
    public void testQuestionController() throws Exception {

        User user = new User("name", "user", Collections.singletonList(Authority.builder()
                .authority(AuthorityEnum.ROLE_USER)
                .build()));
        Question question = new Question();
            question.setId(2l);
            question.setName("Why?");
        when(questionService.getAllQuestions()).thenReturn(Collections.singletonList(question));
        this.mockMvc.perform(get("/question/list")
                .with(user(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create new question")))
                .andExpect(content().string(containsString("Why?")));
    }
}
