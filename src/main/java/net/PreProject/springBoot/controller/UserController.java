package net.PreProject.springBoot.controller;

import net.PreProject.springBoot.model.User;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {



    @GetMapping("/user")
    public String showUserInfo(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                               Model model) {
        model.addAttribute("user", principal);
        return "fragments/user";
    }
}
