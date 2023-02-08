package final_project_2.controllers;

import final_project_2.API.ArticleService;
import final_project_2.models.Test;
import final_project_2.models.User;
import final_project_2.services.NewTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ArticleService articleService;

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


    @GetMapping("/guest")
    public String vewHomePageGuest2(Model model) {
        final List<Test> testList = newTestService.getAllTests();
        model.addAttribute("testList", testList);
        return "guest-home";
    }

    @GetMapping("/")
    public String vewHomePageGuest(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.toString().equals("anonymousUser")){
            User user = (User) principal;
            model.addAttribute("isAdmin", user.isAdmin());
        } else {
            model.addAttribute("isAdmin", false);
        }

        final List<Test> testList = newTestService.getAllTests();
        model.addAttribute("testList", testList);
//        model.addAttribute("articleList", articleService.getMostPopular());
        model.addAttribute("article", articleService.getMostPopular().get(0));
        return "home";
    }

}
