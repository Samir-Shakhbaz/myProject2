package final_project_2.services;

import final_project_2.exceptions.NotFoundException;
import final_project_2.models.Question;
import final_project_2.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    // The getAllQuestions function gets all the answers by doing a SELECT query in the DB.

    public List<Question> getAllQuestions() {
//        if(getAllQuestions().isEmpty()){
//            throw new NoSuchQuestionException("There are no questions yet");
//        }
        return questionRepository.findAll();
    }


    @Transactional
    //save function uses an INSERT query in the database
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    List<Question> saveAllQuestion(List<Question> questionList) {
        return questionRepository.saveAll(questionList);
    }

    //The findById function uses a SELECT query with a WHERE clause in the DB.
    public Question getQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    // The deleteById function deletes the question by doing a DELETE in the DB.
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
