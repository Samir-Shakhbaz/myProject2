package final_project_2.services;

import final_project_2.models.Test;
import final_project_2.repositories.QuestionRepository;
import final_project_2.repositories.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class NewTestServiceImpl implements NewTestService {
    @Autowired
    TestRepository testRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionService questionService;

    @Override
    // The getAllTests function gets all the answers by doing a SELECT query in the DB.
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    @Transactional
    //save function uses an INSERT query in the database
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    @Transactional
    public List<Test> saveAllTests(List<Test> testList) {
        return testRepository.saveAll(testList);

    }

    @Override
    @Transactional
    //The findById function uses a SELECT query with a WHERE clause in the DB.
    public Test getTest(Long id) {
        return testRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    // The deleteById function deletes the test by doing a DELETE in the DB.
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
