package it.ntesConsulting.DNTServizi.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("Cliente")
public class Cliente extends Dipendente {

	//costruttore di default o senza parametri
    public Cliente() {
        super(); 
    }

    //costruttore con parametri
	public Cliente(Long id, String nome, String cognome, String email, String telefono) {
		super(id, nome, cognome, email, telefono);
		
	}

   
}


