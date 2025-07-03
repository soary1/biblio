package com.example.biblio.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuccessController {

    @GetMapping("/success")
    public String success() {
        // Va chercher la vue success.jsp dans /WEB-INF/views/
        return "success";
        
    }
}
