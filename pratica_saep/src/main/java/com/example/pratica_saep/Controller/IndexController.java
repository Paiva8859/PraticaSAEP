package com.example.pratica_saep.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // Página inicial - rota "/"
    @GetMapping("/")
    public String home() {
        return "index";
    }
}

