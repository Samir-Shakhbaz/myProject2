package final_project_2.services;

import final_project_2.models.User;
import final_project_2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    @Autowired
    final UserRepository userRepository;
    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) { userRepository.deleteById(id); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }
}
