package pl.uj.io.cuteanimals.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uj.io.cuteanimals.model.Item;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    List<Item> findAll();
}