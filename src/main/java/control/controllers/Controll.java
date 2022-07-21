package control.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/start")
public class Controll {

    @GetMapping("/hello-start")
    public String hello(Model model){
        model.addAttribute("name", "Tony!");
        return "first/hello_start";
    }
}
