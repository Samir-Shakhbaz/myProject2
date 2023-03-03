//package final_project_2.controllers;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import final_project_2.API.ArticleService;
//import final_project_2.FinalProject2Application;
//import final_project_2.configs.SecurityConfig;
//import final_project_2.services.NewTestService;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest(classes = HomeController.class)
//@Import(SecurityConfig.class)
//@AutoConfigureMockMvc
//public class MyTestController {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    NewTestService newTestService;
//
//    @MockBean
//    ArticleService articleService;
//
//    @Test
//    public void shouldReturnDefaultMessage() throws Exception {
//
//        this.mockMvc.perform(get("/hello"))
//                .andDo(print())
//                .andExpect(status().isFound())
//                .andExpect(content()
//                        .string(containsString("Hello and welcome!")));
//
//    }
//}