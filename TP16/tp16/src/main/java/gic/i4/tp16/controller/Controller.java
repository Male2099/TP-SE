package gic.i4.tp16.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class Controller {
    @GetMapping(path = "/")
    public String task1(){
        return "<h1>Holy moly it work!!!!</h1>";
    }
    
    @GetMapping(path = "/tp16/task2")
    public ModelAndView task2(){
        return new ModelAndView("task2");
    }
    
    @GetMapping(path = "/tp16/task3")
    public ModelAndView task3(){
        return new ModelAndView("task3");
    }
    
    @GetMapping(path = "/tp16/task4")
    public ModelAndView task4(){
        return new ModelAndView("task4");
    }
    
    @PostMapping("/tp16/task5")
    public Object index(String username, String password) {
        if (username.equals("male") && password.equals("123456"))
            return new ModelAndView("task5");
        return new RedirectView("/tp16/task2");
    }

}
