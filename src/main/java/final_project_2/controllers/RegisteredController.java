//package final_project_2.controllers;
//
//import final_project_2.models.Answer;
//import final_project_2.models.Question;
//import final_project_2.models.Test;
//import final_project_2.models.User;
//import final_project_2.services.NewTestService;
//import final_project_2.services.QuestionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Controller
//public class RegisteredController {
//
//    @Autowired
//    NewTestService newTestService;
//
//    @Autowired
//    QuestionService questionService;
//
//    @GetMapping("/registered/home")
//    public String vewHomePageGuest(Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(user);
//        System.out.println(user.isAdmin());
//        final List<Test> testList = newTestService.getAllTests();
//        model.addAttribute("testList", testList);
//        model.addAttribute("isAdmin", user.isAdmin());
//        return "registered-home";
//    }
//
//    @GetMapping("/")
//    public String redirectHome(){
//
//            return "redirect:/test/tests";
//    }
//
//    @GetMapping("/registered/tests")
//    public String getTestsPageRegistered(Model model) {
//        List<Test> tests = newTestService.getAllTests();
//        model.addAttribute("allTests", tests);
//        return "registered-tests";
//    }
//
//    @GetMapping("/registered/tests/{id}")
//    public String getTestPageRegistered(Model model, @PathVariable Long id) {
//        Test test = newTestService.getTest(id); // == new Test(1l, "Test 1", questions())
//        model.addAttribute("testObject", test);
//        return "registered-test";
//    }
//
//    @PostMapping(value = "/registered/answer/submit", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public String saveAnswerRegistered(@RequestParam MultiValueMap<String, String> values, Model model) {
//
//        Long testId = 0L;
//
//        Set<Long> answerIds = values.values().stream()
//                .flatMap(Collection::stream)
//                .map(Long::parseLong)
//                .collect(Collectors.toSet());
//
//        Map<Long, Answer> userAnswers = new HashMap<>();
//        Map<Long, Answer> correctAnswers = new HashMap<>();
//
//        for (String questionId : values.keySet()) {
//
//            Question question = questionService.getQuestion(Long.parseLong(questionId));
//            testId = question.getTest().getId();
//            boolean ok = true;
//
//            List<Long> wrong = new ArrayList<>();
//
//            for (Answer answer : question.getAnswers()) {
//                if(answerIds.contains(answer.getId())){
//                    userAnswers.put(question.getId(), answer);
//                }
//
//                if(answer.isCorrect()){
//                    correctAnswers.put(question.getId(), answer);
//                }
//
//                if (answer.isCorrect() && !answerIds.contains(answer.getId())) {
//                    ok = false;
//                    wrong.add(answer.getId());
//                }
//                if (!answer.isCorrect() && answerIds.contains(answer.getId())) {
//                    ok = false;
//                    wrong.add(answer.getId());
//                }
//
//                System.out.println("hello " + questionId + "-" + answer.getId());
//         }
//            System.out.println(questionId + ": " + ok + ", wrong: " + wrong);
//
//        }
//
//        Test test = newTestService.getTest(testId); // == new Test(1l, "Test 1", questions())
//
//        model.addAttribute("testObject", test);
//        model.addAttribute("userAnswers", userAnswers);
//        model.addAttribute("correctAnswers", correctAnswers);
//
//        return "registered-answers";
//    }
//
//
//
//}
