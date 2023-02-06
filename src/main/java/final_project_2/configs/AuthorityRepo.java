package final_project_2.configs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {

    //simple Repository which allows to find a user by granted authority

    Authority findByAuthority(AuthorityEnum authority);

}
