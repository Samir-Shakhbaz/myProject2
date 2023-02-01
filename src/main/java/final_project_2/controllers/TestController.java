package final_project_2.controllers;

import final_project_2.models.Answer;
import final_project_2.models.Question;
import final_project_2.models.Test;
import final_project_2.services.AnswerService;
//import final_project_2.services.TestService;
import final_project_2.services.NewTestService;
import final_project_2.services.QuestionService;
import final_project_2.services.UserService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Controller
@RequestMapping("/test")
//@RestController
public class TestController {

    @Autowired
    NewTestService newTestService;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @Autowired
    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String vewTestList(@NotNull Model model) {
        final List<Test> testList = newTestService.getAllTests();
        model.addAttribute("testList", testList);
        return "test-list";
    }

    @PostMapping("/save")
    public String saveTest(@ModelAttribute("test") Test test) {
        newTestService.saveTest(test);
        return "redirect:/test-list";
    }

    @GetMapping("/new")
    public String ShowNewTestPage(Model model) {
        Test test = new Test();
        model.addAttribute("test", test);
        return "new-test";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditTestPage(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-test");
        Test test = newTestService.getTest(id);
        modelAndView.addObject("test", test);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateTest(@PathVariable(name = "id") Long id, @ModelAttribute("test") Test test, Model model) {
        if (!id.equals(test.getId())) {
            model.addAttribute("message",
                    "Cannot update, test id " + test.getId()
                            + " doesn't match id to be updated: " + id + ".");
            return "error-page";
        }
            newTestService.saveTest(test);
            return "redirect:/test-list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTest(@PathVariable(name = "id") Long id) {
        newTestService.deleteTest(id);
        return "redirect:/test-list";
    }

    @GetMapping("/assign/{id}")
    public String assignTest(@PathVariable(name = "id") Long id, Model model) {
       Question question = questionService.getQuestion(id);
       List<Test> testList = newTestService.getAllTests();
//        for(int i =0; i< questions.size(); i++){
//            Question question = questionService.getQuestion(id);
//        }
       model.addAttribute("question", question);
       model.addAttribute("testList", testList);
       return "assign-test";
    }

    @PostMapping("assign")
    public String saveTestAssignment(@RequestParam("questionId") Long questionId, @RequestParam("testId") Long testId) {
        Test test = newTestService.getTest(testId);
        test.getQuestions().size();
        if(test.getQuestions().size()>10){
            System.out.println("error");
        }
        Question question = questionService.getQuestion(questionId);
//        test.setQuestion(question);
//        newTestService.saveTest(test);
        question.setTest(test);
        questionService.saveQuestion(question);
        return "redirect:/question/list";
    }

    @RequestMapping("remove/{id}")
    public String removeTest(@PathVariable(name = "id") Long questionId) {
        Question question = questionService.getQuestion(questionId);
        question.setTest(null);
        questionService.saveQuestion(question);
        return"redirect:/question/list";
    }

    @GetMapping("/nik/tests")
    public String getTestsPage(Model model) {
        List<Test> tests = newTestService.getAllTests();
        model.addAttribute("allTests", tests);
        return "tests";
    }

    @GetMapping("/nik/tests/{id}")
    public String getTestPage(Model model, @PathVariable Long id) {
        Test test = newTestService.getTest(id); // == new Test(1l, "Test 1", questions())

        model.addAttribute("testObject", test);

        return "test";
    }

}

//    @GetMapping("/testQ")
//    public String getTestsPageQ(Model model) {
//        List<Test> tests = newTestService.getAllTests();
//        model.addAttribute("allTests", tests);
//        return "testQ";
//    }



