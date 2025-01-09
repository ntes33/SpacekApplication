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
public class ResourceDto {
	
	private Long id;
	
	//nome tecnico della risorsa
	private String name;
	
	//Tipo di risorsa - razzo, satellite, rover
	private String type;
	
	//Stato della risorsa - disponibile, in uso, manutenzione
	private String status;
	
	//ID della missione a cui è assegnata 
	private MissionDto  missionDto;
}
