package control.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FirstController {


    ///calculator?a=2&b=4&action=mult
    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a", required = false) Integer a, @RequestParam(value = "b", required = false) Integer b,
                             @RequestParam(value = "action", required = false) String action, Model model){
        Integer res = 0;
        System.out.println("a:" + a + " b:" + b + " "+action);
        switch (action){
            case "mult":
                res = a*b;
                break;
            case "add":
                res = a+b;
                break;
            case "sub":
                res = a-b;
                break;

        }
        model.addAttribute("res", res);
        return "first/calculator";
    }

    @GetMapping("/a")
    public String startPage(Model model){
        model.addAttribute("name", "Alex");
        return "first/start_page";
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        String name = request.getParameter("name");
        if (name != null){
            System.out.println(name);
        }
        return "first/hello";
    }

    @GetMapping("/bye")
    public String bye(@RequestParam(value = "b", required = false) String b, Model model){
        model.addAttribute("b", b);
        return "first/bye";
    }
}
