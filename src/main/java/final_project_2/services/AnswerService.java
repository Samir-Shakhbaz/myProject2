package final_project_2.services;

import final_project_2.models.Answer;
import final_project_2.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    // The getAllAnswers function gets all the answers by doing a SELECT query in the DB.
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    private List<Answer> answers;

    @Transactional
    //save function uses an INSERT query in the DB
    public List <Answer> saveAnswers(List<Answer> answers) {
        return answerRepository.saveAll(answers);
    }

    @Transactional
    //save function uses an INSERT query in the DB
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public AnswerService() {
        List<Answer> list = new ArrayList<>();
        Answer answer = new Answer();
        answer.setId(1l);
    }

    //The findById function uses a SELECT query with a WHERE clause in the DB.
    public Answer getAnswer(Long id) {
        return answerRepository.findById(id)
                .orElse(null);
    }

    // The deleteById function deletes the answer by doing a DELETE in the DB.
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}

