package final_project_2.repositories;

import final_project_2.models.CorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectAnswerRepository extends JpaRepository <CorrectAnswer, Long> {
}
