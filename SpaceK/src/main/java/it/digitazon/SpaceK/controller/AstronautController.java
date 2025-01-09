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
import it.digitazon.SpaceK.exception.InvalidRequestException;
import it.digitazon.SpaceK.service.interfaces.AstronautService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/astronauts")
@RequiredArgsConstructor
public class AstronautController {
	
	 private final AstronautService astronautService;
	 
	//il body deve essere inviato come form-data 
	 
	 @PostMapping("/create")
	 public ResponseEntity<Response> createAstronaut(
	         @RequestParam String name,
	         @RequestParam String specialty,
	         @RequestParam boolean isAvailable
	 ) {
	     if (name.isEmpty() || specialty.isEmpty()) {
	         throw new InvalidRequestException("Name and specialty are required fields.");
	     }
	     return ResponseEntity.ok(astronautService.createAstronaut(name, specialty, isAvailable));
	 }


    @PutMapping("/update")
    public ResponseEntity<Response> updateAstronaut(
            @RequestParam Long astronautId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) Boolean isAvailable
    ) {
        if (astronautId == null || isAvailable==null) {
            throw new InvalidRequestException("Astronaut ID and isAvailable is required.");
        }
        return ResponseEntity.ok(
                astronautService.updateAstronaut(astronautId,name, specialty, isAvailable )
        );
    }
    
    @PutMapping("/{missionId}/astronauts/{astronautId}")
    public ResponseEntity<Response> assignAstronautToMission(
            @PathVariable Long missionId,
            @PathVariable Long astronautId
    ) {
        if (missionId == null || astronautId == null) {
            throw new InvalidRequestException("Mission ID and Astronaut ID are required.");
        }
        return ResponseEntity.ok(astronautService.assignAstronautToMission(missionId, astronautId));
    }

    
    @DeleteMapping("/delete/{astronautId}")
    public ResponseEntity<Response> deleteAstronaut(@PathVariable Long astronautId) {
        return ResponseEntity.ok(astronautService.deleteAstronaut(astronautId));
    }

    @GetMapping("/get-by-id/{astronautId}")
    public ResponseEntity<Response> getAstronautById(@PathVariable Long astronautId) {
        return ResponseEntity.ok(astronautService.getAstronautById(astronautId));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllAstronauts() {
        return ResponseEntity.ok(astronautService.getAllAstronauts());
    }
    
    @GetMapping("/filter-astronauts-by-available")
    public ResponseEntity<Response> filterAstronauts(
            @RequestParam(required = false) Boolean available,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));

        // Chiamata al servizio per filtrare gli astronauti
        Response response = astronautService.filterAstronauts(available, pageable);

        return ResponseEntity.ok(response);
    }

}
