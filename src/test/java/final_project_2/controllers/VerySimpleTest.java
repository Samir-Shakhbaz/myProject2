package final_project_2.controllers;

import final_project_2.API.ArticleService;
import final_project_2.FinalProject2Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest(classes = FinalProject2Application.class)
public class VerySimpleTest {

    @MockBean
    ArticleService articleService;

        @Test
        public void contextLoads() {

        }
    }

