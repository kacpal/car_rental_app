package bada_proj.repositories;

import bada_proj.entities.Adresy;
import org.springframework.data.repository.CrudRepository;

public interface AdresyRepository extends CrudRepository<Adresy, Long> {
    Adresy findAdresyById(Long id);
}