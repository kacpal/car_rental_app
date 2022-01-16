package bada_proj.repositories;

import bada_proj.entities.Klienci;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface KlienciRepository extends CrudRepository<Klienci, Long> {

    Klienci findKlienciByNazwaUzytkownika(String nazwaUzytkownika);

    @Modifying
    @Transactional
    @Query("UPDATE Klienci SET imie= :imie, nazwisko= :nazwisko WHERE id = :id")
    int update(@Param("id") long id, @Param("imie") String imie, @Param("nazwisko") String nazwisko);
}