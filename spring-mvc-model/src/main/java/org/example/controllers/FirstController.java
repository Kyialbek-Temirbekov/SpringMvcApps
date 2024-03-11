package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam("a") double a,
                            @RequestParam("b") double b,
                            @RequestParam("action") String action,
                            Model model) {
        double result;
        switch(action) {
            case "addition": result = a + b;
                break;
            case "subtraction": result = a - b;
                break;
            case "multiplication": result = a * b;
                break;
            case "division": result = a / b;
                break;
            default: result = 0;
                break;
        }
        model.addAttribute("result","Result: " + Double.toString(result));

        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }
}
