package final_project_2.services;

import final_project_2.models.Answer;
import final_project_2.models.Question;
import final_project_2.models.Test;
import final_project_2.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    private List<Answer> answers;

    @Transactional
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public AnswerService() {
        List<Answer> list = new ArrayList<>();
        Answer answer = new Answer();
        answer.setId(1l);
    }

    public Answer getAnswer(Long id) {
        return answerRepository.findById(id)
                .orElse(null);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

//    public Answer get(Integer id) {////////////////////////////////TEST.HTML//////////////////////////////////
//    return null;}
}

