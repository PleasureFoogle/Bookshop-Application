package yesgroup.myapplication.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yesgroup.myapplication.domain.entities.Bookshop;

import java.util.List;

@Repository
public interface BookshopRepository extends JpaRepository<Bookshop,Integer> {
    Bookshop findByName(String name);
}
