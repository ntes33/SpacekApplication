package it.digitazon.SpaceK.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
public class MissionDto {
	
	   private Long id;
	   
	   private String name;
	   
	   private String description;
	   
	   private String status;
	   
	   private Integer complexityLevel;
	    
	   private LocalDate startDate;

	   private LocalDate endDate;
	   
	   private LocalDateTime createdAt;
	
	   private List<AstronautDto>astronautList;
	   
	   private List<ResourceDto>resourceList;
	
	
}
