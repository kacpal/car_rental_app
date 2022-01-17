package bada_proj.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "WYPOZYCZENIA")
public class Wypozyczenia {
    @Column(name = "DATA_ZWROTU")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataZwrotu;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_KLIENTA", nullable = false)
    private Klienci idKlienta;

    @OneToOne(optional = false)
    @JoinColumn(name = "NR_POJAZDU", nullable = false)
    private Pojazdy nrPojazdu;

    @Id
    @Column(name = "DATA_WYPOZYCZENIA", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataWypozyczenia;


    public LocalDate getDataZwrotu() {
        return dataZwrotu;
    }

    public void setDataZwrotu(LocalDate dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public Pojazdy getNrPojazdu() {
        return nrPojazdu;
    }

    public void setNrPojazdu(Pojazdy nrPojazdu) {
        this.nrPojazdu = nrPojazdu;
    }

    public LocalDate getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(LocalDate dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public void setIdKlienta(Klienci idKlienta) {
        this.idKlienta = idKlienta;
    }

    @Override
    public String toString() {
        return "Wypozyczenia{" +
                "dataZwrotu=" + dataZwrotu +
                ", idKlienta=" + idKlienta +
                ", nrPojazdu=" + nrPojazdu +
                ", dataWypozyczenia=" + dataWypozyczenia +
                '}';
    }
}