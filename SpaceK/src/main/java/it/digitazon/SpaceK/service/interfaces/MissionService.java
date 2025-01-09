package it.digitazon.SpaceK.service.interfaces;

import org.springframework.data.domain.Pageable;

import it.digitazon.SpaceK.dto.MissionDto;
import it.digitazon.SpaceK.dto.Response;
import it.digitazon.SpaceK.enums.MissionStatus;

public interface MissionService {
	    Response createMission(MissionDto missionRequest);
	    Response startMission(Long missionId);
	    Response updateMission(Long missionId, MissionDto missionRequest);
	    Response getAllMissions();
	    Response getMissionById(Long missionId);
	    Response deleteMission(Long missionId);
	    Response getMissionDetails(Long missionId);
	    Response changeMissionStatus(Long missionId);
	    Response calculateEffectiveCompletionTime(Long missionId);
	    Response filterMission(MissionStatus status,  Pageable pageable);
	    
}
