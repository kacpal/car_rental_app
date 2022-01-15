package bada_proj.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MARKI")
public class Marki {
    @Id
    @Column(name = "NR_MARKI", nullable = false)
    private Long id;

    @Column(name = "NAZWA", nullable = false, length = 30)
    private String nazwa;

    @Column(name = "OPIS", nullable = false, length = 300)
    private String opis;

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