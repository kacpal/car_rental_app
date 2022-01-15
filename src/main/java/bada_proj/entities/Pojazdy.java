package bada_proj.entities;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "POJAZDY")
public class Pojazdy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_POJAZDU", nullable = false)
    private Long id;

    @Column(name = "GENERACJA", length = 20)
    private String generacja;

    @Column(name = "ROK_PRODUKCJI", nullable = false)
    private Long rokProdukcji;

    @Column(name = "NR_REJESTRACYJNY", length = 7)
    private String nrRejestracyjny;

    @Column(name = "RODZAJ_PALIWA", nullable = false, length = 15)
    private String rodzajPaliwa;

    @Column(name = "ILOSC_MIEJSC", nullable = false)
    private Long iloscMiejsc;

    @ManyToOne(optional = false)
    @JoinColumn(name = "NR_WYPOZYCZALNI", nullable = false)
    private Wypozyczalnie nrWypozyczalni;

    @ManyToOne(optional = false)
    @JoinColumn(name = "NR_MODELU", nullable = false)
    private Modele nrModelu;

    public Modele getNrModelu() {
        return nrModelu;
    }

    public void setNrModelu(Modele nrModelu) {
        this.nrModelu = nrModelu;
    }

    public Wypozyczalnie getNrWypozyczalni() {
        return nrWypozyczalni;
    }

    public void setNrWypozyczalni(Wypozyczalnie nrWypozyczalni) {
        this.nrWypozyczalni = nrWypozyczalni;
    }

    public Long getIloscMiejsc() {
        return iloscMiejsc;
    }

    public void setIloscMiejsc(Long iloscMiejsc) {
        this.iloscMiejsc = iloscMiejsc;
    }

    public String getRodzajPaliwa() {
        return rodzajPaliwa;
    }

    public void setRodzajPaliwa(String rodzajPaliwa) {
        this.rodzajPaliwa = rodzajPaliwa;
    }

    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

    public Long getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(Long rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public String getGeneracja() {
        return generacja;
    }

    public void setGeneracja(String generacja) {
        this.generacja = generacja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pojazdy{" +
                "id=" + id +
                ", generacja='" + generacja + '\'' +
                ", rokProdukcji=" + rokProdukcji +
                ", nrRejestracyjny='" + nrRejestracyjny + '\'' +
                ", rodzajPaliwa='" + rodzajPaliwa + '\'' +
                ", iloscMiejsc=" + iloscMiejsc +
                ", nrWypozyczalni=" + nrWypozyczalni +
                ", nrModelu=" + nrModelu +
                '}';
    }
}