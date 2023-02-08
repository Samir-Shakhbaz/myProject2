package final_project_2;

import final_project_2.configs.Authority;
import final_project_2.configs.AuthorityEnum;
import final_project_2.configs.AuthorityRepo;
import final_project_2.models.User;
import final_project_2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableCaching
public class FinalProject2Application implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityRepo authorityRepo;

    @Autowired
    UserRepository userRepository;


    // The main method is defined here which starts the application
    public static void main(String[] args) {
        SpringApplication.run(FinalProject2Application.class, args);
    }

    //The run method performs the operation at runtime
    //Here it sets password for the registered user and the admin
    @Override
    public void run(String... args) throws Exception {

        //This class is restricting authority to two allowed authorities: USER and ADMIN
        Authority userAuthority = authorityRepo.findByAuthority(AuthorityEnum.ROLE_USER);
        if (userAuthority == null) {
            userAuthority = authorityRepo.save(Authority.builder().authority(AuthorityEnum.ROLE_USER).build());
        }
        Authority adminAuthority = authorityRepo.findByAuthority(AuthorityEnum.ROLE_ADMIN);
        if (adminAuthority == null) {
            adminAuthority = authorityRepo.save(Authority.builder().authority(AuthorityEnum.ROLE_ADMIN).build());
        }
        //Here passwords are set to USER and ADMIN
        if (userRepository.findByName("user") == null) {
            userRepository.save(new User("user", passwordEncoder.encode("user"), Collections.singletonList(userAuthority)));
        }
        if (userRepository.findByName("admin") == null) {
            userRepository.save(new User("admin", passwordEncoder.encode("admin"), Arrays.asList(adminAuthority, userAuthority)));
        }
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

//    @Bean
//    public CacheManager cacheManager() {
//        // configure and return an implementation of Spring's CacheManager SPI
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Set.of(new ConcurrentMapCache("default")));
//        return cacheManager;
//    }
}




