package bada_proj.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PRACOWNICY")
public class Pracownicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_PRACOWNIKA", nullable = false)
    private Long id;

    @Column(name = "IMIE", nullable = false, length = 20)
    private String imie;

    @Column(name = "NAZWISKO", nullable = false, length = 30)
    private String nazwisko;

    @Column(name = "DATA_URODZENIA", nullable = false)
    private LocalDate dataUrodzenia;

    @Column(name = "PESEL", length = 11)
    private String pesel;

    @Column(name = "PLEC", nullable = false)
    private String plec;

    @ManyToOne(optional = false)
    @JoinColumn(name = "NR_WYPOZYCZALNI", nullable = false)
    private Wypozyczalnie nrWypozyczalni;

    @ManyToOne(optional = false)
    @JoinColumn(name = "NR_STANOWISKA", nullable = false)
    private Stanowiska nrStanowiska;

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

    public Stanowiska getNrStanowiska() {
        return nrStanowiska;
    }

    public void setNrStanowiska(Stanowiska nrStanowiska) {
        this.nrStanowiska = nrStanowiska;
    }

    public Wypozyczalnie getNrWypozyczalni() {
        return nrWypozyczalni;
    }

    public void setNrWypozyczalni(Wypozyczalnie nrWypozyczalni) {
        this.nrWypozyczalni = nrWypozyczalni;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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
}