package it.digitazon.SpaceK.entity;

import it.digitazon.SpaceK.enums.AstronautSpecialisation;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data 
@Table(name="astronauts")
public class Astronaut {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//nome astronaut
	private String name;
	
	//Specializzazione - ingegnere, pilota, scienziato
	private AstronautSpecialisation specialty;
	
	//Disponibilità per nuove missioni
	private boolean isAvailable = true; 
	
	//ID della missione a cui è assegnata 
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="mission_id")
	private Mission  mission;
	
}
