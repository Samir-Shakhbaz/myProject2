package final_project_2;

import final_project_2.models.Test;
import final_project_2.models.User;
//import final_project_2.services.TestService;
import final_project_2.services.NewTestService;
import final_project_2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class FinalProject2Application implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    NewTestService newTestService;

    public static void main(String[] args) {
        SpringApplication.run(FinalProject2Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        if (userService.getAllUsers().isEmpty()) {
            userService.saveUser(
                    new User("user", passwordEncoder.encode("user"))
            );
        }
    }
}




