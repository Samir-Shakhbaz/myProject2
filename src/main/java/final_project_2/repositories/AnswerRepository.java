package final_project_2.repositories;

import final_project_2.models.Answer;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository - THIS ANNOTATIONS SHOULD BE UN-COMMENTED DURING SPRINGBOOTTEST AnswerRepoTest.java
//@Profile("for_testing")
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
