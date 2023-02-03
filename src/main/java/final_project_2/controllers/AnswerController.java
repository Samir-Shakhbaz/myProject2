package final_project_2.controllers;

import final_project_2.models.Answer;
import final_project_2.models.Question;
import final_project_2.models.Test;
import final_project_2.models.User;
//import final_project_2.services.AnswerService;

import final_project_2.services.AnswerService;
import final_project_2.services.NewTestService;
import final_project_2.services.QuestionService;
import final_project_2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Component
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    NewTestService newTestService;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    @PostMapping(value = "/save")
    public String saveAnswer(@ModelAttribute("answer") Answer answer) {
        answerService.saveAnswer(answer);
        return "redirect:/answer/list";
    }

    @GetMapping("/list")
    public String showAnswerListPage(Model model) {
        List<Answer> answerList = answerService.getAllAnswers();
        model.addAttribute("answerList", answerList);
        return "answer-list";
    }

    @GetMapping("/new")
    public String ShowNewAnswerPage(Model model) {
        Answer answer = new Answer();
        model.addAttribute("answer", answer);
        return "new-answer";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit-answer");
        Answer answer = answerService.getAnswer(id);
        mav.addObject("answer", answer);
        return mav;
    }

    @PostMapping("/update/{id}")
    public String updateAnswer(@PathVariable(name = "id") Long id, @ModelAttribute("answer") Answer answer, Model model) {
        if (!id.equals(answer.getId())) {
            model.addAttribute("message",
                    "Cannot update, answer id " + answer.getId()
                            + " doesn't match id to be updated: " + id + ".");
            return "error-page";
        }
        answerService.saveAnswer(answer);
        return "redirect:/answer/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteAnswer(@PathVariable(name = "id") Long id) {
        answerService.deleteAnswer(id);
        return "redirect:/answer/list";
    }

    @PostMapping(value = "/submit", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String saveAnswer(@RequestParam MultiValueMap<String, String> values, Model model) {

        Long testId = 0L;

        Set<Long> answerIds = values.values().stream()
                .flatMap(Collection::stream)
                .map(Long::parseLong)
                .collect(Collectors.toSet());

        Map<Long, Answer> userAnswers = new HashMap<>();
        Map<Long, Answer> correctAnswers = new HashMap<>();

        for (String questionId : values.keySet()) {

            Question question = questionService.getQuestion(Long.parseLong(questionId));
            testId = question.getTest().getId();
            boolean ok = true;

            List<Long> wrong = new ArrayList<>();

            for (Answer answer : question.getAnswers()) {
                if(answerIds.contains(answer.getId())){
                    userAnswers.put(question.getId(), answer);
                }

                if(answer.isCorrect()){
                    correctAnswers.put(question.getId(), answer);
                }

                if (answer.isCorrect() && !answerIds.contains(answer.getId())) {
                    ok = false;
                    wrong.add(answer.getId());
                }
                if (!answer.isCorrect() && answerIds.contains(answer.getId())) {
                    ok = false;
                    wrong.add(answer.getId());
                }

                System.out.println("hello " + questionId + "-" + answer.getId());



            }
            System.out.println(questionId + ": " + ok + ", wrong: " + wrong);

        }

        Test test = newTestService.getTest(testId); // == new Test(1l, "Test 1", questions())

        model.addAttribute("testObject", test);
        model.addAttribute("userAnswers", userAnswers);
        model.addAttribute("correctAnswers", correctAnswers);

        return "answers";
    }


    @GetMapping("/question/assign/{id}")
    public String assignQuestion(@PathVariable(name = "id") Long id, Model model) {
        Answer answer = answerService.getAnswer(id);
        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("answer", answer);
        model.addAttribute("questionList", questionList);
        return "assign-question";
    }

    @PostMapping("question/assign")
    public String saveQuestionAssignment(@RequestParam("answerId") Long answerId, @RequestParam("questionId") Long
            questionId) {
        Question question = questionService.getQuestion(questionId);
        Answer answer = answerService.getAnswer(answerId);
//        test.setQuestion(question);
//        newTestService.saveTest(test);
        answer.setQuestion(question);
        answerService.saveAnswer(answer);
        return "redirect:/answer/list";
    }

    @RequestMapping("/question/remove/{id}")
    public String removeQuestion(@PathVariable(name = "id") Long answerId) {
        Answer answer = answerService.getAnswer(answerId);
        answer.setQuestion(null);
        answerService.saveAnswer(answer);
        return "redirect:/answer/list";
    }


}

