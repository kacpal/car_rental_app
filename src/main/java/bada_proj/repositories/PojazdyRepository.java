package bada_proj.repositories;

import bada_proj.entities.Modele;
import bada_proj.entities.Pojazdy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface PojazdyRepository extends CrudRepository<Pojazdy, Long> {

    List<Pojazdy> findPojazdyByNrModelu(Modele nr_modelu);

    List<Pojazdy> findPojazdyByRokProdukcji(Long rok_produkcji);

    List<Pojazdy> findPojazdyByIloscMiejsc(Long ilosc_miejsc);

    List<Pojazdy> findPojazdyByRodzajPaliwa(String rodzaj_paliwa);
}