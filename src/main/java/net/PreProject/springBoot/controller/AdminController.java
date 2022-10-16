package net.PreProject.springBoot.controller;

import net.PreProject.springBoot.model.Role;
import net.PreProject.springBoot.model.User;
import net.PreProject.springBoot.servise.RoleServiceimpl;
import net.PreProject.springBoot.servise.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/admin/users")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }


    @GetMapping("/admin/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "/new";
    }

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    @PostMapping("/admin/users")
    @Transactional
    public String create(@RequestParam("roles") long[] roleId, @ModelAttribute("user") User user) {
        Set<Role> roleSet = new HashSet<>();
        for(long id : roleId){
            Role role = roleService.getRoleById(id);
            roleSet.add(role);
        }
        System.out.println("_____________");
        System.out.println(roleSet);
        user.setRoles(roleSet);
        userService.saveUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "show";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PatchMapping("/admin/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/users/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
