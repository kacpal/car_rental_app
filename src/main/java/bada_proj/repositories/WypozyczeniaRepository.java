package bada_proj.repositories;

import bada_proj.entities.Klienci;
import bada_proj.entities.Wypozyczenia;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface WypozyczeniaRepository extends CrudRepository<Wypozyczenia, Long> {

    List<Wypozyczenia> findAllByIdKlienta(Klienci idKlienta);

    Wypozyczenia findFirstByDataWypozyczenia(Date date);
}