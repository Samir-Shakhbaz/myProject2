//package final_project_2.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import final_project_2.configs.AuthorityRepo;
//import final_project_2.models.User;
//import final_project_2.repositories.UserRepository;
//import final_project_2.services.LoginService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(LoginController.class)
//class LoginControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @MockBean
//    AuthorityRepo authorityRepo;
//
//    @MockBean
//    UserRepository userRepository;
//
//    @MockBean
//    LoginService loginService;
//
//    @MockBean
//    RestTemplateBuilder restTemplateBuilder;
//
//    @Mock
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Autowired
//    LoginController loginController;
//
//    @Test
//    public void testController() throws Exception {
//        User user = User.builder().name("name").build();
//        assertThat(loginController).isNotNull();
//        mockMvc.perform(post("/submit").contentType("application/json").content(objectMapper.writeValueAsBytes(user)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"));
//
//    }
//}