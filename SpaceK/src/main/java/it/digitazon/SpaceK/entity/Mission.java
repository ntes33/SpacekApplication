package it.digitazon.SpaceK.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import it.digitazon.SpaceK.enums.MissionStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;




@Entity
@Data 
@Table(name="missions")
public class Mission {

	   @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   private Long id;
	   
	   //Nome della missione
	   private String name;
	   
	   //Descrizione della missione
	   private String description;
	   
	   //Stato della missione - pianicata, in corso, completata o annullata
	   @Enumerated(EnumType.STRING)
	   private MissionStatus status;
	   
	   // Livello di complessità (da 1 a 5)
	   @Column(name = "complexity_level")
	   private Integer complexityLevel;
	    
	   // Data di inizio della missione
	   @Column(name = "start_date")
	   private LocalDate startDate;

	   // Data stimata di fine (calcolata in base al livello di complessità)
	   @Column(name = "end_date")
	   private LocalDate endDate;

	   //Data di creazione (impostata automaticamente)
	   @Column(name="created_at", updatable = false)
	   private final LocalDateTime createdAt=LocalDateTime.now();
	   
	   @OneToMany(mappedBy="mission",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	   private List<Astronaut>astronautList;
	   
	   @OneToMany(mappedBy="mission",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	   private List<Resource>resourceList;
	   
	
}
