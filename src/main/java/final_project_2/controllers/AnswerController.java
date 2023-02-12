package final_project_2.controllers;

import final_project_2.models.Answer;
import final_project_2.models.Question;
import final_project_2.models.Test;
import final_project_2.models.User;
import final_project_2.services.AnswerService;
import final_project_2.services.NewTestService;
import final_project_2.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@Component
//all endpoints in this class begin with "/answer"
@RequestMapping("/answer")

public class AnswerController {

    //@Autowired class allow access to methods in those classes

    @Autowired
    NewTestService newTestService;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;


    @PostMapping(value = "/save")
    // here Model is received back from the view
    // then @ModelAttribute creates an answer based on the object
    // that was collected from the HTML page above
    public String saveAnswer(@ModelAttribute("answer") Answer answer) {
        answerService.saveAnswer(answer);
        return "redirect:/answer/list";
    }

    @GetMapping("/list")
    public String showAnswerListPage(Model model) {
        //The service to retrieve all the answers is called
        List<Answer> answerList = answerService.getAllAnswers();
        //After the answers are retrieved, the are stored in model and returned to the view
        model.addAttribute("answerList", answerList);
        return "answer-list";
    }

    @GetMapping("/new")

    public String ShowNewAnswerPage(Model model) {
        // New answer is created, it's empty
        Answer answer = new Answer();
        //Here it is put in the model and sent to the view
        model.addAttribute("answer", answer);
        return "new-answer";
    }

    @GetMapping("/edit/{id}")
    // The path variable "id" is used to pull an answer from the DB
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") Long id) {
        //The view name is passed to the constructor
        ModelAndView mav = new ModelAndView("edit-answer");
        Answer answer = answerService.getAnswer(id);
        mav.addObject("answer", answer);
        return mav;
    }

    @PostMapping("/update/{id}")
    // The path variable "id" is used to pull an answer from the DB
    public String updateAnswer(@PathVariable(name = "id") Long id, @ModelAttribute("answer") Answer answer, Model model) {
        //The "if" statement sets conditions in case no such id is found
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
    // The path variable "id" is used to pull an answer from the DB
    public String deleteAnswer(@PathVariable(name = "id") Long id) {
        answerService.deleteAnswer(id);
        return "redirect:/answer/list";
    }

    @PostMapping(value = "/submit", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String saveAnswer(@RequestParam MultiValueMap<String, String> values, Model model) {
        //here the data submitted by the user is returned from the view

        Long testId = 0L;

        //converting List to Set which allows access to unordered elements in a list
        Set<Long> answerIds = values.values().stream()
                .flatMap(Collection::stream)
                .map(Long::parseLong)
                .collect(Collectors.toSet());

        //creating hasmaps of all user answers and those that he answered correctly
        Map<Long, Answer> userAnswers = new HashMap<>();
        Map<Long, Answer> correctAnswers = new HashMap<>();

        //iterating through
        for (String questionId : values.keySet()) {

            Question question = questionService.getQuestion(Long.parseLong(questionId));
            testId = question.getTest().getId();
            boolean ok = true;

            List<Long> wrong = new ArrayList<>();
            Integer userScore = 0;
            //iterating through answers submitted
            for (Answer answer : question.getAnswers()) {
                boolean addToScore = true;

                System.out.println("THE USER'S SCORE IS BIG: " + userScore);

                //if the answer isn't null it's added to the list
                if (answerIds.contains(answer.getId())) {
                    userAnswers.put(question.getId(), answer);
                }

                //if the answer is correct and the box was checked, the answer is correct and it's added to the correct answers list
                if (answer.isCorrect()) {
                    correctAnswers.put(question.getId(), answer);
                }

                //if answer is correct but the box wasn't checked, the answer is wrong
                if (answer.isCorrect() && !answerIds.contains(answer.getId())) {
                    ok = false;
                    addToScore = false;
                    wrong.add(answer.getId());
                }

                //if the answer is not correct but the box was checked, the answer is wrong
                if (!answer.isCorrect() && answerIds.contains(answer.getId())) {
                    ok = false;
                    addToScore = false;
                    wrong.add(answer.getId());
                }

                if (addToScore) {
                    userScore++;
                }
                System.out.println("hello " + questionId + "-" + answer.getId());
                System.out.println("THE USER'S SCORE IS A LOT: " + userScore);
            }
            System.out.println(questionId + ": " + ok + ", wrong: " + wrong);

        }

        Test test = newTestService.getTest(testId);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.toString().equals("anonymousUser")) {
            User user = (User) principal;
            model.addAttribute("isAdmin", user.isAdmin());
        } else {
            model.addAttribute("isAdmin", false);
        }
        //models are created to display all user answers and correct answers
        model.addAttribute("testObject", test);
        model.addAttribute("userAnswers", userAnswers);
        model.addAttribute("correctAnswers", correctAnswers);

        return "answers";
    }

    @GetMapping("/question/assign/{id}")
    // The path variable "id" is used to pull the answer from the DB
    public String assignQuestion(@PathVariable(name = "id") Long id, Model model) {
        //The answer is pulled by id
        Answer answer = answerService.getAnswer(id);
        //The Service to retrieve all the questions is called
        List<Question> questionList = questionService.getAllQuestions();
        //Then the answer and the question list is added to the model and returned to the view
        model.addAttribute("answer", answer);
        model.addAttribute("questionList", questionList);
        return "assign-question";
    }

    @PostMapping("question/assign")
    public String saveQuestionAssignment(@RequestParam("answerId") Long answerId, @RequestParam("questionId") Long
            questionId) {
        Question question = questionService.getQuestion(questionId);
        Answer answer = answerService.getAnswer(answerId);
        answer.setQuestion(question);
        answerService.saveAnswer(answer);
        return "redirect:/answer/list";
    }

    @RequestMapping("/question/remove/{id}")
    //The path variable "id" is used to pull the answer from the DB
    public String removeQuestion(@PathVariable(name = "id") Long answerId) {
        Answer answer = answerService.getAnswer(answerId);
        //Then the question associated with the answer is set to null
        answer.setQuestion(null);
        //And new data is saved the DB
        answerService.saveAnswer(answer);
        return "redirect:/answer/list";
    }
}

