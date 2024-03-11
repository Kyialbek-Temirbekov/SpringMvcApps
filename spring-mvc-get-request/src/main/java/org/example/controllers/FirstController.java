package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

    // @RequestParam(value="name", required=false) String name
    @GetMapping("/hello")
    public String helloPage(@RequestParam("name") String name,
                            @RequestParam("surname") String surname) {
        System.out.println("Hello, " + name + " " + surname);
        return "first/hello";
    }
/*    public String helloPage(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("Hello, " + name + " " + surname);
        return "first/hello";
    }
 */
    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }
}
