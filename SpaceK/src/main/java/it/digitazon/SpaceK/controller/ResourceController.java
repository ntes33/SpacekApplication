package it.digitazon.SpaceK.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.digitazon.SpaceK.dto.Response;
import it.digitazon.SpaceK.enums.ResourceStatus;
import it.digitazon.SpaceK.service.interfaces.ResourceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {
	 
	    private final ResourceService resourceServiceImpl;
	
	    @PostMapping("/create")
	    public ResponseEntity<Response> createResource(
	            @RequestParam String name,
	            @RequestParam String type,
	            @RequestParam String status
	    ) {	        
	        return ResponseEntity.ok(resourceServiceImpl.createResource(name, type, status));
	    }

	    
	    
	    @PutMapping("/update")
	    public ResponseEntity<Response> updateResource(
	            @RequestParam Long resourceId,
	            @RequestParam(required = false) String name,
	            @RequestParam(required = false) String type,
	            @RequestParam(required = false) String status
	    ) {
	        return ResponseEntity.ok(resourceServiceImpl.updateResource(resourceId,name,type, status));
	    }

	    
	    @DeleteMapping("/delete/{resourceId}")
	    public ResponseEntity<Response> deleteResource(@PathVariable Long resourceId) {
	        return ResponseEntity.ok(resourceServiceImpl.deleteResource(resourceId));
	    }

	    @GetMapping("/get-by-id/{resourceId}")
	    public ResponseEntity<Response> getResourceById(@PathVariable Long resourceId) {	        
	        return ResponseEntity.ok(resourceServiceImpl.getResourceById(resourceId));
	    }

	    @GetMapping("/get-all")
	    public ResponseEntity<Response> getAllResources() {
	        return ResponseEntity.ok(resourceServiceImpl.getAllResources());
	    }

	    @GetMapping("/get-by-mission/{missionId}")
	    public ResponseEntity<Response> getAllResourcesByMission(@PathVariable Long missionId) {
	        return ResponseEntity.ok(resourceServiceImpl.getAllResourcesByMission(missionId));
	    }
	    
	    @PutMapping("/missions/{missionId}/resources/{resourceId}")
	    public ResponseEntity<Response> assignResourceToMission(
	            @PathVariable Long missionId,
	            @PathVariable Long resourceId
	    ) {
	        
	        return ResponseEntity.ok(resourceServiceImpl.assignResourceToMission(missionId, resourceId));
	    }

	    /*
	     * consente di filtrare risorse in base al loro stato e  
	     * ottenere i risultati in formato paginato.
	     * 
	     */
	    @GetMapping("/filter-resource-by-status")
	    public ResponseEntity<Response> filterResource(	
				@RequestParam(required = false) String status,				
				@RequestParam(defaultValue = "0") int page,
				@RequestParam(defaultValue = "1000") int size
		) {
			Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
			ResourceStatus resourceStatus = status != null ? ResourceStatus.valueOf(status.toUpperCase()) : null;

			return ResponseEntity.ok(resourceServiceImpl.filterResource(resourceStatus,pageable));

		}

}
