package bada_proj.entities;

import javax.persistence.*;

@Entity
@Table(name = "ADRESY")
public class Adresy {
    @Id
    @Column(name = "NR_ADRESU", nullable = false)
    private Long id;

    @Column(name = "MIASTO", nullable = false, length = 20)
    private String miasto;

    @Column(name = "ULICA", nullable = false, length = 20)
    private String ulica;

    @Column(name = "NR_LOKALU", nullable = false, length = 4)
    private String nrLokalu;

    @ManyToOne(optional = false)
    @JoinColumn(name = "NR_POCZTY", nullable = false)
    private Poczty nrPoczty;

    public Poczty getNrPoczty() {
        return nrPoczty;
    }

    public void setNrPoczty(Poczty nrPoczty) {
        this.nrPoczty = nrPoczty;
    }

    public String getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(String nrLokalu) {
        this.nrLokalu = nrLokalu;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}