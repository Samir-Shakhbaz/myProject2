package final_project_2.controllers;


import final_project_2.FinalProject2Application;
import final_project_2.configs.Authority;
import final_project_2.configs.AuthorityEnum;
import final_project_2.models.Answer;
import final_project_2.models.User;
import final_project_2.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = FinalProject2Application.class)
@AutoConfigureMockMvc
public class AnswerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AnswerService answerService;

    @Test
    public void saveAnswer() throws Exception {

        User user = new User("name", "user", Collections.singletonList(Authority.builder()
                .authority(AuthorityEnum.ROLE_USER)
                .build()));

        Answer answer = new Answer();
                answer.setId(55L);
        when(answerService.getAllAnswers()).thenReturn(Collections.singletonList(answer));
//        mockMvc
//                .perform()


    }

}

//        //use mockMvc to start a request
//        mockMvc
//                //.perform is used to indicate what mockMvc should do
//                .perform(
//                        //the get method and the path passed in as a parameter is used to indicate the
//                        //HTTP method and the url path used to make the request
//                        get("/hello"))
//                //print the response
//                .andDo(print())
//                //the response should have status 200 OK
//                .andExpect(status().isOk())
//                //test that this response has a body that contains a "Hello Back" String
//                .andExpect(content().string(containsString("Hello Back")));
