package final_project_2.services;

import final_project_2.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface UserService extends UserDetailsService {
    User saveUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);

}
