package final_project_2.controllers;

import final_project_2.models.Answer;
import final_project_2.models.Question;
import final_project_2.repositories.QuestionRepository;
import final_project_2.services.AnswerService;
import final_project_2.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerService answerService;

    @GetMapping("/new")
    public String showNewQuestionPage(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return "new-question";
    }

    @GetMapping("/anew")
    public String showANewQuestionPage(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return "a-new-question";
    }

    @PostMapping(value = "/save")
    public String saveQuestion(@ModelAttribute("question") Question question) {
        questionService.saveQuestion(question);
        return "redirect:/question/list";
    }

    @GetMapping("/list")
    public String showQuestionListPage(Model model) {
        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questionList", questionList);
        return "question-list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditQuestionPage(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-question");
        Question question = questionService.getQuestion(id);
        modelAndView.addObject("question", question);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateQuestion(@PathVariable(name = "id") Long id, @ModelAttribute("question") Question question, Model model) {
        if (!id.equals(question.getId())) {
            model.addAttribute("message",
                    "Cannot update, question id " + question.getId()
                            + " doesn't match id to be updated: " + id + ".");
            return "error-page";
        }
        questionService.saveQuestion(question);
        return "redirect:/question/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable(name = "id") Long id) {
        questionService.deleteQuestion(id);
        return "redirect:/question/list";
    }
}
