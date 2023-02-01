package final_project_2.services;

import final_project_2.models.Question;
import final_project_2.models.Test;
import final_project_2.repositories.QuestionRepository;
import final_project_2.repositories.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    // The save function uses an INSERT query in the DB.
    @Override
    @Transactional
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    @Transactional
    public List<Test> saveAllTests(List<Test> testList) {return testRepository.saveAll(testList);

    }
    @Override
    @Transactional
    public Test getTest(Long id) {
        return testRepository.findById(id)
                .orElse(null);
    }

//    public List<Test> getAvailableTest() {
//        return getAllTests().stream().filter(c -> c.getQuestions() == null)
//                .collect(Collectors.toList());
//    }



//    @Override ////////////////////////////////////TEST.HTML//////////////////////////////////
//    public Test get(Integer id){
//        return getAllTests().get(id-1);
//    }
    @Override
    @Transactional
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }


}
