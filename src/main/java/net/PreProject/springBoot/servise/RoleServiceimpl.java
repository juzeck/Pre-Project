package net.PreProject.springBoot.servise;

import net.PreProject.springBoot.model.Role;
import net.PreProject.springBoot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceimpl {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceimpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public Role getRoleById(Long id){
        return roleRepository.getRoleById(id);
    }
}
