package control.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String hello(){
        return "first/hello";
    }

    @GetMapping("/bye")
    public String bye(){
        return "first/bye";
    }
}
