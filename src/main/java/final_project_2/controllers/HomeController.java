package final_project_2.controllers;

import final_project_2.models.Test;
import final_project_2.services.NewTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    NewTestService newTestService;

    @GetMapping("/hello")
    public String greeting() {
        return "Hello and welcome!";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String vewHomePage(Model model) {
        final List<Test> testList = newTestService.getAllTests();
        model.addAttribute("testList", testList);
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
