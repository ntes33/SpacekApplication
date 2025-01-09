package it.digitazon.SpaceK.entity;

import it.digitazon.SpaceK.enums.ResourceStatus;
import it.digitazon.SpaceK.enums.ResourceType;
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
@Table(name="resources")
public class Resource {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//nome tecnico della risorsa 
	private String name;
	
	//Tipo di risorsa - razzo, satellite, rover
	private ResourceType type;
	
	//Stato della risorsa - disponibile, in uso, manutenzione
	private ResourceStatus status;
	
	//ID della missione a cui è assegnata 
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="mission_id")
	private Mission  mission;
	
	
	
	
	
}
