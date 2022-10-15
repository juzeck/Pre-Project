package net.PreProject.springBoot.repository;

import net.PreProject.springBoot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleById(Long id);
}