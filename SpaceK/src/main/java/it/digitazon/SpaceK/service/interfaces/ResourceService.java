package it.digitazon.SpaceK.service.interfaces;

import org.springframework.data.domain.Pageable;

import it.digitazon.SpaceK.dto.Response;
import it.digitazon.SpaceK.enums.ResourceStatus;

public interface ResourceService {
	    Response createResource(String name, String type,String status );
	    Response updateResource(Long resourceId,String name, String type,String status);
	    Response deleteResource(Long resourceId);
	    Response getResourceById(Long resourceId);
	    Response getAllResources();
	    Response getAllResourcesByMission(Long missionId);
	    Response assignResourceToMission(Long missionId, Long resourceId);
	    Response filterResource(ResourceStatus status,  Pageable pageable);
		
}
