package final_project_2.controllers;

import final_project_2.API.ArticleService;
import final_project_2.API.Docs;
import final_project_2.FinalProject2Application;
import final_project_2.configs.Authority;
import final_project_2.configs.AuthorityEnum;
import final_project_2.configs.SecurityConfig;
import final_project_2.models.User;
import final_project_2.services.NewTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;


@SpringBootTest(classes = HomeController.class)
@Import(SecurityConfig.class)
@AutoConfigureMockMvc
public class SpringBootTestAgain {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NewTestService newTestService;

    @MockBean
    ArticleService articleService;

    @Test
    public void BootTestAgain() throws Exception {
        when(articleService.getSearchJava()).thenReturn(Collections.singletonList(new Docs()));
        mockMvc.perform(
                get("/")
                )
                .andDo(print())
                .andExpect(view().name("home"));
    }
}