package bada_proj.entities;

import javax.persistence.*;

@Entity
@Table(name = "POCZTY")
public class Poczty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_POCZTY", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}