package final_project_2;

import final_project_2.configs.Authority;
import final_project_2.configs.AuthorityEnum;
import final_project_2.configs.AuthorityRepo;
import final_project_2.models.Test;
import final_project_2.models.User;
//import final_project_2.services.TestService;
import final_project_2.repositories.UserRepository;
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

    @Autowired
    AuthorityRepo authorityRepo;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(FinalProject2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        Authority userAuth = Authority.builder().authority(AuthorityEnum.ROLE_USER).build();
//        Authority adminAuth = Authority.builder().authority(AuthorityEnum.ROLE_ADMIN).build();


//        if (authorityRepo.findAll().isEmpty()) {
//            authorityRepo.saveAll(Arrays.asList(userAuth,adminAuth));
//        }


//        if (userRepository.findAll().isEmpty()) {
//            userRepository.saveAll(
//                    Arrays.asList(
//
//                            new User("user", passwordEncoder.encode("user"), Collections.singletonList(userAuth)),
//                            new User("admin", passwordEncoder.encode("admin"), Arrays.asList(adminAuth, userAuth))
//                    )
//            );
//        }
        Authority userAuthority = authorityRepo.findByAuthority(AuthorityEnum.ROLE_USER);
        if(userAuthority == null){
            userAuthority = authorityRepo.save(Authority.builder().authority(AuthorityEnum.ROLE_USER).build());
        }
        Authority adminAuthority = authorityRepo.findByAuthority(AuthorityEnum.ROLE_ADMIN);
        if(adminAuthority == null){
            adminAuthority = authorityRepo.save(Authority.builder().authority(AuthorityEnum.ROLE_ADMIN).build());
        }
        if(userRepository.findByName("user") == null){
            userRepository.save(new User("user", passwordEncoder.encode("user"), Collections.singletonList(userAuthority)));
        }
        if(userRepository.findByName("admin") == null){
            userRepository.save(new User("admin", passwordEncoder.encode("admin"), Arrays.asList(adminAuthority, userAuthority)));
        }
    }
}



