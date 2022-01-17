package bada_proj.repositories;

import bada_proj.entities.Modele;
import bada_proj.entities.Pojazdy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PojazdyRepository extends CrudRepository<Pojazdy, Long> {

    List<Pojazdy> findPojazdyByNrModelu(Modele nr_modelu);

    List<Pojazdy> findPojazdyByRokProdukcji(Long rok_produkcji);

    List<Pojazdy> findPojazdyByIloscMiejsc(Long ilosc_miejsc);

    List<Pojazdy> findPojazdyByRodzajPaliwa(String rodzaj_paliwa);

    @Query(value = "SELECT * FROM Pojazdy WHERE NR_POJAZDU IN (SELECT WYPOZYCZENIA.NR_POJAZDU FROM WYPOZYCZENIA WHERE DATA_ZWROTU < :date) OR NR_POJAZDU NOT IN (SELECT WYPOZYCZENIA.NR_POJAZDU FROM WYPOZYCZENIA)", nativeQuery = true)
    List<Pojazdy> findAvailableAfter(String date);

    Pojazdy findFirstById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Pojazdy SET rokProdukcji= :rokProdukcji, rodzajPaliwa= :rodzajPaliwa, iloscMiejsc= :iloscMiejsc WHERE id = :id")
    int update(@Param("id") long id, @Param("rokProdukcji") long rokProdukcji, @Param("rodzajPaliwa") String rodzajPaliwa, @Param("iloscMiejsc") long iloscMiejsc);
}