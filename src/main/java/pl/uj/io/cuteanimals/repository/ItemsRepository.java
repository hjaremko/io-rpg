package pl.uj.io.cuteanimals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uj.io.cuteanimals.model.entity.Item;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer>, GeneralRepository<Item> {}
