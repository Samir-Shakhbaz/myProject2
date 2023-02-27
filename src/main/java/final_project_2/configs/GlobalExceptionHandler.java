package final_project_2.configs;

import final_project_2.exceptions.NoSuchQuestionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchQuestionException.class)
    public ModelAndView questionNotFound(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("message", "no such question");
        return mav;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView globalException(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("message", "error message");
        mav.addObject("exception", "bad exception");
        return mav;
    }

}
