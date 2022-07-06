package control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controll {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name", "Tony!");
        return "hello";
    }
}
