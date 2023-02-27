package final_project_2.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

//    @GetMapping ("/error")
//    public String handleError() {
//        return "error";
//    }

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}
