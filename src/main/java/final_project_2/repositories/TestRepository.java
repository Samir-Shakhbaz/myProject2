package final_project_2.repositories;

import final_project_2.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository <Test, Long> {
    Test findByQuestionsId(Long id);
}
