package it.ntesConsulting.DNTServizi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Risorsa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;	
    private String nome;
    
    
    
    //costruttore de default
	public Risorsa() {
		super();
	}

   // costruttore con parametri
	public Risorsa(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
   
}
