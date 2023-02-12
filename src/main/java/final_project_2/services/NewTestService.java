package final_project_2.services;

import final_project_2.models.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewTestService {
    List<Test> getAllTests();

    @Transactional
    Test saveTest(Test test);

    List<Test> saveAllTests(List<Test> testList);

    Test getTest(Long id);

    void deleteTest(Long id);

}


