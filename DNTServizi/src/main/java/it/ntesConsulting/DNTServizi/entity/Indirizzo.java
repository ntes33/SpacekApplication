package it.ntesConsulting.DNTServizi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "indirizzi")
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String città;
    private String via;
    private String cap;

    // Costruttore di default
    public Indirizzo() {
        super();
    }

    // Costruttore con parametri
    public Indirizzo(Long id, String città, String via, String cap) {
        super();
        this.id = id;
        this.città = città;
        this.via = via;
        this.cap = cap;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    @Override
    public String toString() {
        return "Indirizzo [id=" + id + ", città=" + città + ", via=" + via + ", cap=" + cap + "]";
    }
}

