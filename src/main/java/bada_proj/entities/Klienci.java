package bada_proj.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "KLIENCI")
public class Klienci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_KLIENTA", nullable = false)
    private Long id;

    @Column(name = "IMIE", nullable = false, length = 20)
    private String imie;

    @Column(name = "NAZWISKO", nullable = false, length = 30)
    private String nazwisko;

    @Column(name = "DATA_URODZENIA", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataUrodzenia;

    @Column(name = "NR_PRAWA_JAZDY", nullable = false, length = 30)
    private String nrPrawaJazdy;

    @Column(name = "NR_DOKUMENTU_TOZSAMOSCI", nullable = false, length = 30)
    private String nrDokumentuTozsamosci;

    @ManyToOne(optional = false)
    @JoinColumn(name = "NR_ADRESU", nullable = false)
    private Adresy nrAdresu;

    @Column(name = "HASLO", nullable = false, length = 64)
    private String haslo;

    @Column(name = "NAZWA_UZYTKOWNIKA", nullable = false, length = 30)
    private String nazwaUzytkownika;

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public void setNazwaUzytkownika(String nazwaUzytkownika) {
        this.nazwaUzytkownika = nazwaUzytkownika;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Adresy getNrAdresu() {
        return nrAdresu;
    }

    public void setNrAdresu(Adresy nrAdresu) {
        this.nrAdresu = nrAdresu;
    }

    public String getNrDokumentuTozsamosci() {
        return nrDokumentuTozsamosci;
    }

    public void setNrDokumentuTozsamosci(String nrDokumentuTozsamosci) {
        this.nrDokumentuTozsamosci = nrDokumentuTozsamosci;
    }

    public String getNrPrawaJazdy() {
        return nrPrawaJazdy;
    }

    public void setNrPrawaJazdy(String nrPrawaJazdy) {
        this.nrPrawaJazdy = nrPrawaJazdy;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Klienci{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", nrPrawaJazdy='" + nrPrawaJazdy + '\'' +
                ", nrDokumentuTozsamosci='" + nrDokumentuTozsamosci + '\'' +
                ", nrAdresu=" + nrAdresu +
                ", haslo='" + haslo + '\'' +
                ", nazwaUzytkownika='" + nazwaUzytkownika + '\'' +
                '}';
    }
}