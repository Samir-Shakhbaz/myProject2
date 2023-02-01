package final_project_2.controllers;

import final_project_2.models.Answer;
import final_project_2.models.CorrectAnswer;
import final_project_2.services.CorrectAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/correct")
public class CorrectAnswerController {

    @Autowired
    CorrectAnswerService correctAnswerService;

    @PostMapping(value = "/save")
    public String saveCorrectAnswer(@ModelAttribute("correct_answer") CorrectAnswer correctAnswer) {
        correctAnswerService.saveCorrectAnswer(correctAnswer);
        return "redirect:/correct-answer/list";
    }

    @GetMapping("/list")
    public String showCorrectAnswerListPage(Model model) {
        List<CorrectAnswer> correctAnswerList = correctAnswerService.getAllCorrectAnswers();
        model.addAttribute("correctanswerList", correctAnswerList);
        return "correct-answer-list";
    }

    @GetMapping("/new")
    public String ShowNewCorrectAnswerPage(Model model) {
        CorrectAnswer correctAnswer = new CorrectAnswer();
        model.addAttribute("correct_answer", correctAnswer);
        return "new-correct-answer";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit-correct-answer");
        CorrectAnswer correctAnswer = correctAnswerService.getCorrectAnswer(id);
        mav.addObject("correct_answer", correctAnswer);
        return mav;
    }

    @PostMapping("/update/{id}")
    public String updateAnswer(@PathVariable(name = "id") Long id, @ModelAttribute("correct_answer") CorrectAnswer correctAnswer, Model model) {
        if (!id.equals(correctAnswer.getId())) {
            model.addAttribute("message",
                    "Cannot update, answer id " + correctAnswer.getId()
                            + " doesn't match id to be updated: " + id + ".");
            return "error-page";
        }
        correctAnswerService.saveCorrectAnswer(correctAnswer);
        return "redirect:/correct-answer/list";
    }

}
