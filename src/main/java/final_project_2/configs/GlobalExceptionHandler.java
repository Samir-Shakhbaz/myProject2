package final_project_2.configs;


import final_project_2.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView questionNotFound(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView globalException(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = Throwable.class)
    public ModelAndView myGlobalException(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        return mav;
    }

}
