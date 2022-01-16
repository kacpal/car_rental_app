package bada_proj.repositories;

import bada_proj.entities.Pracownicy;
import org.springframework.data.repository.CrudRepository;

public interface PracownicyRepository extends CrudRepository<Pracownicy, Long> {
    Pracownicy findPracownicyById(Long id);

    Pracownicy findPracownicyByNazwaUzytkownika(String nazwaUzytkownika);
}