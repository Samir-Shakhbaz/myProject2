package final_project_2.services;

import final_project_2.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public interface NewTestService {

    List<Test> getAllTests();

    @Transactional
    Test saveTest(Test test);

    List<Test> saveAllTests(List<Test> testList);

    Test getTest(Long id);

    void deleteTest(Long id);

//    public List<Test> getAvailableTest();

//    Test get(Integer id);//////////////////////////TEST.HTML///////////////////////////////
}


