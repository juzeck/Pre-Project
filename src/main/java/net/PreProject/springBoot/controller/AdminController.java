package net.PreProject.springBoot.controller;

import net.PreProject.springBoot.model.Role;
import net.PreProject.springBoot.model.User;
import net.PreProject.springBoot.servise.RoleServiceimpl;
import net.PreProject.springBoot.servise.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserServiceImpl userService;
    private final RoleServiceimpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceimpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping({"/admin", "list"})
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("allRoles", roleService.findAll());

        model.addAttribute("showUserProfile",
                model.containsAttribute("user") && !((User) model.getAttribute("user")).isNew());
        model.addAttribute("showNewUserForm",
                model.containsAttribute("user") && ((User) model.getAttribute("user")).isNew());
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }

        return "fragments/admin-page";
    }
    @GetMapping("/admin/{id}/profile")
    public String showUserProfileModal(@PathVariable("id") Long userId, Model model, RedirectAttributes attributes) {
        try {
            model.addAttribute("allRoles", roleService.findAll());
            model.addAttribute("user", userService.findById(userId));
            return "fragments/edit";
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("/admin")
    public String insertUser(@Valid @ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/admin";
    }


    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    @PatchMapping()
    public String updateUser(@Valid @ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @DeleteMapping("")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.deleteById(user.getId());
        return "redirect:/admin";
    }


}
