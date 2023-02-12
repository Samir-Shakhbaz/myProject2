package final_project_2.services;

import final_project_2.models.CorrectAnswer;
import final_project_2.repositories.CorrectAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CorrectAnswerService {

    @Autowired
    CorrectAnswerRepository correctAnswerRepository;

    private List<CorrectAnswer> correctAnswers;

    public List<CorrectAnswer> getAllCorrectAnswers() {
        return correctAnswerRepository.findAll();
    }

    @Transactional
    public CorrectAnswer saveCorrectAnswer(CorrectAnswer correctAnswer) {
        return correctAnswerRepository.save(correctAnswer);
    }

    public CorrectAnswerService() {
        List<CorrectAnswer> list = new ArrayList<>();
        CorrectAnswer correctAnswer = new CorrectAnswer();
        correctAnswer.setId(1l);
    }

    public CorrectAnswer getCorrectAnswer(Long id) {
        return correctAnswerRepository.findById(id)
                .orElse(null);
    }

    public void deleteCorrectAnswer(Long id) {
        correctAnswerRepository.deleteById(id);
    }
}
