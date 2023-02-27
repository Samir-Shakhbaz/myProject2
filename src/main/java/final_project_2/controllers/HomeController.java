package final_project_2.controllers;

import final_project_2.API.ArticleService;
import final_project_2.models.Test;
import final_project_2.models.User;
import final_project_2.services.NewTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

//    @GetMapping("/hello")
//    public String greeting() {
//        return "Hello and welcome!";
//    }

    @GetMapping("/about")
    public String about(Model model) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        System.out.println(authentication);
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (!principal.toString().equals("anonymousUser")) {
//            User user = (User) principal;
//            model.addAttribute("isAdmin", user.isAdmin());
//        } else {
//            model.addAttribute("isAdmin", false);
//        }


        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login?logout")
    public String logout() {
        return "home";
    }


    @GetMapping("/guest")
    public String vewHomePageGuest2(Model model) {
        final List<Test> testList = newTestService.getAllTests();
        model.addAttribute("testList", testList);
        return "guest-home";
    }

    @GetMapping("/")
    public String viewHomePageGuest(Model model) throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.toString().equals("anonymousUser")) {
            User user = (User) principal;
            model.addAttribute("isAdmin", user.isAdmin());
        } else {
            model.addAttribute("isAdmin", false);
        }

        final List<Test> testList = newTestService.getAllTests();
        model.addAttribute("testList", testList);
        model.addAttribute("searchArticle", articleService.getSearchJava().get(0));
        return "home";
    }
}
