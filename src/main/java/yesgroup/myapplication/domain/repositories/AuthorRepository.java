package yesgroup.myapplication.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yesgroup.myapplication.domain.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findByName(String name);
}
