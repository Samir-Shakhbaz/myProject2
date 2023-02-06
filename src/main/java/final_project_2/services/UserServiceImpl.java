package final_project_2.services;

import final_project_2.configs.Authority;
import final_project_2.configs.AuthorityEnum;
import final_project_2.configs.AuthorityRepo;
import final_project_2.models.User;
import final_project_2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
//@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    @Autowired
    AuthorityRepo authorityRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    final UserRepository userRepository;

//    @Autowired
//    CacheManager cacheManager;

    @Override
    @Transactional
    @CachePut(value = "users", key = "#user.id")
    //save function uses an INSERT query in the database
    public User saveUser(User user) {
        Authority userAuthority = authorityRepo.findByAuthority(AuthorityEnum.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Collections.singletonList(userAuthority));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users")
    // The getAllUsers function gets all the answers by doing a SELECT query in the DB.
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    // The deleteById function deletes the question by doing a DELETE in the DB.
    public void deleteUser(Long id) { userRepository.deleteById(id); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }
}
