package bada_proj.entities;

import javax.persistence.*;

@Entity
@Table(name = "WYPOZYCZALNIE")
public class Wypozyczalnie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_WYPOZYCZALNI", nullable = false)
    private Long id;

    @Column(name = "NAZWA", nullable = false, length = 30)
    private String nazwa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "NR_ADRESU", nullable = false)
    private Adresy nrAdresu;

    public Adresy getNrAdresu() {
        return nrAdresu;
    }

    public void setNrAdresu(Adresy nrAdresu) {
        this.nrAdresu = nrAdresu;
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