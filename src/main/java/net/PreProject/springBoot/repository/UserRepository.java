package net.PreProject.springBoot.repository;


import net.PreProject.springBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}