package it.digitazon.SpaceK.service.interfaces;

import org.springframework.data.domain.Pageable;

import it.digitazon.SpaceK.dto.Response;

public interface AstronautService {
	Response createAstronaut(String name, String specialty, boolean isAvailable);
    Response updateAstronaut(Long astronautId,String name, String specialty ,boolean isAvailable );
    Response deleteAstronaut(Long astronautId);
    Response getAstronautById(Long astronautId);
    Response getAllAstronauts();
    Response assignAstronautToMission(Long missionId, Long astronautId);
    Response filterAstronauts(Boolean available, Pageable pageable);
}
