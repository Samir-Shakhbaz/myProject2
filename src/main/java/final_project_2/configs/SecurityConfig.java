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
                //CSRF filter is disabled to allow Postman use the application
                .csrf().disable()
                .cors().disable()
                //changing endpoint access settings
                .authorizeHttpRequests(
                        (requests) -> requests
                                //guest setting allows access to all users
                                .antMatchers("/", "/css/**","/create-account","/save").permitAll()
                                //"/registered" can be accessed by users who created an account
                                .antMatchers("/registered").hasAnyAuthority("USER")
                                //"ADMIN" can access all endpoints in the application
                                .antMatchers("/secure", "/registered").hasAnyAuthority("ADMIN")
                                //any other request will need authentication
                                .anyRequest().authenticated()
                )
                //a form to log in with the default login page is used
                .formLogin().and()
                //a form to log out with the default logout page is used
                .logout();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

