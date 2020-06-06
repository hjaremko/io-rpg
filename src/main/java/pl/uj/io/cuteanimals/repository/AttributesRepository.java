package pl.uj.io.cuteanimals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uj.io.cuteanimals.model.entity.Attributes;

@Repository
public interface AttributesRepository
        extends JpaRepository<Attributes, Integer>, GeneralRepository<Attributes> {}
