package final_project_2.repositories;

import final_project_2.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String username);

}
