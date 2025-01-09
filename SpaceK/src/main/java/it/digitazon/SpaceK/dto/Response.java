package it.digitazon.SpaceK.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	    private int status;
	    private String message;
	    private final LocalDateTime timestamp = LocalDateTime.now();

	    private int totalPage ;
	    private Long totalElement;
	    private String  estimatedEndDate;
	    private String effectiveEndDate;
	    private String endDate;
	    private MissionDto mission;
	    private List<MissionDto>missions;

	    private AstronautDto astronaut;
	    private List<AstronautDto> astronauts;

	    private ResourceDto resource;
	    private List<ResourceDto> resources;

	    
}
