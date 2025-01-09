package it.digitazon.SpaceK.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.digitazon.SpaceK.dto.MissionDto;
import it.digitazon.SpaceK.dto.Response;
import it.digitazon.SpaceK.enums.MissionStatus;
import it.digitazon.SpaceK.exception.InvalidRequestException;
import it.digitazon.SpaceK.service.interfaces.MissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@Slf4j
public class MissionController {
	
    private final MissionService missionService;
    
    
    @PostMapping("/create")
    public ResponseEntity<Response> createMission(@RequestBody MissionDto missionDto) {
        return ResponseEntity.ok(missionService.createMission(missionDto));
    }
   
    @PutMapping("/{missionId}/start")
    public ResponseEntity<Response> startMission(@PathVariable Long missionId) {
        if (missionId == null) {
            throw new InvalidRequestException("Mission ID is required");
        }

        Response response = missionService.startMission(missionId);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/delete/{missionId}")
    public ResponseEntity<Response> deleteMission(@PathVariable Long missionId) {
        return ResponseEntity.ok(missionService.deleteMission(missionId));
    }
    
    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllMissions() {
        return ResponseEntity.ok(missionService.getAllMissions());
    }

    @GetMapping("/get-mission-by-id/{missionId}")
    public ResponseEntity<Response> getMissionById(@PathVariable Long missionId) {
        return ResponseEntity.ok(missionService.getMissionById(missionId));
    }

    @PutMapping("/update/{missionId}")
    public ResponseEntity<Response> updateMission(@PathVariable Long missionId, @RequestBody MissionDto missionDto) {
        log.info("Updating mission with id: {}", missionId);
        return ResponseEntity.ok(missionService.updateMission(missionId, missionDto));
    }

    @PatchMapping("/change-status/{missionId}")
    public ResponseEntity<Response> changeMissionStatus(@PathVariable Long missionId) {
        log.info("Changing status of mission with id: {}", missionId);
        return ResponseEntity.ok(missionService.changeMissionStatus(missionId));
    }
    
    @GetMapping("/get-mission-details/{missionId}")
    public ResponseEntity<Response> getMissionDetails(@PathVariable Long missionId) {
        log.info("Fetching detailed information for mission with id: {}", missionId);
        return ResponseEntity.ok(missionService.getMissionDetails(missionId));
    }

    @GetMapping("/{missionId}/calculate-effective-completion-time")
    public ResponseEntity<Response> calculateEstimatedCompletionTime(@PathVariable Long missionId) {
        Response response = missionService.calculateEffectiveCompletionTime(missionId);
        return ResponseEntity.ok(response);
    }
    
    /*
     * consente di filtrare missioni in base al loro stato e  
     * ottenere i risultati in formato paginato.
     * 
     */
    @GetMapping("/filter-mission-by-status")
    public ResponseEntity<Response> filterMission(	
			@RequestParam(required = false) String status,				
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size
	) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
		MissionStatus missionStatus = status != null ? MissionStatus.valueOf(status.toUpperCase()) : null;

		return ResponseEntity.ok(missionService.filterMission(missionStatus,pageable));

	}
    
  
    
}







