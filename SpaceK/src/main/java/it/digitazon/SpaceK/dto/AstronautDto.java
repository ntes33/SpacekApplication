package it.digitazon.SpaceK.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@AllArgsConstructor
@NoArgsConstructor
public class AstronautDto {
	
    private Long id;
	
	//nome astronaut
	private String name;
	
	//Specializzazione - ingegnere, pilota, scienziato
	private String specialty;
	
	//Disponibilità per nuove missioni
	private boolean isAvailable = true;
	
	//ID della missione a cui è assegnata 
	private MissionDto  missionDto;
}
