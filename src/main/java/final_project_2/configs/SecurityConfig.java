package final_project_2.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(
                        (requests) -> requests
                        .antMatchers("/", "/css/**").permitAll()
//                        .antMatchers("/new").hasAnyAuthority("ADMIN", "NEW_USER")
//                        .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//                        .antMatchers("/delete/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                        )
                .formLogin().and()
                .logout();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}



//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        //this bean creates an in memory repository of users.
//        //add a user to the cache with username "admin" password "hi" and a ROLE_USER
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("hi"))
//                        .roles("USER").build());
//    }
