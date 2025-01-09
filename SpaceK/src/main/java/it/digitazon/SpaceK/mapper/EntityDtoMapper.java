package it.digitazon.SpaceK.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import it.digitazon.SpaceK.dto.AstronautDto;
import it.digitazon.SpaceK.dto.MissionDto;
import it.digitazon.SpaceK.dto.ResourceDto;
import it.digitazon.SpaceK.entity.Astronaut;
import it.digitazon.SpaceK.entity.Mission;
import it.digitazon.SpaceK.entity.Resource;

@Component
public class EntityDtoMapper {
	 // Mission to DTO Basic
    public MissionDto mapMissionToDtoBasic(Mission mission) {
        MissionDto missionDto = new MissionDto();
        missionDto.setId(mission.getId());
        missionDto.setName(mission.getName());
        missionDto.setDescription(mission.getDescription());
        missionDto.setStatus(mission.getStatus().name());
        missionDto.setComplexityLevel(mission.getComplexityLevel());
        missionDto.setStartDate(mission.getStartDate());
        missionDto.setEndDate(mission.getEndDate());
        missionDto.setCreatedAt(mission.getCreatedAt());
        return missionDto;
    }

    // Astronaut to DTO Basic
    public AstronautDto mapAstronautToDtoBasic(Astronaut astronaut) {
        AstronautDto astronautDto = new AstronautDto();
        astronautDto.setId(astronaut.getId());
        astronautDto.setName(astronaut.getName());
        astronautDto.setSpecialty(astronaut.getSpecialty().name());
        astronautDto.setAvailable(astronaut.isAvailable());
        return astronautDto;
    }

    // Resource to DTO Basic
    public ResourceDto mapResourceToDtoBasic(Resource resource) {
        ResourceDto resourceDto = new ResourceDto();
        resourceDto.setId(resource.getId());
        resourceDto.setName(resource.getName());
        resourceDto.setType(resource.getType().name());
        resourceDto.setStatus(resource.getStatus().name());
        return resourceDto;
    }

    // Mission to DTO with Astronauts and Resources
    public MissionDto mapMissionToDtoWithDetails(Mission mission) {
        MissionDto missionDto = mapMissionToDtoBasic(mission);

        if (mission.getAstronautList() != null && !mission.getAstronautList().isEmpty()) {
            missionDto.setAstronautList(
                    mission.getAstronautList().stream()
                            .map(this::mapAstronautToDtoBasic)
                            .collect(Collectors.toList())
            ); 
        }

        if (mission.getResourceList() != null && !mission.getResourceList().isEmpty()) {
            missionDto.setResourceList(
                    mission.getResourceList().stream()
                            .map(this::mapResourceToDtoBasic)
                            .collect(Collectors.toList())
            );
        }

        return missionDto;
    }

    /*
     * @Component


    public MissionDto mapMissionToDtoDetailed(Mission mission) {
        // Mappa i dettagli completi della missione
        MissionDto missionDto = new MissionDto();
        missionDto.setId(mission.getId());
        missionDto.setName(mission.getName());
        missionDto.setDescription(mission.getDescription());
        missionDto.setStatus(mission.getStatus());
        missionDto.setComplexityLevel(mission.getComplexityLevel());
        missionDto.setStartDate(mission.getStartDate());
        missionDto.setEndDate(mission.getEndDate());
        missionDto.setCreatedAt(mission.getCreatedAt());
        
        // Aggiungi eventuali dettagli aggiuntivi come risorse e astronauti, se applicabile
        if (mission.getResources() != null) {
            missionDto.setResources(
                mission.getResources().stream()
                        .map(resource -> new ResourceDto(resource.getId(), resource.getName(), resource.getType(), resource.getStatus()))
                        .collect(Collectors.toList())
            );
        }

        if (mission.getAstronauts() != null) {
            missionDto.setAstronauts(
                mission.getAstronauts().stream()
                        .map(astronaut -> new AstronautDto(astronaut.getId(), astronaut.getName(), astronaut.getSpecialty(), astronaut.isAvailable()))
                        .collect(Collectors.toList())
            );
        }

        return missionDto;
    }


     */
    
    
    
    
    
    
    // Astronaut to DTO with Mission
    public AstronautDto mapAstronautToDtoWithMission(Astronaut astronaut) {
        AstronautDto astronautDto = mapAstronautToDtoBasic(astronaut);

        if (astronaut.getMission() != null) {
            astronautDto.setMissionDto(mapMissionToDtoBasic(astronaut.getMission()));
        }

        return astronautDto;
    }

    // Resource to DTO with Mission
    public ResourceDto mapResourceToDtoWithMission(Resource resource) {
        ResourceDto resourceDto = mapResourceToDtoBasic(resource);

        if (resource.getMission() != null) {
            resourceDto.setMissionDto(mapMissionToDtoBasic(resource.getMission()));
        }

        return resourceDto;
    }
}

	   

