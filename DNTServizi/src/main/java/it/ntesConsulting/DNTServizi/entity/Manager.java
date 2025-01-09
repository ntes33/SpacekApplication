package it.ntesConsulting.DNTServizi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("Manager")
@Entity
public class Manager extends Dipendente {

    // L'area o il team sotto la gestione del Direttore
    @Column
    private String areaResponsabilita;

    // Costruttore di default
    public Manager() {
        super();
    }

    // Costruttore con parametri
    public Manager(Long id, String nome, String cognome, String email, String telefono, String areaResponsabilita) {
        super(id, nome, cognome, email, telefono);
        this.areaResponsabilita = areaResponsabilita;
    }

    // Getter per areaResponsabilita
    public String getAreaResponsabilita() {
        return areaResponsabilita;
    }

    // Setter per areaResponsabilita
    public void setAreaResponsabilita(String areaResponsabilita) {
        this.areaResponsabilita = areaResponsabilita;
    }

    // Override di toString (opzionale, per il debug)
    @Override
    public String toString() {
        return super.toString() + ", areaResponsabilita='" + areaResponsabilita + "'";
    }
}

