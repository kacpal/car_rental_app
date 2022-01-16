package bada_proj.repositories;

import bada_proj.entities.Poczty;
import org.springframework.data.repository.CrudRepository;

public interface PocztyRepository extends CrudRepository<Poczty, Long> {
    Poczty findFirstByIdIsNot(Long id);
}