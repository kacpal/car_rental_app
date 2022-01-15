package bada_proj.entities;

import javax.persistence.*;

@Entity
@Table(name = "MODELE")
public class Modele {
    @Id
    @Column(name = "NR_MODELU", nullable = false)
    private Long id;

    @Column(name = "NAZWA", nullable = false, length = 30)
    private String nazwa;

    @Column(name = "OPIS", nullable = false, length = 400)
    private String opis;

    @ManyToOne(optional = false)
    @JoinColumn(name = "NR_MARKI", nullable = false)
    private Marki nrMarki;

    public Marki getNrMarki() {
        return nrMarki;
    }

    public void setNrMarki(Marki nrMarki) {
        this.nrMarki = nrMarki;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}