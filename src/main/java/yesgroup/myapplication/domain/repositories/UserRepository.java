package yesgroup.myapplication.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yesgroup.myapplication.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByUsername(String username);
}
