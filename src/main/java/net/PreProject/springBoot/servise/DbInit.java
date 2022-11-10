package net.PreProject.springBoot.servise;

import net.PreProject.springBoot.model.Role;
import net.PreProject.springBoot.model.User;
import net.PreProject.springBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit {

    private final UserServiceImpl userService;
    private final RoleServiceimpl roleService;

    private final UserRepository userRepository;

    @Autowired
    public DbInit(UserServiceImpl userService, RoleServiceimpl roleService, UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void postConstruct() {
        User admin = new User("admin", "admin", 21, "admin@email", "admin");
        User user = new User("user", "user", 22, "user@email", "user");
        userService.saveUser(admin);
        userService.saveUser(user);
        Role roleAdmin = new Role(1L, "ROLE_ADMIN");
        Role roleUser = new Role(2L, "ROLE_USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleAdmin);
        admin.setRoles(roleSet);
        userService.saveUser(admin);
        roleSet.clear();
        roleSet.add(roleUser);
        user.setRoles(roleSet);
        userService.saveUser(user);


    }

    @PreDestroy
    private void preDestroy(){
        userService.deleteById(userRepository.findByFirstname("admin").getId());
        userService.deleteById(userRepository.findByFirstname("user").getId());
    }
}